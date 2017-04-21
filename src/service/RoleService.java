package service;

import bean.TransferResultInfo;
import dao.RoleDao;

public interface RoleService {
	public static final int insert = RoleDao.insert;
	public static final int update = RoleDao.update;
	public static final int delete = RoleDao.delete;
	public static final int select = RoleDao.select;
	public static final int set_uid = RoleDao.uid;
	public static final int set_name = RoleDao.name;
	public static final int set_roleCode = RoleDao.roleCode;
	public static final int set_roleValue = RoleDao.roleValue;
	public static final int set_createTime = RoleDao.createTime;
	public static final int set_createIp = RoleDao.createIp;
	public static final int set_createUserUid = RoleDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
