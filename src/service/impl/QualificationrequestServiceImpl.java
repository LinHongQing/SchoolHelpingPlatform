package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Qualificationrequest;
import cache.Configurations;
import cache.ResultCodeStorage;
import dao.QualificationrequestDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferAdminInfo;
import bean.TransferDbData;
import bean.TransferQualificationrequestInfo;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import bean.TransferUserInfo;
import service.QualificationrequestService;
import util.StringUtil;

public class QualificationrequestServiceImpl implements
		QualificationrequestService {
	
	private QualificationrequestDao qualificationrequestDao;
	private TransferDbData transferDbData;

	public QualificationrequestDao getQualificationrequestDao() {
		return qualificationrequestDao;
	}

	public void setQualificationrequestDao(
			QualificationrequestDao qualificationrequestDao) {
		this.qualificationrequestDao = qualificationrequestDao;
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
			qualificationrequestDao.init();
			qualificationrequestDao.insert(transferDbData);
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
			qualificationrequestDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			qualificationrequestDao.init();
			qualificationrequestDao.update(transferDbData);
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
			qualificationrequestDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			qualificationrequestDao.init();
			qualificationrequestDao.delete(transferDbData);
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
			qualificationrequestDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find(int findMode) {
		// TODO Auto-generated method stub
		try {
			qualificationrequestDao.init();
			List<Qualificationrequest> list_qualificationType = qualificationrequestDao.select(transferDbData);
			switch (findMode) {
			case find_summary: {
				List<TransferQualificationrequestInfo> finalList = new ArrayList<TransferQualificationrequestInfo>();
				for (Qualificationrequest qualificationRequest : list_qualificationType) {
					TransferQualificationrequestInfo q = new TransferQualificationrequestInfo();
					q.setUid(qualificationRequest.getUid());
					if (qualificationRequest.getUser() != null) {
						TransferUserInfo user = new TransferUserInfo();
						user.setUid(qualificationRequest.getUser().getUid());
						user.setName(qualificationRequest.getUser().getName());
						q.setUser(user);
					}
					if (qualificationRequest.getQualificationtype() != null) {
						TransferQualificationtypeInfo type = new TransferQualificationtypeInfo();
						type.setUid(qualificationRequest.getQualificationtype().getUid());
						type.setName(qualificationRequest.getQualificationtype().getName());
						q.setType(type);
					}
					q.setRequesttime(qualificationRequest.getRequesttime());
					q.setCheckingtime(qualificationRequest.getCheckingtime() == null ? -1 : qualificationRequest.getCheckingtime());
					q.setCheckingstatus(qualificationRequest.getCheckingstatus());
					finalList.add(q);
				}
				TransferResultInfo<List<TransferQualificationrequestInfo>> rs = new TransferResultInfo<List<TransferQualificationrequestInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			case find_detail: {
				List<TransferQualificationrequestInfo> finalList = new ArrayList<TransferQualificationrequestInfo>();
				for (Qualificationrequest qualificationRequest : list_qualificationType) {
					TransferQualificationrequestInfo q = new TransferQualificationrequestInfo();
					q.setUid(qualificationRequest.getUid());
					if (qualificationRequest.getUser() != null) {
						TransferUserInfo user = new TransferUserInfo();
						user.setUid(qualificationRequest.getUser().getUid());
						user.setName(qualificationRequest.getUser().getName());
						q.setUser(user);
					}
					if (qualificationRequest.getQualificationtype() != null) {
						TransferQualificationtypeInfo type = new TransferQualificationtypeInfo();
						type.setUid(qualificationRequest.getQualificationtype().getUid());
						type.setName(qualificationRequest.getQualificationtype().getName());
						q.setType(type);
					}
					q.setResourceuid(qualificationRequest.getResourceid());
					q.setDescription(qualificationRequest.getDescription());
					q.setRequestip(qualificationRequest.getRequestip());
					q.setRequesttime(qualificationRequest.getRequesttime());
					q.setCheckingstatus(qualificationRequest.getCheckingstatus());
					q.setCheckingtype(qualificationRequest.getCheckingtype() == null ? Configurations.int_invalid : qualificationRequest.getCheckingtype());
					q.setCheckingtime(qualificationRequest.getCheckingtime() == null ? Configurations.int_invalid : qualificationRequest.getCheckingtime());
					q.setCheckingip(qualificationRequest.getCheckingip());
					if (qualificationRequest.getAdmin() != null) {
						TransferAdminInfo checkinguser = new TransferAdminInfo();
						checkinguser.setUid(qualificationRequest.getAdmin().getUid());
						checkinguser.setName(qualificationRequest.getAdmin().getName());
						q.setCheckinguser(checkinguser);
					}
					finalList.add(q);
				}
				TransferResultInfo<List<TransferQualificationrequestInfo>> rs = new TransferResultInfo<List<TransferQualificationrequestInfo>>();
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
			qualificationrequestDao.close();
		}
	}

}
