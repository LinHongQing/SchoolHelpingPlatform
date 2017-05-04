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
import dao.AdminDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
	Session session;
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT a FROM Admin AS a WHERE a.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND a.uid = ?";
				break;
			case email:
				preHql += " AND a.email = ?";
				break;
			case name:
				preHql += " AND a.name = ?";
				break;
			case pwd:
				preHql += " AND a.pwd = ?";
				break;
			case privilegeUid:
				preHql += " AND a.privilege.uid = ?";
				break;
			case createTime:
				preHql += " AND a.createtime >= ? AND a.createtime <= ?";
				break;
			case createIp:
				preHql += " AND a.createip = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Admin> hibernateListProcess(TransferDbData transferDbData)
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
			case email:
			case name:
			case pwd:
			case privilegeUid:
			case createIp:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case createTime:
				String[] splitStrings = value.split(Configurations.string_split);
				if (splitStrings.length > 1) {
					for (int i = 0; i < splitStrings.length; i++)
						query.setString(currentSet + i, splitStrings[i]);
				} else {
					query.setString(currentSet++, value);
					query.setString(currentSet++, value);
				}
			}
		}
		List<Admin> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Admin admin = new Admin();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				admin.setUid(value);
				break;
			case email:
				admin.setEmail(value);
				break;
			case name:
				admin.setName(value);
				break;
			case pwd:
				admin.setPwd(value);
				break;
			case privilegeUid:
				Privilege privilege = new Privilege();
				privilege.setUid(value);
				admin.setPrivilege(privilege);
				break;
			case createIp:
				admin.setCreateip(value);
				break;
			case createTime:
				admin.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(admin);
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
		Admin admin = (Admin) session.get(Admin.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case email:
				admin.setEmail(value);
				break;
			case name:
				admin.setName(value);
				break;
			case pwd:
				admin.setPwd(value);
				break;
			case privilegeUid:
				Privilege privilege = new Privilege();
				privilege.setUid(value);
				admin.setPrivilege(privilege);
				break;
			case createIp:
				admin.setCreateip(value);
				break;
			case createTime:
				admin.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(admin);
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
		Admin admin = (Admin) session.get(Admin.class, s_uid);
		admin.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(admin);
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
	public List<Admin> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

}
