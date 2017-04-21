package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Admin;
import bean.TransferDbData;

public interface AdminDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int uid = 0;
	public static final int email = 1;
	public static final int name = 2;
	public static final int pwd = 3;
	public static final int privilegeUid = 4;
	public static final int createIp = 5;
	public static final int createTime = 6;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Admin> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
