package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Complaintrequest;
import cache.Configurations;
import cache.ResultCodeStorage;
import dao.ComplaintrequestDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferAdminInfo;
import bean.TransferComplaintrequestInfo;
import bean.TransferDbData;
import bean.TransferProblemInfo;
import bean.TransferResultInfo;
import bean.TransferUserInfo;
import service.ComplaintrequestService;
import util.StringUtil;

public class ComplaintrequestServiceImpl implements ComplaintrequestService {
	
	private ComplaintrequestDao complaintrequestDao;
	private TransferDbData transferDbData;

	public ComplaintrequestDao getComplaintrequestDao() {
		return complaintrequestDao;
	}

	public void setComplaintrequestDao(ComplaintrequestDao complaintrequestDao) {
		this.complaintrequestDao = complaintrequestDao;
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
			complaintrequestDao.init();
			complaintrequestDao.insert(transferDbData);
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
			complaintrequestDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			complaintrequestDao.init();
			complaintrequestDao.update(transferDbData);
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
			complaintrequestDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find(int findMode) {
		// TODO Auto-generated method stub
		try {
			complaintrequestDao.init();
			List<Complaintrequest> list_complaintRequest = complaintrequestDao.select(transferDbData);
			List<TransferComplaintrequestInfo> finalList = new ArrayList<TransferComplaintrequestInfo>();
			switch (findMode) {
			case find_summary: {
				for (Complaintrequest complaintRequest : list_complaintRequest) {
					TransferComplaintrequestInfo c = new TransferComplaintrequestInfo();
					c.setUid(complaintRequest.getUid());
					if (complaintRequest.getUser() != null) {
						TransferUserInfo user = new TransferUserInfo();
						user.setUid(complaintRequest.getUser().getUid());
						user.setName(complaintRequest.getUser().getName());
						c.setUser(user);
					}
					if (complaintRequest.getProblem() != null) {
						TransferProblemInfo problem = new TransferProblemInfo();
						problem.setUid(complaintRequest.getProblem().getUid());
						problem.setTitle(complaintRequest.getProblem().getTitle());
						c.setProblem(problem);
					}
					c.setCreatetime(complaintRequest.getCreatetime());
					c.setCreateip(complaintRequest.getCreateip());
					c.setStatus(complaintRequest.getStatus());
					if (complaintRequest.getAdmin() != null) {
						TransferAdminInfo replycreateuser = new TransferAdminInfo();
						replycreateuser.setUid(complaintRequest.getAdmin().getUid());
						replycreateuser.setName(complaintRequest.getAdmin().getName());
						c.setReplycreateuser(replycreateuser);
					}
					finalList.add(c);
				}
				TransferResultInfo<List<TransferComplaintrequestInfo>> rs = new TransferResultInfo<List<TransferComplaintrequestInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			case find_detail: {
				for (Complaintrequest complaintRequest : list_complaintRequest) {
					TransferComplaintrequestInfo c = new TransferComplaintrequestInfo();
					c.setUid(complaintRequest.getUid());
					if (complaintRequest.getUser() != null) {
						TransferUserInfo user = new TransferUserInfo();
						user.setUid(complaintRequest.getUser().getUid());
						user.setName(complaintRequest.getUser().getName());
						c.setUser(user);
					}
					if (complaintRequest.getProblem() != null) {
						TransferProblemInfo problem = new TransferProblemInfo();
						problem.setUid(complaintRequest.getProblem().getUid());
						problem.setTitle(complaintRequest.getProblem().getTitle());
						c.setProblem(problem);
					}
					c.setDescription(complaintRequest.getDescription());
					c.setCreatetime(complaintRequest.getCreatetime());
					c.setCreateip(complaintRequest.getCreateip());
					c.setResourceuid(complaintRequest.getResourceid());
					c.setStatus(complaintRequest.getStatus());
					c.setReplydescription(complaintRequest.getReplydescription());
					c.setReplyresourceuid(complaintRequest.getReplyresourceid());
					c.setReplycreateip(complaintRequest.getReplycreateip());
					c.setReplycreatetime(complaintRequest.getReplycreatetime() == null ? Configurations.int_invalid : complaintRequest.getReplycreatetime());
					if (complaintRequest.getAdmin() != null) {
						TransferAdminInfo replycreateuser = new TransferAdminInfo();
						replycreateuser.setUid(complaintRequest.getAdmin().getUid());
						replycreateuser.setName(complaintRequest.getAdmin().getName());
						c.setReplycreateuser(replycreateuser);
					}
					finalList.add(c);
				}
				TransferResultInfo<List<TransferComplaintrequestInfo>> rs = new TransferResultInfo<List<TransferComplaintrequestInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			default:
				TransferResultInfo<String> rs = new TransferResultInfo<String>();
				rs.setMsgType(ResultCodeStorage.type_error);
				rs.setMsgCode(ResultCodeStorage.code_err_invalid_parameter);
				rs.setMsgContent(StringUtil.formatResultInfoMessage(ResultCodeStorage.code_err_invalid_parameter, "参数无效"));
				return rs;
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
			complaintrequestDao.close();
		}
	}

}
