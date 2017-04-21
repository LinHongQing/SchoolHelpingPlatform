package service;

import bean.TransferResultInfo;
import dao.UserloginlogDao;

public interface UserloginlogService {
	public static final int insert = UserloginlogDao.insert;
	public static final int update = UserloginlogDao.update;
	public static final int select = UserloginlogDao.select;
	public static final int set_uid = UserloginlogDao.uid;
	public static final int set_userUid = UserloginlogDao.userUid;
	public static final int set_ip = UserloginlogDao.ip;
	public static final int set_loginTime = UserloginlogDao.loginTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> login();
}
