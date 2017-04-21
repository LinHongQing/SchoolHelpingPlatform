package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Userloginlog;
import cache.Configurations;
import cache.ResultCodeStorage;
import dao.UserloginlogDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;
import bean.TransferLoginlogInfo;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferResultInfo;
import bean.TransferUserInfo;
import service.UserloginlogService;
import util.StringUtil;

public class UserloginlogServiceImpl implements UserloginlogService {
	
	private UserloginlogDao userloginlogDao;
	private TransferDbData transferDbData;

	public UserloginlogDao getUserloginlogDao() {
		return userloginlogDao;
	}

	public void setUserloginlogDao(UserloginlogDao userloginlogDao) {
		this.userloginlogDao = userloginlogDao;
	}

	@Override
	public void initParameters() {
		// TODO Auto-generated method stub
		transferDbData = new TransferDbData();
	}

	@Override
	public void setParameters(int set, String value) {
		// TODO Auto-generated method stub
		transferDbData.insertValues(set, value);
	}

	@Override
	public TransferResultInfo<String> insert() {
		// TODO Auto-generated method stub
		try {
			userloginlogDao.init();
			userloginlogDao.insert(transferDbData);
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_success);
			rs.setMsgCode(ResultCodeStorage.code_success);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.type_success, null));
			return rs;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_dao_hibernate_save);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_dao_hibernate_save, e.getMessage()));
			return rs;
		} catch (HibernateSessionNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_session_hibernate_empty);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_session_hibernate_empty, e.getMessage()));
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userloginlogDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			userloginlogDao.init();
			userloginlogDao.update(transferDbData);
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_success);
			rs.setMsgCode(ResultCodeStorage.code_success);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.type_success, null));
			return rs;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_dao_hibernate_update);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_dao_hibernate_update, e.getMessage()));
			return rs;
		} catch (HibernateSessionNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_session_hibernate_empty);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_session_hibernate_empty, e.getMessage()));
			return rs;
		} catch (MissingParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, e.getMessage()));
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userloginlogDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find() {
		// TODO Auto-generated method stub
		try {
			userloginlogDao.init();
			List<Userloginlog> list_loginLog = userloginlogDao.select(transferDbData);
			List<TransferLoginlogInfo> finalList = new ArrayList<TransferLoginlogInfo>();
			for (Userloginlog loginLog : list_loginLog) {
				TransferLoginlogInfo l = new TransferLoginlogInfo();
				l.setUid(loginLog.getUid());
				if (loginLog.getUser() != null) {
					TransferUserInfo user = new TransferUserInfo();
					user.setUid(loginLog.getUser().getUid());
					user.setName(loginLog.getUser().getName());
					l.setUser(user);
				}
				l.setIp(loginLog.getIp());
				l.setLogintime(loginLog.getLogintime());
				finalList.add(l);
			}
			TransferResultInfo<List<TransferLoginlogInfo>> rs = new TransferResultInfo<List<TransferLoginlogInfo>>();
			rs.setMsgType(ResultCodeStorage.type_success);
			rs.setMsgCode(ResultCodeStorage.code_success);
			rs.setMsgContent(finalList);
			return rs;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_dao_hibernate_query);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_dao_hibernate_query, e.getMessage()));
			return rs;
		} catch (HibernateSessionNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_session_hibernate_empty);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_session_hibernate_empty, e.getMessage()));
			return rs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userloginlogDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> login() {
		// TODO Auto-generated method stub
		try {
			userloginlogDao.init();
			if (transferDbData.getValues().get(UserloginlogService.set_userUid) == null)
				throw new MissingParameterException("useruid 参数为空");
			List<Userloginlog> list_loginLog = userloginlogDao.select(transferDbData);
			TransferOnlineUserBasicInfo fin = new TransferOnlineUserBasicInfo();
			for (Userloginlog loginLog : list_loginLog) {
				fin.setUserlastloginip(loginLog.getIp());
				fin.setUserlastlogintime(loginLog.getLogintime());
				break;
			}
			if (list_loginLog.size() == 0) {
				fin.setUserlastlogintime(Configurations.invalid_int);
			}
			fin.setUserlogincount(list_loginLog.size() + 1);
			TransferResultInfo<TransferOnlineUserBasicInfo> rs = new TransferResultInfo<TransferOnlineUserBasicInfo>();
			rs.setMsgType(ResultCodeStorage.type_success);
			rs.setMsgCode(ResultCodeStorage.code_success);
			rs.setMsgContent(fin);
			return rs;
		} catch (MissingParameterException e) {
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_missing_parameter);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_missing_parameter, e.getMessage()));
			return rs;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_dao_hibernate_query);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_dao_hibernate_query, e.getMessage()));
			return rs;
		} catch (HibernateSessionNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_session_hibernate_empty);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_session_hibernate_empty, e.getMessage()));
			return rs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userloginlogDao.close();
		}
	}

}
