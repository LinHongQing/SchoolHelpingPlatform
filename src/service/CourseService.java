package service;

import bean.TransferResultInfo;
import dao.CourseDao;

public interface CourseService {
	public static final int insert = CourseDao.insert;
	public static final int update = CourseDao.update;
	public static final int delete = CourseDao.delete;
	public static final int select = CourseDao.select;
	public static final int set_uid = CourseDao.uid;
	public static final int set_name = CourseDao.name;
	public static final int set_academyUid = CourseDao.academyUid;
	public static final int set_createTime = CourseDao.createTime;
	public static final int set_createIp = CourseDao.createIp;
	public static final int set_createUserUid = CourseDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
