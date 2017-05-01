package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Problemtype;
import pojo.Resource;
import cache.Configurations;
import bean.TransferDbData;
import dao.ResourceDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class ResourceDaoImpl extends HibernateDaoSupport implements ResourceDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT r FROM Resource AS r WHERE r.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND r.uid = ?";
				break;
			case name:
				preHql += " AND r.name = ?";
				break;
			case type:
				preHql += " AND p.type = ?";
				break;
			case value:
				preHql += " AND p.value = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Resource> hibernateListProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		String hql = getHql(transferDbData);
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Query query = session.createQuery(hql);
		int currentSet = 0;
		for (Integer key : transferDbData.getValues().keySet()) {
			String s_value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
			case name:
			case type:
			case value:
				query.setString(currentSet, s_value);
				currentSet++;
				break;
			}
		}
		List<Resource> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Resource resource = new Resource();
		for (Integer key : transferDbData.getValues().keySet()) {
			String s_value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				resource.setUid(s_value);
				break;
			case name:
				resource.setName(s_value);
				break;
			case type:
				resource.setType(Short.valueOf(s_value));
				break;
			case value:
				resource.setValue(s_value);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(resource);
		tx.commit();
	}
	
	private void hibernateUpdateProcess(TransferDbData transferDbData)
			throws HibernateSessionNotInitializedException, MissingParameterException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		String s_uid = transferDbData.getValues().get(uid);
		if (s_uid == null)
			throw new MissingParameterException("uid 不能为空");
		session.beginTransaction();
		Resource resource = (Resource) session.get(Resource.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String s_value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				resource.setName(s_value);
				break;
			case type:
				resource.setType(Short.valueOf(s_value));
				break;
			case value:
				resource.setValue(s_value);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(resource);
		tx.commit();
	}
	
	private void hibernateDeleteProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException{
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		String s_uid = transferDbData.getValues().get(uid);
		if (s_uid == null)
			throw new MissingParameterException("uid 不能为空");
		session.beginTransaction();
		Problemtype problemType = (Problemtype) session.get(Problemtype.class, s_uid);
		problemType.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(problemType);
		tx.commit();
	}
	
	@Override
	public void init() throws HibernateException {
		// TODO Auto-generated method stub
		session = getHibernateTemplate().getSessionFactory().openSession();
	}

	@Override
	public void close() throws HibernateException {
		// TODO Auto-generated method stub
		if (session != null)
			releaseSession(session);
	}

	@Override
	public void insert(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		hibernateSaveProcess(transferDbData);
	}

	@Override
	public void update(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException,
			MissingParameterException {
		// TODO Auto-generated method stub
		hibernateUpdateProcess(transferDbData);
	}

	@Override
	public void delete(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException,
			MissingParameterException {
		// TODO Auto-generated method stub
		hibernateDeleteProcess(transferDbData);
	}

	@Override
	public List<Resource> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
