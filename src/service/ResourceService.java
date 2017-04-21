package service;

import bean.TransferResultInfo;
import dao.ResourceDao;

public interface ResourceService {
	public static final int insert = ResourceDao.insert;
	public static final int update = ResourceDao.update;
	public static final int delete = ResourceDao.delete;
	public static final int select = ResourceDao.select;
	public static final int set_uid = ResourceDao.uid;
	public static final int set_name = ResourceDao.name;
	public static final int set_type = ResourceDao.type;
	public static final int set_value = ResourceDao.value;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
}
