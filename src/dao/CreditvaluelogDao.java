package dao;

import java.util.List;

import org.hibernate.HibernateException;

import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import pojo.Creditvaluelog;
import bean.TransferDbData;

public interface CreditvaluelogDao {
	public static final int insert = 1;
	public static final int update = 2;
	public static final int select = 3;
	public static final int uid = 0;
	public static final int userUid = 1;
	public static final int changeValue = 2;
	public static final int finalValue = 3;
	public static final int createTime = 4;
	public static final int reason = 5;
	public abstract void init() throws HibernateException;
	public abstract void close() throws HibernateException;
	public abstract void insert(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
	public abstract void update(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException, MissingParameterException;
	public abstract List<Creditvaluelog> select(TransferDbData transferDbData) throws HibernateException, HibernateSessionNotInitializedException;
}
