package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Term;
import cache.Configurations;
import bean.TransferDbData;
import dao.TermDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class TermDaoImpl extends HibernateDaoSupport implements TermDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT t FROM Term AS t WHERE t.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND t.uid = ?";
				break;
			case name:
				preHql += " AND t.name = ?";
				break;
			case startTime:
				preHql += " AND t.starttime >= ? AND t.starttime <= ?";
				break;
			case endTime:
				preHql += " AND t.endtime >= ? AND t.endtime <= ?";
				break;
			case createIp:
				preHql += " AND t.createip = ?";
				break;
			case createTime:
				preHql += " AND t.createtime >= ? AND t.createtime <= ?";
				break;
			case createUserUid:
				preHql += " AND t.admin.uid = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Term> hibernateListProcess(TransferDbData transferDbData)
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
			case createIp:
			case createUserUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case startTime:
			case endTime:
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
		List<Term> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Term term = new Term();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				term.setUid(value);
				break;
			case name:
				term.setName(value);
				break;
			case startTime:
				term.setStarttime(Integer.valueOf(value));
				break;
			case endTime:
				term.setEndtime(Integer.valueOf(value));
				break;
			case createIp:
				term.setCreateip(value);
				break;
			case createTime:
				term.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				term.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(term);
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
		Term term = (Term) session.get(Term.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				term.setName(value);
				break;
			case startTime:
				term.setStarttime(Integer.valueOf(value));
				break;
			case endTime:
				term.setEndtime(Integer.valueOf(value));
				break;
			case createIp:
				term.setCreateip(value);
				break;
			case createTime:
				term.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				term.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(term);
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
		Term term = (Term) session.get(Term.class, s_uid);
		term.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(term);
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
	public List<Term> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

}
