package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import cache.ResultCodeStorage;
import dao.UserDao;
import exception.HibernateSessionNotInitializedException;
import exception.LoginFailedException;
import exception.MissingParameterException;
import bean.TransferAcademyInfo;
import bean.TransferCourseInfo;
import bean.TransferDbData;
import bean.TransferGradeInfo;
import bean.TransferLocationInfo;
import bean.TransferOnlineUserBasicInfo;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import bean.TransferRoleInfo;
import bean.TransferUserInfo;
import pojo.User;
import service.UserService;
import util.StringUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private TransferDbData transferDbData;
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void initParameters() {
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
			userDao.init();
			userDao.insert(transferDbData);
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
			userDao.close();
		}
		
	}
	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			userDao.init();
			userDao.update(transferDbData);
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
			userDao.close();
		}
	}
	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			userDao.init();
			userDao.delete(transferDbData);
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
			userDao.close();
		}
	}
	
	@Override
	public TransferResultInfo<?> find(int findMode) {
		// TODO Auto-generated method stub
		try {
			userDao.init();
			List<User> list_user = userDao.select(transferDbData);
			switch (findMode) {
			case findMode_summary: {
				List<TransferUserInfo> finalList = new ArrayList<TransferUserInfo>();
				for (User user : list_user) {
					TransferUserInfo u = new TransferUserInfo();
					u.setUid(user.getUid());
					u.setName(user.getName());
					u.setNum(user.getNum());
					if (user.getGrade() != null) {
						TransferGradeInfo grade = new TransferGradeInfo();
						grade.setUid(user.getGrade().getUid());
						grade.setName(user.getGrade().getName());
						u.setGrade(grade);
					}
					if (user.getAcademy() != null) {
						TransferAcademyInfo academy = new TransferAcademyInfo();
						academy.setUid(user.getAcademy().getUid());
						academy.setName(user.getAcademy().getName());
						u.setAcademy(academy);
					}
					if (user.getCourse() != null) {
						TransferCourseInfo course = new TransferCourseInfo();
						course.setUid(user.getCourse().getUid());
						course.setName(user.getCourse().getName());
						u.setCourse(course);
					}
					u.setCreditvalue(user.getCreditvalue());
					finalList.add(u);
				}
				TransferResultInfo<List<TransferUserInfo>> rs = new TransferResultInfo<List<TransferUserInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			case findMode_detail: {
				List<TransferUserInfo> finalList = new ArrayList<TransferUserInfo>();
				for (User user : list_user) {
					TransferUserInfo u = new TransferUserInfo();
					u.setUid(user.getUid());
					u.setName(user.getName());
					u.setNum(user.getNum());
					u.setNickname(user.getNickname());
					if (user.getLocation() != null) {
					TransferLocationInfo location = new TransferLocationInfo();
					location.setUid(user.getLocation().getUid());
					location.setName(user.getLocation().getName());
					u.setLocation(location);
					}
					if (user.getGrade() != null) {
						TransferGradeInfo grade = new TransferGradeInfo();
						grade.setUid(user.getGrade().getUid());
						grade.setName(user.getGrade().getName());
						u.setGrade(grade);
					}
					if (user.getAcademy() != null) {
						TransferAcademyInfo academy = new TransferAcademyInfo();
						academy.setUid(user.getAcademy().getUid());
						academy.setName(user.getAcademy().getName());
						u.setAcademy(academy);
					}
					if (user.getCourse() != null) {
						TransferCourseInfo course = new TransferCourseInfo();
						course.setUid(user.getCourse().getUid());
						course.setName(user.getCourse().getName());
						u.setCourse(course);
					}
					if (user.getRole() != null) {
						TransferRoleInfo role = new TransferRoleInfo();
						role.setUid(user.getRole().getUid());
						role.setName(user.getRole().getName());
						u.setRole(role);
					}
					if (user.getQualificationtype() != null) {
						TransferQualificationtypeInfo qualType = new TransferQualificationtypeInfo();
						qualType.setUid(user.getQualificationtype().getUid());
						qualType.setName(user.getQualificationtype().getName());
						u.setQualtype(qualType);
					}
					u.setCreditvalue(user.getCreditvalue());
					u.setCreatetime(user.getCreatetime());
					u.setCreateip(user.getCreateip());
					finalList.add(u);
				}
				TransferResultInfo<List<TransferUserInfo>> rs = new TransferResultInfo<List<TransferUserInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			default: {
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_error);
				rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
				rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, "参数无效"));
				return rs;
			}
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> changepwd(int changeMode) {
		// TODO Auto-generated method stub
		try {
			userDao.init();
			String old_pwd = transferDbData.getValues().get(set_pwd_old);
			String new_pwd = transferDbData.getValues().get(set_pwd);
			switch(changeMode) {
			case changePwd_admin: {
				transferDbData.insertValues(set_pwd, new_pwd);
				userDao.update(transferDbData);
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_success));
				return rs;
			}
			case changePwd_user:
			default: {
				transferDbData.insertValues(set_pwd, old_pwd);
				List<User> users = userDao.select(transferDbData);
				for (User user : users) {
					if (user.getPwd().equals(transferDbData.getValues().get(set_pwd_old))) {
						transferDbData.insertValues(set_pwd, new_pwd);
						userDao.update(transferDbData);
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
				rs.setMsgCode(ResultCodeStorage.code_err_invalid_uid);
				rs.setMsgContent(ResultCodeStorage.getResultCodeDescription(ResultCodeStorage.code_err_invalid_uid));
				return rs;
			}
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransferResultInfo<String> rs = new TransferResultInfo<String>();
			rs.setMsgType(ResultCodeStorage.type_error);
			rs.setMsgCode(ResultCodeStorage.code_err_generic_server_internal_exception);
			rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_generic_server_internal_exception, e.getMessage()));
			return rs;
		} finally {
			userDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> login() {
		// TODO Auto-generated method stub
		try {
			userDao.init();
			List<User> users = userDao.select(transferDbData);
			TransferOnlineUserBasicInfo u = new TransferOnlineUserBasicInfo();
			TransferUserInfo content = new TransferUserInfo();
			if (users.size() != 0) {
				for (User user : users) {
					content.setUid(user.getUid());
					content.setNum(user.getNum());
					content.setName(user.getName());
					content.setNickname(user.getNickname());
					if (user.getGrade() != null) {
						TransferGradeInfo grade = new TransferGradeInfo();
						grade.setUid(user.getGrade().getUid());
						grade.setName(user.getGrade().getName());
						content.setGrade(grade);
					}
					if (user.getAcademy() != null) {
						TransferAcademyInfo academy = new TransferAcademyInfo();
						academy.setUid(user.getAcademy().getUid());
						academy.setName(user.getAcademy().getName());
						content.setAcademy(academy);
					}
					if (user.getCourse() != null) {
						TransferCourseInfo course = new TransferCourseInfo();
						course.setUid(user.getCourse().getUid());
						course.setName(user.getCourse().getName());
						content.setCourse(course);
					}
					content.setCreditvalue(user.getCreditvalue());
					if (user.getLocation() != null) {
						TransferLocationInfo location = new TransferLocationInfo();
						location.setUid(user.getLocation().getUid());
						location.setName(user.getLocation().getName());
						location.setLatitude(user.getLocation().getLatitude());
						location.setLongitude(user.getLocation().getLongitude());
						content.setLocation(location);
					}
					if (user.getRole() != null) {
						TransferRoleInfo role = new TransferRoleInfo();
						role.setUid(user.getRole().getUid());
						role.setName(user.getRole().getName());
						role.setRolecode(user.getRole().getRolecode());
						role.setRolevalue(user.getRole().getRolevalue());
						content.setRole(role);
					}
					if (user.getQualificationtype() != null) {
						TransferQualificationtypeInfo qualType = new TransferQualificationtypeInfo();
						qualType.setUid(user.getQualificationtype().getUid());
						qualType.setName(user.getQualificationtype().getName());
						content.setQualtype(qualType);
					}
					u.setUser(content);
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
			userDao.close();
		}
	}

}
