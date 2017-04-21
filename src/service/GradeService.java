package service;

import bean.TransferResultInfo;
import dao.GradeDao;

public interface GradeService {
	public static final int insert = GradeDao.insert;
	public static final int update = GradeDao.update;
	public static final int delete = GradeDao.delete;
	public static final int select = GradeDao.select;
	public static final int set_uid = GradeDao.uid;
	public static final int set_name = GradeDao.name;
	public static final int set_admissionTime = GradeDao.admissionTime;
	public static final int set_graduationTime = GradeDao.graduationTime;
	public static final int set_createTime = GradeDao.createTime;
	public static final int set_createIp = GradeDao.createIp;
	public static final int set_createUserUid = GradeDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
