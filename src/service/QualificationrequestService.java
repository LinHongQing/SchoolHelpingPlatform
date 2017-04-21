package service;

import bean.TransferResultInfo;
import dao.QualificationrequestDao;

public interface QualificationrequestService {
	public static final int find_summary = 0;
	public static final int find_detail = 1;
	
	public static final int insert = QualificationrequestDao.insert;
	public static final int update = QualificationrequestDao.update;
	public static final int select = QualificationrequestDao.select;
	public static final int delete = QualificationrequestDao.delete;
	public static final int set_uid = QualificationrequestDao.uid;
	public static final int set_userUid = QualificationrequestDao.userUid;
	public static final int set_typeUid = QualificationrequestDao.typeUid;
	public static final int set_description = QualificationrequestDao.description;
	public static final int set_resourceUid = QualificationrequestDao.resourceUid;
	public static final int set_requestTime = QualificationrequestDao.requestTime;
	public static final int set_requestIp = QualificationrequestDao.requestIp;
	public static final int set_checkingStatus = QualificationrequestDao.checkingStatus;
	public static final int set_checkingType = QualificationrequestDao.checkingType;
	public static final int set_checkingTime = QualificationrequestDao.checkingTime;
	public static final int set_checkingIp = QualificationrequestDao.checkingIp;
	public static final int set_checkingUserUid = QualificationrequestDao.checkingUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find(int findMode);
}
