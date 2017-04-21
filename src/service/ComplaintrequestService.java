package service;

import bean.TransferResultInfo;
import dao.ComplaintrequestDao;

public interface ComplaintrequestService {
	public static final int find_summary = 0;
	public static final int find_detail = 1;
	
	public static final int insert = ComplaintrequestDao.insert;
	public static final int update = ComplaintrequestDao.update;
	public static final int select = ComplaintrequestDao.select;
	public static final int set_uid = ComplaintrequestDao.uid;
	public static final int set_userUid = ComplaintrequestDao.userUid;
	public static final int set_problemUid = ComplaintrequestDao.problemUid;
	public static final int set_createTime = ComplaintrequestDao.createTime;
	public static final int set_createIp = ComplaintrequestDao.createIp;
	public static final int set_description = ComplaintrequestDao.description;
	public static final int set_resourceUid = ComplaintrequestDao.resourceUid;
	public static final int set_status = ComplaintrequestDao.status;
	public static final int set_replyDescription = ComplaintrequestDao.replyDescription;
	public static final int set_replyResourceUid = ComplaintrequestDao.replyResourceUid;
	public static final int set_replyCreateUserUid = ComplaintrequestDao.replyCreateUserUid;
	public static final int set_replyCreateIp = ComplaintrequestDao.replyCreateIp;
	public static final int set_replyCreateTime = ComplaintrequestDao.replyCreateTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<?> find(int findMode);
}
