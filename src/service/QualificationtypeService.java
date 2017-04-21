package service;

import dao.QualificationtypeDao;
import bean.TransferResultInfo;

public interface QualificationtypeService {
	public static final int insert = QualificationtypeDao.insert;
	public static final int update = QualificationtypeDao.update;
	public static final int delete = QualificationtypeDao.delete;
	public static final int select = QualificationtypeDao.select;
	public static final int set_uid = QualificationtypeDao.uid;
	public static final int set_name = QualificationtypeDao.name;
	public static final int set_problemTypeUid = QualificationtypeDao.problemTypeUid;
	public static final int set_createTime = QualificationtypeDao.createTime;
	public static final int set_createIp = QualificationtypeDao.createIp;
	public static final int set_createUserUid = QualificationtypeDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
