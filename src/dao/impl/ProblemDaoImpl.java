package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Location;
import pojo.Problem;
import pojo.Problemtype;
import pojo.Solve;
import pojo.User;
import cache.Configurations;
import bean.TransferDbData;
import dao.ProblemDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class ProblemDaoImpl extends HibernateDaoSupport implements ProblemDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT p FROM Problem AS p WHERE p.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND p.uid = ?";
				break;
			case createUserUid:
				preHql += " AND p.user.uid = ?";
				break;
			case locationUid:
				preHql += " AND p.location.uid = ?";
				break;
			case problemTypeUid:
				preHql += " AND p.problemtype.uid = ?";
				break;
			case title:
				preHql += " AND p.title = ?";
				break;
			case preferDay:
				preHql += " AND p.preferday = ?";
				break;
			case preferStart:
				preHql += " AND p.preferstart >= ? AND p.preferstart <= ?";
				break;
			case preferEnd:
				preHql += " AND p.preferend >= ? AND p.preferend <= ?";
				break;
			case description:
				preHql += " AND p.description = ?";
				break;
			case resourceUid:
				preHql += " AND p.resourceid = ?";
				break;
			case createIp:
				preHql += " AND p.createip = ?";
				break;
			case createTime:
				preHql += " AND p.createtime >= ? AND p.createtime <= ?";
				break;
			case status:
				preHql += " AND p.status = ?";
				break;
			case solvedUid:
				preHql += " AND p.solve.uid = ?";
				break;
			}
		}
		preHql += " ORDER BY p.createtime desc";
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Problem> hibernateListProcess(TransferDbData transferDbData)
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
			case createUserUid:
			case locationUid:
			case problemTypeUid:
			case title:
			case preferDay:
			case description:
			case resourceUid:
			case createIp:
			case status:
			case solvedUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case preferStart:
			case preferEnd:
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
		List<Problem> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Problem problem = new Problem();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				problem.setUid(value);
				break;
			case createUserUid:
				User user = new User();
				user.setUid(value);
				problem.setUser(user);
				break;
			case locationUid:
				Location location = new Location();
				location.setUid(value);
				problem.setLocation(location);
				break;
			case problemTypeUid:
				Problemtype problemType = new Problemtype();
				problemType.setUid(value);
				problem.setProblemtype(problemType);
				break;
			case title:
				problem.setTitle(value);
				break;
			case preferDay:
				problem.setPreferday(value);
				break;
			case preferStart:
				problem.setPreferstart(Integer.valueOf(value));
				break;
			case preferEnd:
				problem.setPreferend(Integer.valueOf(value));
				break;
			case description:
				problem.setDescription(value);
				break;
			case resourceUid:
				problem.setResourceid(value);
				break;
			case createIp:
				problem.setCreateip(value);
				break;
			case createTime:
				problem.setCreatetime(Integer.valueOf(value));
				break;
			case status:
				problem.setStatus(Integer.valueOf(value));
				break;
			case solvedUid:
				Solve solve = new Solve();
				solve.setUid(value);
				problem.setSolve(solve);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(problem);
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
		Problem problem = (Problem) session.get(Problem.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case createUserUid:
				User user = new User();
				user.setUid(value);
				problem.setUser(user);
				break;
			case locationUid:
				Location location = new Location();
				location.setUid(value);
				problem.setLocation(location);
				break;
			case problemTypeUid:
				Problemtype problemType = new Problemtype();
				problemType.setUid(value);
				problem.setProblemtype(problemType);
				break;
			case title:
				problem.setTitle(value);
				break;
			case preferDay:
				problem.setPreferday(value);
				break;
			case preferStart:
				problem.setPreferstart(Integer.valueOf(value));
				break;
			case preferEnd:
				problem.setPreferend(Integer.valueOf(value));
				break;
			case description:
				problem.setDescription(value);
				break;
			case resourceUid:
				problem.setResourceid(value);
				break;
			case createIp:
				problem.setCreateip(value);
				break;
			case createTime:
				problem.setCreatetime(Integer.valueOf(value));
				break;
			case status:
				problem.setStatus(Integer.valueOf(value));
				break;
			case solvedUid:
				Solve solve = new Solve();
				if (value == null) {
					problem.setSolve(null);
				} else {
					solve.setUid(value);
					problem.setSolve(solve);
				}
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(problem);
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
		Problem problem = (Problem) session.get(Problem.class, s_uid);
		problem.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(problem);
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
	public List<Problem> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Problem> selectApplicable(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException {
		// TODO Auto-generated method stub
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		String s_problemTypeUid = transferDbData.getValues().get(problemTypeUid);
		String s_currentDay = transferDbData.getValues().get(currentWeekDay);
		String s_currentTime = transferDbData.getValues().get(currentTime);
		
		if (s_problemTypeUid == null || "".equals(s_problemTypeUid))
			throw new MissingParameterException("problemTypeUid 不能为空");
		if (s_currentDay == null || "".equals(s_currentDay))
			throw new MissingParameterException("currentDay 不能为空");
		if (s_currentTime == null || "".equals(s_currentTime))
			throw new MissingParameterException("currentTime 不能为空");
		
		String sql = "SELECT * FROM campushelpingplatform.problem where";							// 初始部分
		
		sql += " find_in_set('" + s_currentDay + "', preferDay) and (";		// 日期的星期部分
		// 问题类型部分
		String[] problemTypeUids = s_problemTypeUid.split(Configurations.split_string);
		int i = 0, count = problemTypeUids.length;
		for (String string : problemTypeUids) {
			sql += "find_in_set('" + string + "', problemtypeid)";
			i++;
			if (i < count)
				sql += " or ";
		}
		// 日期的时间部分
		sql += ") and preferstart <= " + s_currentTime + " and preferend >= " + s_currentTime;
		// 问题暂未解决
		sql += " and status = " + Configurations.db_problem_status_waiting;
		System.out.println("sql:" + sql);
		//Hibernate 部分
		return session.createSQLQuery(sql).addEntity(Problem.class).list();
/*		return session.createSQLQuery(sql).list();*/
	}
}
