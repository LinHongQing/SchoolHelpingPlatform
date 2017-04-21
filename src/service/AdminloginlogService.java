package service;

import bean.TransferResultInfo;
import dao.AdminloginlogDao;

public interface AdminloginlogService {
	public static final int insert = AdminloginlogDao.insert;
	public static final int update = AdminloginlogDao.update;
	public static final int select = AdminloginlogDao.select;
	public static final int set_uid = AdminloginlogDao.uid;
	public static final int set_adminUid = AdminloginlogDao.adminUid;
	public static final int set_ip = AdminloginlogDao.ip;
	public static final int set_loginTime = AdminloginlogDao.loginTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> login();
}
