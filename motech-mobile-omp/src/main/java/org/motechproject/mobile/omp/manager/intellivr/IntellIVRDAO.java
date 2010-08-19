package org.motechproject.mobile.omp.manager.intellivr;

import java.util.List;

import org.hibernate.SessionFactory;
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
	public List<IVRCallSession> loadIVRCallSessionsByUserPhoneAndState(
			String user, String phone, Integer[] states) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.eq("userId", user))
		.add(Restrictions.eq("phone", phone))
		.add(Restrictions.in("state", states))
		.list();
	}
	
	public IVRCallSession loadIVRCallSessionByExternalId(String externalId) {
		return (IVRCallSession)sessionFactory
		.getCurrentSession()
		.createCriteria(IVRCallSession.class)
		.add(Restrictions.eq("externalId", externalId))
		.uniqueResult();
	}

}