package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Qualificationtype;
import cache.Configurations;
import bean.TransferDbData;
import dao.QualificationtypeDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class QualificationtypeDaoImpl extends HibernateDaoSupport implements QualificationtypeDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT q FROM Qualificationtype AS q WHERE q.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND q.uid = ?";
				break;
			case name:
				preHql += " AND q.name = ?";
				break;
			case problemTypeUid:
				preHql += " AND q.problemtypeid = ?";
				break;
			case createIp:
				preHql += " AND q.createip = ?";
				break;
			case createTime:
				preHql += " AND q.createtime >= ? AND q.createtime <= ?";
				break;
			case createUserUid:
				preHql += " AND q.admin.uid = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Qualificationtype> hibernateListProcess(TransferDbData transferDbData)
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
			case problemTypeUid:
			case createIp:
			case createUserUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
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
		List<Qualificationtype> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Qualificationtype qualificationType = new Qualificationtype();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				qualificationType.setUid(value);
				break;
			case name:
				qualificationType.setName(value);
				break;
			case problemTypeUid:
				qualificationType.setProblemtypeid(value);
				break;
			case createIp:
				qualificationType.setCreateip(value);
				break;
			case createTime:
				qualificationType.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				qualificationType.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(qualificationType);
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
		Qualificationtype qualificationType = (Qualificationtype) session.get(Qualificationtype.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				qualificationType.setName(value);
				break;
			case problemTypeUid:
				qualificationType.setProblemtypeid(value);
				break;
			case createIp:
				qualificationType.setCreateip(value);
				break;
			case createTime:
				qualificationType.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				qualificationType.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(qualificationType);
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
		Qualificationtype qualificationType = (Qualificationtype) session.get(Qualificationtype.class, s_uid);
		qualificationType.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(qualificationType);
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
	public List<Qualificationtype> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
