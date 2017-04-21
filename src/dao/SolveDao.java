package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Solve;
import bean.TransferDbData;

public interface SolveDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int uid = 0;
	public static final int problemUid = 1;
	public static final int createUserUid = 2;
	public static final int assistantUid = 3;
	public static final int description = 4;
	public static final int createTime = 5;
	public static final int createIp = 6;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Solve> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
