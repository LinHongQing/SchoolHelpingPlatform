package service;

import bean.TransferResultInfo;
import dao.TermDao;

public interface TermService {
	public static final int insert = TermDao.insert;
	public static final int update = TermDao.update;
	public static final int delete = TermDao.delete;
	public static final int select = TermDao.select;
	public static final int set_uid = TermDao.uid;
	public static final int set_name = TermDao.name;
	public static final int set_startTime = TermDao.startTime;
	public static final int set_endTime = TermDao.endTime;
	public static final int set_createTime = TermDao.createTime;
	public static final int set_createIp = TermDao.createIp;
	public static final int set_createUserUid = TermDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
