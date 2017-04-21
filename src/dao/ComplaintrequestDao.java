package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Complaintrequest;
import bean.TransferDbData;

public interface ComplaintrequestDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int uid = 0;
	public static final int userUid = 1;
	public static final int createTime = 2;
	public static final int createIp = 3;
	public static final int problemUid = 4;
	public static final int description = 5;
	public static final int resourceUid = 6;
	public static final int status = 7;
	public static final int replyDescription = 8;
	public static final int replyResourceUid = 9;
	public static final int replyCreateUserUid = 10;
	public static final int replyCreateTime = 11;
	public static final int replyCreateIp = 12;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Complaintrequest> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
