package service;

import bean.TransferResultInfo;
import dao.AdminDao;

public interface AdminService {
	public static final int insert = AdminDao.insert;
	public static final int update = AdminDao.update;
	public static final int delete = AdminDao.delete;
	public static final int select = AdminDao.select;
	public static final int changePwd = -1;
	public static final int login = -2;
	public static final int logout = -3;
	public static final int set_uid = AdminDao.uid;
	public static final int set_email = AdminDao.email;
	public static final int set_name = AdminDao.name;
	public static final int set_pwd = AdminDao.pwd;
	public static final int set_pwd_old = AdminDao.pwd + 100;
	public static final int set_privilegeUid = AdminDao.privilegeUid;
	public static final int set_createTime = AdminDao.createTime;
	public static final int set_createIp = AdminDao.createIp;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<String> changepwd();
	public abstract TransferResultInfo<?> login();
}
