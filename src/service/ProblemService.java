package service;

import bean.TransferResultInfo;
import dao.ProblemDao;

public interface ProblemService {
	public static final int find_summary = 0;
	public static final int find_detail = 1;
	public static final int find_applicable = 2;
	
	public static final int insert = ProblemDao.insert;
	public static final int update = ProblemDao.update;
	public static final int select = ProblemDao.select;
	public static final int delete = ProblemDao.delete;
	public static final int select_applicable = ProblemDao.select_applicable;
	public static final int set_uid = ProblemDao.uid;
	public static final int set_createUserUid = ProblemDao.createUserUid;
	public static final int set_locationUid = ProblemDao.locationUid;
	public static final int set_problemTypeUid = ProblemDao.problemTypeUid;
	public static final int set_title = ProblemDao.title;
	public static final int set_preferDay = ProblemDao.preferDay;
	public static final int set_preferStart = ProblemDao.preferStart;
	public static final int set_preferEnd = ProblemDao.preferEnd;
	public static final int set_description = ProblemDao.description;
	public static final int set_resourceUid = ProblemDao.resourceUid;
	public static final int set_createIp = ProblemDao.createIp;
	public static final int set_createTime = ProblemDao.createTime;
	public static final int set_status = ProblemDao.status;
	public static final int set_solvedUid = ProblemDao.solvedUid;
	public static final int set_currentWeekDay = ProblemDao.currentWeekDay;
	public static final int set_currentTime = ProblemDao.currentTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find(int findMode);
}
