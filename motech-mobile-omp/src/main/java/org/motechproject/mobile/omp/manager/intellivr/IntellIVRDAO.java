package org.motechproject.mobile.omp.manager.intellivr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class IntellIVRDAO implements IVRDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long saveIVRCallSession(IVRCallSession callSession) {
		return ((Long)sessionFactory.getCurrentSession().save(callSession)).longValue();
	}

	public IVRCallSession loadIVRCallSession(long id) {
		return (IVRCallSession)sessionFactory.getCurrentSession().load(IVRCallSession.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<IVRCallSession> loadIVRCallSessionsByState(Integer[] states) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.in("state", states))
		.list();
	}

	@SuppressWarnings("unchecked")
	public List<IVRCallSession> loadIVRCallSessions(
			String user, String phone, String language, Integer[] states, int attempts, int days, String callDirection) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.eq("userId", user))
		.add(Restrictions.eq("phone", phone))
		.add(Restrictions.in("state", states))
		.add(Restrictions.eq("attempts", attempts))
		.add(Restrictions.eq("days", days))
		.add(Restrictions.eq("callDirection", callDirection))
		.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<IVRCallSession> loadIVRCallSessionsByStateNextAttemptBeforeDate(
			Integer[] states, Date date) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.in("state", states))
		.add(Restrictions.le("nextAttempt", date))
		.list();
		
	}
	
	public IVRCall loadIVRCallByExternalId(String externalId) {
		return (IVRCall)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCall.class)
		.add(Restrictions.eq("externalId", externalId))
		.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<IVRCallSession> loadIVRCallSessionsCreatedBetweenDates(
			Date start, Date end) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.ge("created", start))
		.add(Restrictions.le("created", end))
		.list();
	}

	public int countIVRCallSessionsCreatedBetweenDates(Date start, Date end) {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.setProjection(Projections.rowCount())
		.add(Restrictions.ge("created", start))
		.add(Restrictions.le("created", end))
		.list()
		.get(0);
	}

	public int countIVRCallSesssions() {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.setProjection(Projections.rowCount())
		.list()
		.get(0);
	}

	public int countIVRCalls() {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCall.class)
		.setProjection(Projections.rowCount())
		.list()
		.get(0);
	}

	public int countIVRCallsCreatedBetweenDates(Date start, Date end) {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCall.class)
		.setProjection(Projections.rowCount())
		.add(Restrictions.ge("created", start))
		.add(Restrictions.le("created", end))
		.list()
		.get(0);
	}

	public int countIVRCallsWithStatus(IVRCallStatus status) {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCall.class)
		.setProjection(Projections.rowCount())
		.add(Restrictions.eq("status", status))
		.list()
		.get(0);
	}

	public int countIVRCallsCreatedBetweenDatesWithStatus(Date start, Date end, IVRCallStatus status) {
		return (Integer)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCall.class)
		.setProjection(Projections.rowCount())
		.add(Restrictions.ge("created", start))
		.add(Restrictions.le("created", end))
		.add(Restrictions.eq("status", status))
		.list()
		.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<IVRRecordingStat> getIVRRecordingStats() {
		List queryResult = 
		sessionFactory
		.getCurrentSession()
		.createQuery("select name, count(ivr_menu_id), avg(duration) from org.motechproject.mobile.omp.manager.intellivr.IVRMenu group by name")
		.list();
		List<IVRRecordingStat> returnValue = new ArrayList<IVRRecordingStat>();
		for ( Object o : queryResult ) {
			Object[] row = (Object[]) o;
			returnValue.add(new IVRRecordingStat((String)row[0], (Long)row[1], (Double)row[2]));
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public List<IVRCallStatusStat> getIVRCallStatusStats() {
		List queryResult = 
			sessionFactory
			.getCurrentSession()
			.createQuery("select status, count(ivr_call_id) from org.motechproject.mobile.omp.manager.intellivr.IVRCall group by status")
			.list();
		Map<IVRCallStatus, Object[]> map = new HashMap<IVRCallStatus, Object[]>();
		for ( Object o : queryResult ) {
			Object[] row = (Object[])o;
			map.put((IVRCallStatus)row[0], row);
		}
		List<IVRCallStatusStat> returnValue = new ArrayList<IVRCallStatusStat>();
		for ( IVRCallStatus s : IVRCallStatus.values() ) {
			if ( map.containsKey(s) ) {
				Object[] o = map.get(s);
				returnValue.add(new IVRCallStatusStat((IVRCallStatus)o[0], (Long)o[1]));
			} else
				returnValue.add(new IVRCallStatusStat(s,0));
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public List<IVRCallStatusStat> getIVRCallStatusStatsBetweenDates(
			Date start, Date end) {
		List queryResult = 
			sessionFactory
			.getCurrentSession()
			.createQuery("select status, count(ivr_call_id) from org.motechproject.mobile.omp.manager.intellivr.IVRCall where created >= :start and created <= :end group by status")
			.setTimestamp("start", start)
			.setTimestamp("end", end)
			.list();
		Map<IVRCallStatus, Object[]> map = new HashMap<IVRCallStatus, Object[]>();
		for ( Object o : queryResult ) {
			Object[] row = (Object[])o;
			map.put((IVRCallStatus)row[0], row);
		}
		List<IVRCallStatusStat> returnValue = new ArrayList<IVRCallStatusStat>();
		for ( IVRCallStatus s : IVRCallStatus.values() ) {
			if ( map.containsKey(s) ) {
				Object[] o = map.get(s);
				returnValue.add(new IVRCallStatusStat((IVRCallStatus)o[0], (Long)o[1]));
			} else
				returnValue.add(new IVRCallStatusStat(s,0));
		}
		return returnValue;
	}

}