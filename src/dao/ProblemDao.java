package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Problem;
import bean.TransferDbData;

public interface ProblemDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int select_applicable = 5;
	public static final int uid = 0;
	public static final int createUserUid = 1;
	public static final int locationUid = 2;
	public static final int problemTypeUid = 3;
	public static final int title = 4;
	public static final int preferDay = 5;
	public static final int preferStart = 6;
	public static final int preferEnd = 7;
	public static final int description = 8;
	public static final int resourceUid = 9;
	public static final int createIp = 10;
	public static final int createTime = 11;
	public static final int status = 12;
	public static final int solvedUid = 13;
	public static final int currentWeekDay = 14;
	public static final int currentTime = 15;
	
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Problem> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract List<Problem> selectApplicable(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
}
