package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Qualificationrequest;
import bean.TransferDbData;

public interface QualificationrequestDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int delete = 4;
	public static final int uid = 0;
	public static final int userUid = 1;
	public static final int typeUid = 2;
	public static final int description = 3;
	public static final int resourceUid = 4;
	public static final int requestTime = 5;
	public static final int requestIp = 6;
	public static final int checkingStatus = 7;
	public static final int checkingType = 8;
	public static final int checkingTime = 9;
	public static final int checkingIp = 10;
	public static final int checkingUserUid = 11;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract void delete(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Qualificationrequest> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
