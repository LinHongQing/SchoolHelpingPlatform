package service;

import bean.TransferResultInfo;
import dao.SolveDao;

public interface SolveService {
	public static final int insert = SolveDao.insert;
	public static final int update = SolveDao.update;
	public static final int select = SolveDao.select;
	public static final int delete = SolveDao.delete;
	public static final int set_uid = SolveDao.uid;
	public static final int set_problemUid = SolveDao.problemUid;
	public static final int set_createUserUid = SolveDao.createUserUid;
	public static final int set_assistantUid = SolveDao.assistantUid;
	public static final int set_description = SolveDao.description;
	public static final int set_createIp = SolveDao.createIp;
	public static final int set_createTime = SolveDao.createTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
}
