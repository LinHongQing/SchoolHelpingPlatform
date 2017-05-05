package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.Admin;
import pojo.Location;
import cache.Configurations;
import bean.TransferDbData;
import dao.LocationDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {
	Session session;
	
	private static String getHql(TransferDbData transferDbData) {
		String preHql = "SELECT l FROM Location AS l WHERE l.isvalid = " + Configurations.db_entry_valid;
		for (Integer key : transferDbData.getValues().keySet()) {
			switch(key) {
			case uid:
				preHql += " AND l.uid = ?";
				break;
			case name:
				preHql += " AND l.name LIKE ?";
				break;
			case longitude:
				preHql += " AND l.longitude >= ? AND l.longitude <= ?";
				break;
			case latitude:
				preHql += " AND l.latitude >= ? AND l.latitude <= ?";
				break;
			case createIp:
				preHql += " AND l.createip = ?";
				break;
			case createTime:
				preHql += " AND l.createtime >= ? AND l.createtime <= ?";
				break;
			case createUserUid:
				preHql += " AND l.admin.uid = ?";
				break;
			}
		}
		return preHql;
	}
	
	@SuppressWarnings("unchecked")
	private List<Location> hibernateListProcess(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		String hql = getHql(transferDbData);
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Query query = session.createQuery(hql);
		int currentSet = 0;
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				query.setString(currentSet, "%" + value + "%");
				currentSet++;
				break;
			case uid:
			case createIp:
			case createUserUid:
				query.setString(currentSet, value);
				currentSet++;
				break;
			case longitude:
			case latitude:
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
		List<Location> list = query.list();
		return list;
	}
	
	private void hibernateSaveProcess(TransferDbData transferDbData)
			throws NumberFormatException, HibernateSessionNotInitializedException, NumberFormatException {
		if (session == null)
			throw new HibernateSessionNotInitializedException("Hibernate Session 未被初始化!");
		Location location = new Location();
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case uid:
				location.setUid(value);
				break;
			case name:
				location.setName(value);
				break;
			case longitude:
				location.setLongitude(Float.valueOf(value));
				break;
			case latitude:
				location.setLatitude(Float.valueOf(value));
				break;
			case createIp:
				location.setCreateip(value);
				break;
			case createTime:
				location.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				location.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.save(location);
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
		Location location = (Location) session.get(Location.class, s_uid);
		for (Integer key : transferDbData.getValues().keySet()) {
			String value = transferDbData.getValues().get(key);
			switch(key) {
			case name:
				location.setName(value);
				break;
			case longitude:
				location.setLongitude(Float.valueOf(value));
				break;
			case latitude:
				location.setLatitude(Float.valueOf(value));
				break;
			case createIp:
				location.setCreateip(value);
				break;
			case createTime:
				location.setCreatetime(Integer.valueOf(value));
				break;
			case createUserUid:
				Admin admin = new Admin();
				admin.setUid(value);
				location.setAdmin(admin);
				break;
			}
		}
		Transaction tx = session.beginTransaction();
		session.update(location);
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
		Location location = (Location) session.get(Location.class, s_uid);
		location.setIsvalid(Configurations.db_entry_invalid);
		Transaction tx = session.beginTransaction();
		session.save(location);
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
	public List<Location> select(TransferDbData transferDbData)
			throws HibernateException, HibernateSessionNotInitializedException {
		// TODO Auto-generated method stub
		return hibernateListProcess(transferDbData);
	}

}
