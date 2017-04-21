package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.User;
import pojo.Userloginlog;
import cache.Configurations;
import bean.TransferDbData;
import dao.UserloginlogDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class UserloginlogDaoImpl extends HibernateDaoSupport implements UserloginlogDao {

	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT l FROM Userloginlog AS l WHERE l.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND l.uid = ?";
				break;
			case userUid:
				preHql += " AND l.user.uid = ?";
				break;
			case loginTime:
				preHql += " AND l.logintime >= ? AND l.logintime <= ?";
				break;
			case ip:
				preHql += " AND l.ip = ?";
			}
		}
		preHql += " order by l.logintime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Userloginlog> hibernateListProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		String hql = getHql(transferDbData);
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Query query = session.createQuery(hql);
		int currentSet = 0;
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
			case userUid:
			case ip:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case loginTime:
				String[] splitStrings = value.split(Configurations.split_string);
				if (splitStrings.length > 1) {
					for (int i = 0; i < splitStrings.length; i++)
						query.setString(currentSet + i, splitStrings[i]);
				} else {
					query.setString(currentSet++, value);
					query.setString(currentSet++, value);
				}
			}
		}
		List<Userloginlog> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Userloginlog loginLog = new Userloginlog();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				loginLog.setUid(value);
				break;
			case userUid:
				User user = new User();
				user.setUid(value);
				loginLog.setUser(user);
				break;
			case ip:
				loginLog.setIp(value);
				break;
			case loginTime:
				loginLog.setLogintime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(loginLog);
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
		Userloginlog loginLog = (Userloginlog) session.get(Userloginlog.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case userUid:
				User user = new User();
				user.setUid(value);
				loginLog.setUser(user);
				break;
			case ip:
				loginLog.setIp(value);
				break;
			case loginTime:
				loginLog.setLogintime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(loginLog);
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
	public List<Userloginlog> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
