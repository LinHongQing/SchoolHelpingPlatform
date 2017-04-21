package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;
import pojo.Role;

public interface RoleDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int uid = 0;
	public static final int name = 1;
	public static final int roleCode = 2;
	public static final int roleValue = 3;
	public static final int createIp = 4;
	public static final int createUserUid = 5;
	public static final int createTime = 6;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Role> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
