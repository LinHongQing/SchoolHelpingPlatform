package service;

import bean.TransferResultInfo;
import dao.LocationDao;

public interface LocationService {
	public static final int insert = LocationDao.insert;
	public static final int update = LocationDao.update;
	public static final int delete = LocationDao.delete;
	public static final int select = LocationDao.select;
	public static final int set_uid = LocationDao.uid;
	public static final int set_name = LocationDao.name;
	public static final int set_longitude = LocationDao.longitude;
	public static final int set_latitude = LocationDao.latitude;
	public static final int set_createTime = LocationDao.createTime;
	public static final int set_createIp = LocationDao.createIp;
	public static final int set_createUserUid = LocationDao.createUserUid;
	public abstract void initParameters();
	public abstract void setParameters(int set, String value);
	public abstract TransferResultInfo<String> insert();
	public abstract TransferResultInfo<String> update();
	public abstract TransferResultInfo<String> delete();
	public abstract TransferResultInfo<?> find();
	public abstract TransferResultInfo<?> init();
}
