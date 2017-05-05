package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Qualificationrequest;
import pojo.Qualificationtype;
import pojo.User;
import cache.Configurations;
import bean.TransferDbData;
import dao.QualificationrequestDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class QualificationrequestDaoImpl extends HibernateDaoSupport implements QualificationrequestDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT q FROM Qualificationrequest AS q WHERE q.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND q.uid = ?";
				break;
			case userUid:
				preHql += " AND q.user.uid = ?";
				break;
			case typeUid:
				preHql += " AND q.qualificationtype.uid = ?";
				break;
			case description:
				preHql += " AND q.description LIKE ?";
				break;
			case resourceUid:
				preHql += " AND q.resourceid = ?";
				break;
			case requestTime:
				preHql += " AND q.requesttime >= ? AND q.requesttime <= ?";
				break;
			case requestIp:
				preHql += " AND q.requestip = ?";
				break;
			case checkingStatus:
				preHql += " AND q.checkingstatus = ?";
				break;
			case checkingType:
				preHql += " AND q.checkingtype = ?";
				break;
			case checkingIp:
				preHql += " AND q.checkingip = ?";
				break;
			case checkingTime:
				preHql += " AND q.checkingtime >= ? AND q.checkingtime <= ?";
				break;
			case checkingUserUid:
				preHql += " AND q.admin.uid = ?";
				break;
			}
		}
		preHql += " ORDER BY q.requesttime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Qualificationrequest> hibernateListProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		String hql = getHql(transferDbData);
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Query query = session.createQuery(hql);
		int currentSet = 0;
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case description:
				query.setString(currentSet, "%" + value + "%");
				currentSet++;
				break;
			case uid:
			case userUid:
			case typeUid:
			case resourceUid:
			case requestIp:
			case checkingStatus:
			case checkingType:
			case checkingIp:
			case checkingUserUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case requestTime:
			case checkingTime:
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
		List<Qualificationrequest> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Qualificationrequest qualificationRequest = new Qualificationrequest();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				qualificationRequest.setUid(value);
				break;
			case userUid:
				User user = new User();
				user.setUid(value);
				qualificationRequest.setUser(user);
				break;
			case typeUid:
				Qualificationtype qualificationType = new Qualificationtype();
				qualificationType.setUid(value);
				qualificationRequest.setQualificationtype(qualificationType);
				break;
			case description:
				qualificationRequest.setDescription(value);
				break;
			case resourceUid:
				qualificationRequest.setResourceid(value);
				break;
			case requestIp:
				qualificationRequest.setRequestip(value);
				break;
			case requestTime:
				qualificationRequest.setRequesttime(Integer.valueOf(value));
				break;
			case checkingStatus:
				qualificationRequest.setCheckingstatus(Short.valueOf(value));
				break;
			case checkingType:
				qualificationRequest.setCheckingtype(Short.valueOf(value));
				break;
			case checkingIp:
				qualificationRequest.setCheckingip(value);
				break;
			case checkingTime:
				qualificationRequest.setCheckingtime(Integer.valueOf(value));
				break;
			case checkingUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				qualificationRequest.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(qualificationRequest);
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
		Qualificationrequest qualificationRequest = (Qualificationrequest) session.get(Qualificationrequest.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case userUid:
				User user = new User();
				user.setUid(value);
				qualificationRequest.setUser(user);
				break;
			case typeUid:
				Qualificationtype qualificationType = new Qualificationtype();
				qualificationType.setUid(value);
				qualificationRequest.setQualificationtype(qualificationType);
				break;
			case description:
				qualificationRequest.setDescription(value);
				break;
			case resourceUid:
				qualificationRequest.setResourceid(value);
				break;
			case requestIp:
				qualificationRequest.setRequestip(value);
				break;
			case requestTime:
				qualificationRequest.setRequesttime(Integer.valueOf(value));
				break;
			case checkingStatus:
				qualificationRequest.setCheckingstatus(Short.valueOf(value));
				break;
			case checkingType:
				qualificationRequest.setCheckingtype(Short.valueOf(value));
				break;
			case checkingIp:
				qualificationRequest.setCheckingip(value);
				break;
			case checkingTime:
				qualificationRequest.setCheckingtime(Integer.valueOf(value));
				break;
			case checkingUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				qualificationRequest.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(qualificationRequest);
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
		Qualificationrequest qualificationRequest = (Qualificationrequest) session.get(Qualificationrequest.class, s_uid);
		qualificationRequest.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(qualificationRequest);
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
	public List<Qualificationrequest> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
