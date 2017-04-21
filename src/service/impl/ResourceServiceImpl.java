package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Resource;
import cache.ResultCodeStorage;
import dao.ResourceDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;
import bean.TransferResourceInfo;
import bean.TransferResultInfo;
import service.ResourceService;
import util.StringUtil;

public class ResourceServiceImpl implements ResourceService {
	
	private ResourceDao resourceDao;
	private TransferDbData transferDbData;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
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
			resourceDao.init();
			resourceDao.insert(transferDbData);
			resourceDao.close();
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
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			resourceDao.init();
			resourceDao.update(transferDbData);
			resourceDao.close();
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
		}
	}

	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			resourceDao.init();
			resourceDao.delete(transferDbData);
			resourceDao.close();
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
		}
	}

	@Override
	public TransferResultInfo<?> find() {
		// TODO Auto-generated method stub
		try {
			resourceDao.init();
			List<Resource> list_resource = resourceDao.select(transferDbData);
			List<TransferResourceInfo> finalList = new ArrayList<TransferResourceInfo>();
			for (Resource resource : list_resource) {
				TransferResourceInfo r = new TransferResourceInfo();
				r.setUid(resource.getUid());
				r.setName(resource.getName());
				r.setType(resource.getType());
				r.setValue(resource.getValue());
				finalList.add(r);
			}
			resourceDao.close();
			TransferResultInfo<List<TransferResourceInfo>> rs = new TransferResultInfo<List<TransferResourceInfo>>();
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
		}
	}

}
