package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Privilege;
import cache.Configurations;
import bean.TransferDbData;
import dao.PrivilegeDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class PrivilegeDaoImpl extends HibernateDaoSupport implements PrivilegeDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT p FROM Privilege AS p WHERE p.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND p.uid = ?";
				break;
			case name:
				preHql += " AND p.name = ?";
				break;
			case privilegeValue:
				preHql += " AND p.privilegevalue >= ? AND p.privilegevalue <= ?";
				break;
			case privilegeCode:
				preHql += " AND p.privilegecode = ?";
				break;
			case createIp:
				preHql += " AND p.createip = ?";
				break;
			case createTime:
				preHql += " AND p.createtime >= ? AND p.createtime <= ?";
				break;
			case createUserUid:
				preHql += " AND p.admin.uid = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Privilege> hibernateListProcess(TransferDbData transferDbData)
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
			case name:
			case privilegeCode:
			case createIp:
			case createUserUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case privilegeValue:
			case createTime:
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
		List<Privilege> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Privilege privilege = new Privilege();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				privilege.setUid(value);
				break;
			case name:
				privilege.setName(value);
				break;
			case privilegeCode:
				privilege.setPrivilegecode(value);
				break;
			case privilegeValue:
				privilege.setPrivilegevalue(Integer.valueOf(value));
				break;
			case createIp:
				privilege.setCreateip(value);
				break;
			case createTime:
				privilege.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				privilege.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(privilege);
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
		Privilege privilege = (Privilege) session.get(Privilege.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				privilege.setName(value);
				break;
			case privilegeCode:
				privilege.setPrivilegecode(value);
				break;
			case privilegeValue:
				privilege.setPrivilegevalue(Integer.valueOf(value));
				break;
			case createIp:
				privilege.setCreateip(value);
				break;
			case createTime:
				privilege.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				privilege.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(privilege);
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
		Privilege privilege = (Privilege) session.get(Privilege.class, s_uid);
		privilege.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(privilege);
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
	public List<Privilege> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
