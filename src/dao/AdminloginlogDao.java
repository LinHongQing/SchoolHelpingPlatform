package dao;

import java.util.List;

import org.hibernate.HibernateException;

import pojo.Adminloginlog;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;

public interface AdminloginlogDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int uid = 0;
	public static final int adminUid = 1;
	public static final int ip = 2;
	public static final int loginTime = 3;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Adminloginlog> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
