package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
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

}