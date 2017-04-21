package service;

import dao.ProblemtypeDao;
import bean.TransferResultInfo;

public interface ProblemtypeService {
	public static final int insert = ProblemtypeDao.insert;
	public static final int update = ProblemtypeDao.update;
	public static final int delete = ProblemtypeDao.delete;
	public static final int select = ProblemtypeDao.select;
	public static final int set_uid = ProblemtypeDao.uid;
	public static final int set_name = ProblemtypeDao.name;
	public static final int set_createTime = ProblemtypeDao.createTime;
	public static final int set_createIp = ProblemtypeDao.createIp;
	public static final int set_createUserUid = ProblemtypeDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
