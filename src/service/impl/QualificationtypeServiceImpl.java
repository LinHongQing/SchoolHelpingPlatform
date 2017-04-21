package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Qualificationtype;
import cache.ResultCodeStorage;
import dao.QualificationtypeDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferAdminInfo;
import bean.TransferDbData;
import bean.TransferQualificationtypeInfo;
import bean.TransferResultInfo;
import service.QualificationtypeService;
import util.StringUtil;

public class QualificationtypeServiceImpl implements QualificationtypeService {

	private QualificationtypeDao qualificationtypeDao;
	private TransferDbData transferDbData;
	
	public QualificationtypeDao getQualificationtypeDao() {
		return qualificationtypeDao;
	}

	public void setQualificationtypeDao(QualificationtypeDao qualificationtypeDao) {
		this.qualificationtypeDao = qualificationtypeDao;
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
			qualificationtypeDao.init();
			qualificationtypeDao.insert(transferDbData);
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
			qualificationtypeDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			qualificationtypeDao.init();
			qualificationtypeDao.update(transferDbData);
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
			qualificationtypeDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			qualificationtypeDao.init();
			qualificationtypeDao.delete(transferDbData);
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
			qualificationtypeDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find() {
		// TODO Auto-generated method stub
		try {
			qualificationtypeDao.init();
			List<Qualificationtype> list_qualificationType = qualificationtypeDao.select(transferDbData);
			List<TransferQualificationtypeInfo> finalList = new ArrayList<TransferQualificationtypeInfo>();
			for (Qualificationtype qualificationType : list_qualificationType) {
				TransferQualificationtypeInfo q = new TransferQualificationtypeInfo();
				q.setUid(qualificationType.getUid());
				q.setName(qualificationType.getName());
				q.setStrapplicableproblemtypeUids(qualificationType.getProblemtypeid());
				q.setCreatetime(qualificationType.getCreatetime());
				q.setCreateip(qualificationType.getCreateip());
				if (qualificationType.getAdmin() != null) {
					TransferAdminInfo createuser = new TransferAdminInfo();
					createuser.setUid(qualificationType.getAdmin().getUid());
					createuser.setName(qualificationType.getAdmin().getName());
					q.setCreateuser(createuser);
				}
				finalList.add(q);
			}
			TransferResultInfo<List<TransferQualificationtypeInfo>> rs = new TransferResultInfo<List<TransferQualificationtypeInfo>>();
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
			qualificationtypeDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> init() {
		// TODO Auto-generated method stub
		try {
			qualificationtypeDao.init();
			List<Qualificationtype> list_qualificationType = qualificationtypeDao.select(transferDbData);
			List<TransferQualificationtypeInfo> finalList = new ArrayList<TransferQualificationtypeInfo>();
			for (Qualificationtype qualificationType : list_qualificationType) {
				TransferQualificationtypeInfo q = new TransferQualificationtypeInfo();
				q.setUid(qualificationType.getUid());
				q.setName(qualificationType.getName());
				finalList.add(q);
			}
			TransferResultInfo<List<TransferQualificationtypeInfo>> rs = new TransferResultInfo<List<TransferQualificationtypeInfo>>();
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
			qualificationtypeDao.close();
		}
	}

}
