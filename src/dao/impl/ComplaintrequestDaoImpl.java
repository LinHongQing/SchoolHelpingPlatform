package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Complaintrequest;
import pojo.Problem;
import pojo.User;
import cache.Configurations;
import bean.TransferDbData;
import dao.ComplaintrequestDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class ComplaintrequestDaoImpl extends HibernateDaoSupport implements ComplaintrequestDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT c FROM Complaintrequest AS c WHERE c.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND c.uid = ?";
				break;
			case userUid:
				preHql += " AND c.user.uid = ?";
				break;
			case problemUid:
				preHql += " AND c.problem.uid = ?";
				break;
			case createIp:
				preHql += " AND c.createip = ?";
				break;
			case createTime:
				preHql += " AND c.createtime >= ? AND c.createtime <= ?";
				break;
			case description:
				preHql += " AND c.description = ?";
				break;
			case resourceUid:
				preHql += " AND c.resourceid = ?";
				break;
			case status:
				preHql += " AND c.status = ?";
				break;
			case replyDescription:
				preHql += " AND c.replydescription = ?";
				break;
			case replyResourceUid:
				preHql += " AND c.replyresourceid = ?";
				break;
			case replyCreateUserUid:
				preHql += " AND c.admin.uid = ?";
				break;
			case replyCreateIp:
				preHql += " AND c.replycreateip = ?";
				break;
			case replyCreateTime:
				preHql += " AND c.replycreatetime >= ? AND c.replycreatetime <= ?";
				break;
			}
		}
		preHql += " order by c.createtime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Complaintrequest> hibernateListProcess(TransferDbData transferDbData)
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
			case problemUid:
			case createIp:
			case description:
			case resourceUid:
			case status:
			case replyResourceUid:
			case replyDescription:
			case replyCreateUserUid:
			case replyCreateIp:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case createTime:
			case replyCreateTime:
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
		List<Complaintrequest> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Complaintrequest complaintRequest = new Complaintrequest();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				complaintRequest.setUid(value);
				break;
			case userUid:
				User user = new User();
				user.setUid(value);
				complaintRequest.setUser(user);
				break;
			case problemUid:
				Problem problem = new Problem();
				problem.setUid(value);
				complaintRequest.setProblem(problem);
				break;
			case createIp:
				complaintRequest.setCreateip(value);
				break;
			case description:
				complaintRequest.setDescription(value);
				break;
			case createTime:
				complaintRequest.setCreatetime(Integer.valueOf(value));
				break;
			case status:
				complaintRequest.setStatus(Integer.valueOf(value));
				break;
			case replyDescription:
				complaintRequest.setReplydescription(value);
				break;
			case replyResourceUid:
				complaintRequest.setReplyresourceid(value);
			case replyCreateUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				complaintRequest.setAdmin(admin);
				break;
			case replyCreateIp:
				complaintRequest.setReplycreateip(value);
				break;
			case replyCreateTime:
				complaintRequest.setReplycreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(complaintRequest);
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
		Complaintrequest complaintRequest = (Complaintrequest) session.get(Complaintrequest.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case userUid:
				User user = new User();
				user.setUid(value);
				complaintRequest.setUser(user);
				break;
			case problemUid:
				Problem problem = new Problem();
				problem.setUid(value);
				complaintRequest.setProblem(problem);
				break;
			case createIp:
				complaintRequest.setCreateip(value);
				break;
			case description:
				complaintRequest.setDescription(value);
				break;
			case createTime:
				complaintRequest.setCreatetime(Integer.valueOf(value));
				break;
			case status:
				complaintRequest.setStatus(Integer.valueOf(value));
				break;
			case replyDescription:
				complaintRequest.setReplydescription(value);
				break;
			case replyResourceUid:
				complaintRequest.setReplyresourceid(value);
			case replyCreateUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				complaintRequest.setAdmin(admin);
				break;
			case replyCreateIp:
				complaintRequest.setReplycreateip(value);
				break;
			case replyCreateTime:
				complaintRequest.setReplycreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(complaintRequest);
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
	public List<Complaintrequest> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
