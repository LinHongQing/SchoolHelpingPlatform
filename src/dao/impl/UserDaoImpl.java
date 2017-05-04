package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cache.Configurations;
import bean.TransferDbData;
import pojo.Academy;
import pojo.Course;
import pojo.Grade;
import pojo.Location;
import pojo.Qualificationtype;
import pojo.Role;
import pojo.User;
import dao.UserDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	Session session;

	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT u FROM User AS u WHERE u.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND u.uid = ?";
				break;
			case name:
				preHql += " AND u.name = ?";
				break;
			case num:
				preHql += " AND u.num = ?";
				break;
			case nickname:
				preHql += " AND u.nickname = ?";
				break;
			case pwd:
				preHql += " AND u.pwd = ?";
				break;
			case resourceUid:
				preHql += " AND u.resourceid = ?";
				break;
			case academyUid:
				preHql += " AND u.academy.uid = ?";
				break;
			case gradeUid:
				preHql += " AND u.grade.uid = ?";
				break;
			case courseUid:
				preHql += " AND u.course.uid = ?";
				break;
			case locationUid:
				preHql += " AND u.location.uid = ?";
				break;
			case roleUid:
				preHql += " AND u.role.uid = ?";
				break;
			case qualTypeUid:
				preHql += " AND u.qualificationtype.uid = ?";
				break;
			case creditValue:
				preHql += " AND u.creditvalue >= ? AND u.creditvalue <= ?";
				break;
			case createTime:
				preHql += " AND u.createtime >= ? AND u.createtime <= ?";
				break;
			case createIp:
				preHql += " AND u.createip = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<User> hibernateListProcess(TransferDbData transferDbData)
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
			case num:
			case pwd:
			case nickname:
			case resourceUid:
			case academyUid:
			case gradeUid:
			case courseUid:
			case locationUid:
			case roleUid:
			case qualTypeUid:
			case createIp:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case creditValue:
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
		List<User> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		User user = new User();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				user.setUid(value);
				break;
			case name:
				user.setName(value);
				break;
			case num:
				user.setNum(value);
				break;
			case pwd:
				user.setPwd(value);
				break;
			case nickname:
				user.setNickname(value);
				break;
			case resourceUid:
				user.setResourceid(value);
				break;
			case academyUid:
				Academy academy = new Academy();
				academy.setUid(value);
				user.setAcademy(academy);
				break;
			case gradeUid:
				Grade grade = new Grade();
				grade.setUid(value);
				user.setGrade(grade);
				break;
			case courseUid:
				Course course = new Course();
				course.setUid(value);
				user.setCourse(course);
				break;
			case locationUid:
				Location location = new Location();
				location.setUid(value);
				user.setLocation(location);
				break;
			case roleUid:
				Role role = new Role();
				role.setUid(value);
				user.setRole(role);
				break;
			case qualTypeUid:
				Qualificationtype qualType = new Qualificationtype();
				qualType.setUid(value);
				user.setQualificationtype(qualType);
				break;
			case createIp:
				user.setCreateip(value);
				break;
			case creditValue:
				user.setCreditvalue(Integer.valueOf(value));
				break;
			case createTime:
				user.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}
	
	private void hibernateUpdateProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		String s_uid = transferDbData.getValues().get(uid);
		if (s_uid == null)
			throw new MissingParameterException("uid 不能为空");
		session.beginTransaction();
		User user = (User) session.get(User.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			if (value == null || "".equals(value))
				continue;
			switch(key) {
			case name:
				user.setName(value);
				break;
			case num:
				user.setNum(value);
				break;
			case pwd:
				user.setPwd(value);
				return;
			case nickname:
				user.setNickname(value);
				break;
			case resourceUid:
				user.setResourceid(value);
				break;
			case academyUid:
				Academy academy = new Academy();
				academy.setUid(value);
				user.setAcademy(academy);
				break;
			case gradeUid:
				Grade grade = new Grade();
				grade.setUid(value);
				user.setGrade(grade);
				break;
			case courseUid:
				Course course = new Course();
				course.setUid(value);
				user.setCourse(course);
				break;
			case locationUid:
				Location location = new Location();
				location.setUid(value);
				user.setLocation(location);
				break;
			case roleUid:
				Role role = new Role();
				role.setUid(value);
				user.setRole(role);
				break;
			case qualTypeUid:
				Qualificationtype qualType = new Qualificationtype();
				qualType.setUid(value);
				user.setQualificationtype(qualType);
				break;
			case createIp:
				user.setCreateip(value);
				break;
			case creditValue:
				user.setCreditvalue(Integer.valueOf(value));
				break;
			case createTime:
				user.setCreatetime(Integer.valueOf(value));
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(user);
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
		User user = (User) session.get(User.class, s_uid);
		user.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}
	
	public void init() throws HibernateException {
		session = getHibernateTemplate().getSessionFactory().openSession();
	}

	@Override
	public void close() throws HibernateException {
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
		throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException {
		// TODO Auto-generated method stub
		hibernateUpdateProcess(transferDbData);
	}

	@Override
	public void delete(TransferDbData transferDbData)
		throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException {
		// TODO Auto-generated method stub
		hibernateDeleteProcess(transferDbData);
	}
	
	@Override
	public List<User> select(TransferDbData transferDbData)
		throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

}
