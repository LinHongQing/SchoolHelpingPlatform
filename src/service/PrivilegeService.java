package service;

import bean.TransferResultInfo;
import dao.PrivilegeDao;

public interface PrivilegeService {
	public static final int insert = PrivilegeDao.insert;
	public static final int update = PrivilegeDao.update;
	public static final int delete = PrivilegeDao.delete;
	public static final int select = PrivilegeDao.select;
	public static final int set_uid = PrivilegeDao.uid;
	public static final int set_name = PrivilegeDao.name;
	public static final int set_privilegeCode = PrivilegeDao.privilegeCode;
	public static final int set_privilegeValue = PrivilegeDao.privilegeValue;
	public static final int set_createTime = PrivilegeDao.createTime;
	public static final int set_createIp = PrivilegeDao.createIp;
	public static final int set_createUserUid = PrivilegeDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
