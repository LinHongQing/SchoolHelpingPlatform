package service;

import dao.AcademyDao;
import bean.TransferResultInfo;

public interface AcademyService {
	public static final int insert = AcademyDao.insert;
	public static final int update = AcademyDao.update;
	public static final int delete = AcademyDao.delete;
	public static final int select = AcademyDao.select;
	public static final int set_uid = AcademyDao.uid;
	public static final int set_name = AcademyDao.name;
	public static final int set_createTime = AcademyDao.createTime;
	public static final int set_createIp = AcademyDao.createIp;
	public static final int set_createUserUid = AcademyDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	
	public abstract TransferResultInfo<?> init();
}
