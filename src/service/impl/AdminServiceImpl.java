package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Admin;
import cache.ResultCodeStorage;
import dao.AdminDao;
import exception.HibernateSessionNotInitializedException;
import exception.LoginFailedException;
import exception.MissingParameterException;
import bean.TransferAdminInfo;
import bean.TransferDbData;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferPrivilegeInfo;
import bean.TransferResultInfo;
import service.AdminService;
import util.StringUtil;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;
	private TransferDbData transferDbData;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
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
			adminDao.init();
			adminDao.insert(transferDbData);
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
			adminDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			adminDao.init();
			adminDao.update(transferDbData);
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
			adminDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			adminDao.init();
			adminDao.delete(transferDbData);
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
		} finally {
			adminDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find() {
		// TODO Auto-generated method stub
		try {
			adminDao.init();
			List<Admin> list_admin = adminDao.select(transferDbData);
			List<TransferAdminInfo> finalList = new ArrayList<TransferAdminInfo>();
			for (Admin admin : list_admin) {
				TransferAdminInfo a = new TransferAdminInfo();
				a.setUid(admin.getUid());
				a.setEmail(admin.getEmail());
				a.setName(admin.getName());
				TransferPrivilegeInfo privilege = new TransferPrivilegeInfo();
				privilege.setUid(admin.getPrivilege().getUid());
				privilege.setName(admin.getPrivilege().getName());
				a.setPrivilege(privilege);
				a.setCreatetime(admin.getCreatetime());
				a.setCreateip(admin.getCreateip());
				finalList.add(a);
			}
			TransferResultInfo<List<TransferAdminInfo>> rs = new TransferResultInfo<List<TransferAdminInfo>>();
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
			adminDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> changepwd() {
		// TODO Auto-generated method stub
		try {
			adminDao.init();
			String old_pwd = transferDbData.getValues().get(set_pwd_old);
			String new_pwd = transferDbData.getValues().get(set_pwd);
			transferDbData.insertValues(set_pwd, old_pwd);
			List<Admin> admins = adminDao.select(transferDbData);
			for (Admin admin : admins) {
				if (admin.getPwd().equals(old_pwd)) {
					transferDbData.insertValues(set_pwd, new_pwd);
					adminDao.update(transferDbData);
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_success);
					rs.setMsgCode(ResultCodeStorage.code_success);
					rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
					return rs;
				}
				else {
					TransferResultInfo<String> rs = new TransferResultInfo<String>();
					rs.setMsgType(ResultCodeStorage.type_error);
					rs.setMsgCode(ResultCodeStorage.code_err_invalid_password);
					rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_err_invalid_password));
					return rs;
				}
			}
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_invalid_password);
			rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_err_invalid_password));
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
			adminDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> login() {
		// TODO Auto-generated method stub
		try {
			adminDao.init();
			List<Admin> admins = adminDao.select(transferDbData);
			if (admins.size() != 0) {
				TransferOnlineUserBasicInfo u = new TransferOnlineUserBasicInfo();
				TransferAdminInfo content = new TransferAdminInfo();
				for (Admin admin : admins) {
					content.setUid(admin.getUid());
					content.setEmail(admin.getEmail());
					content.setName(admin.getName());
					if (admin.getPrivilege() != null) {
						TransferPrivilegeInfo privilege = new TransferPrivilegeInfo();
						privilege.setUid(admin.getPrivilege().getUid());
						privilege.setName(admin.getPrivilege().getName());
						privilege.setPrivilegecode(admin.getPrivilege().getPrivilegecode());
						privilege.setPrivilegevalue(admin.getPrivilege().getPrivilegevalue());
						content.setPrivilege(privilege);
					}
					u.setAdmin(content);
					break;
				}
				TransferResultInfo<TransferOnlineUserBasicInfo> rs = new TransferResultInfo<TransferOnlineUserBasicInfo>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(u);
				return rs;
			} else {
				throw new LoginFailedException("用户名或密码错误");
			}
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
		} catch (LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_login_failed);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_login_failed, e.getMessage()));
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
			adminDao.close();
		}
	}

}
