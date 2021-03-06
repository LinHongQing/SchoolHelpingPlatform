package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Problem;
import pojo.Solve;
import pojo.User;
import cache.Configurations;
import bean.TransferDbData;
import dao.SolveDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class SolveDaoImpl extends HibernateDaoSupport implements SolveDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT s FROM Solve AS s WHERE s.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND s.uid = ?";
				break;
			case problemUid:
				preHql += " AND s.problem.uid = ?";
				break;
			case createUserUid:
				preHql += " AND s.user.uid = ?";
				break;
			case assistantUid:
				preHql += " AND s.assistantid = ?";
				break;
			case createIp:
				preHql += " AND s.createip = ?";
				break;
			case createTime:
				preHql += " AND s.createtime >= ? AND s.createtime <= ?";
				break;
			}
		}
		preHql += " ORDER BY s.createtime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Solve> hibernateListProcess(TransferDbData transferDbData)
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
			case problemUid:
			case createUserUid:
			case assistantUid:
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
		List<Solve> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Solve solve = new Solve();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				solve.setUid(value);
				break;
			case problemUid:
				Problem problem = new Problem();
				problem.setUid(value);
				solve.setProblem(problem);
				break;
			case createUserUid:
				User user = new User();
				user.setUid(value);
				solve.setUser(user);
				break;
			case assistantUid:
				solve.setAssistantid(value);
				break;
			case description:
				solve.setDescription(value);
				break;
			case createIp:
				solve.setCreateip(value);
				break;
			case createTime:
				solve.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(solve);
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
		Solve solve = (Solve) session.get(Solve.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case problemUid:
				Problem problem = new Problem();
				problem.setUid(value);
				solve.setProblem(problem);
				break;
			case createUserUid:
				User user = new User();
				user.setUid(value);
				solve.setUser(user);
				break;
			case assistantUid:
				solve.setAssistantid(value);
				break;
			case description:
				solve.setDescription(value);
				break;
			case createIp:
				solve.setCreateip(value);
				break;
			case createTime:
				solve.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(solve);
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
		Solve solve = (Solve) session.get(Solve.class, s_uid);
		solve.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(solve);
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
	public List<Solve> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}
}
