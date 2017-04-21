package service;

import bean.TransferResultInfo;
import dao.CreditvaluelogDao;

public interface CreditvaluelogService {
	public static final int insert = CreditvaluelogDao.insert;
	public static final int update = CreditvaluelogDao.update;
	public static final int select = CreditvaluelogDao.select;
	public static final int set_uid = CreditvaluelogDao.uid;
	public static final int set_userUid = CreditvaluelogDao.userUid;
	public static final int set_changeValue = CreditvaluelogDao.changeValue;
	public static final int set_finalValue = CreditvaluelogDao.finalValue;
	public static final int set_reason = CreditvaluelogDao.reason;
	public static final int set_createTime = CreditvaluelogDao.createTime;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<?> find();
}
