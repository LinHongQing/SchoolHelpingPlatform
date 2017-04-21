package service;


import dao.UserDao;
import bean.TransferResultInfo;

public interface UserService {
	public static final int findMode_summary = 0;
	public static final int findMode_detail = 1;
	public static final int insert = UserDao.insert;
	public static final int update = UserDao.update;
	public static final int delete = UserDao.delete;
	public static final int select = UserDao.select;
	public static final int changePwd_admin = -1;
	public static final int changePwd_user = -2;
	public static final int login = -3;
	public static final int logout = -4;
	public static final int set_uid = UserDao.uid;
	public static final int set_name = UserDao.name;
	public static final int set_num = UserDao.num;
	public static final int set_pwd = UserDao.pwd;
	public static final int set_pwd_old = UserDao.pwd + 100;
	public static final int set_nickname = UserDao.nickname;
	public static final int set_resourceUid = UserDao.resourceUid;
	public static final int set_academyUid = UserDao.academyUid;
	public static final int set_gradeUid = UserDao.gradeUid;
	public static final int set_courseUid = UserDao.courseUid;
	public static final int set_locationUid = UserDao.locationUid;
	public static final int set_roleUid = UserDao.roleUid;
	public static final int set_qualTypeUid = UserDao.qualTypeUid;
	public static final int set_creditValue = UserDao.creditValue;
	public static final int set_createTime = UserDao.createTime;
	public static final int set_createIp = UserDao.createIp;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find(int findMode);
	public abstract TransferResultInfo<String> changepwd(int changeMode);
	public abstract TransferResultInfo<?> login();
}
