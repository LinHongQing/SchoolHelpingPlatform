package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Academy;
import pojo.Admin;
import pojo.Course;
import cache.Configurations;
import bean.TransferDbData;
import dao.CourseDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT c FROM Course AS c WHERE c.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND c.uid = ?";
				break;
			case name:
				preHql += " AND c.name = ?";
				break;
			case academyUid:
				preHql += " AND c.academy.uid = ?";
				break;
			case createIp:
				preHql += " AND c.createip = ?";
				break;
			case createTime:
				preHql += " AND c.createtime >= ? AND c.createtime <= ?";
				break;
			case createUserUid:
				preHql += " AND c.admin.id = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Course> hibernateListProcess(TransferDbData transferDbData)
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
			case academyUid:
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
		List<Course> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Course course = new Course();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				course.setUid(value);
				break;
			case name:
				course.setName(value);
				break;
			case academyUid:
				Academy academy = new Academy();
				academy.setUid(value);
				course.setAcademy(academy);
				break;
			case createIp:
				course.setCreateip(value);
				break;
			case createTime:
				course.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				course.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(course);
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
		Course course = (Course) session.get(Course.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				course.setName(value);
				break;
			case academyUid:
				Academy academy = new Academy();
				academy.setUid(value);
				course.setAcademy(academy);
				break;
			case createIp:
				course.setCreateip(value);
				break;
			case createTime:
				course.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				course.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(course);
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
		Course course = (Course) session.get(Course.class, s_uid);
		course.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(course);
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
	public List<Course> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

}
