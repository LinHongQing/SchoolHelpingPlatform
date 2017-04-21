package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;
import pojo.User;

public interface UserDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int uid = 0;
	public static final int name = 1;
	public static final int num = 2;
	public static final int pwd = 3;
	public static final int nickname = 4;
	public static final int resourceUid = 5;
	public static final int academyUid = 6;
	public static final int gradeUid = 7;
	public static final int courseUid = 8;
	public static final int locationUid = 9;
	public static final int roleUid = 10;
	public static final int qualTypeUid = 11;
	public static final int creditValue = 12;
	public static final int createTime = 13;
	public static final int createIp = 14;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<User> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
