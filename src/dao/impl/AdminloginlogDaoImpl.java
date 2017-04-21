package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Adminloginlog;
import cache.Configurations;
import bean.TransferDbData;
import dao.AdminloginlogDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class AdminloginlogDaoImpl extends HibernateDaoSupport implements AdminloginlogDao {

	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT a FROM Adminloginlog AS a WHERE a.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND a.uid = ?";
				break;
			case adminUid:
				preHql += " AND a.admin.uid = ?";
				break;
			case loginTime:
				preHql += " AND a.logintime >= ? AND a.logintime <= ?";
				break;
			case ip:
				preHql += " AND a.ip = ?";
			}
		}
		preHql += " order by a.logintime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Adminloginlog> hibernateListProcess(TransferDbData transferDbData)
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
			case adminUid:
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
		List<Adminloginlog> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Adminloginlog loginLog = new Adminloginlog();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				loginLog.setUid(value);
				break;
			case adminUid:
				Admin admin = new Admin();
				admin.setUid(value);
				loginLog.setAdmin(admin);
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
		Adminloginlog loginLog = (Adminloginlog) session.get(Adminloginlog.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case adminUid:
				Admin admin = new Admin();
				admin.setUid(value);
				loginLog.setAdmin(admin);
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
	public List<Adminloginlog> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
