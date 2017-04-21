package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import pojo.Problem;
import cache.ResultCodeStorage;
import dao.ProblemDao;
import exception.HibernateSessionNotInitializedException;
import exception.MissingParameterException;
import bean.TransferDbData;
import bean.TransferLocationInfo;
import bean.TransferProblemInfo;
import bean.TransferProblemtypeInfo;
import bean.TransferResultInfo;
import bean.TransferSolveInfo;
import bean.TransferUserInfo;
import service.ProblemService;
import util.StringUtil;

public class ProblemServiceImpl implements ProblemService {
	
	private ProblemDao problemDao;
	private TransferDbData transferDbData;

	public ProblemDao getProblemDao() {
		return problemDao;
	}

	public void setProblemDao(ProblemDao problemDao) {
		this.problemDao = problemDao;
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
			problemDao.init();
			problemDao.insert(transferDbData);
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
			problemDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> update() {
		// TODO Auto-generated method stub
		try {
			problemDao.init();
			problemDao.update(transferDbData);
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
			problemDao.close();
		}
	}

	@Override
	public TransferResultInfo<String> delete() {
		// TODO Auto-generated method stub
		try {
			problemDao.init();
			problemDao.delete(transferDbData);
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
			problemDao.close();
		}
	}

	@Override
	public TransferResultInfo<?> find(int findMode) {
		// TODO Auto-generated method stub
		try {
			problemDao.init();
			switch(findMode) {
			case find_summary: {
				List<Problem> list_problem = problemDao.select(transferDbData);
				List<TransferProblemInfo> finalList = new ArrayList<TransferProblemInfo>();
				for (Problem problem : list_problem) {
					TransferProblemInfo p = new TransferProblemInfo();
					p.setUid(problem.getUid());
					if (problem.getUser() != null) {
						TransferUserInfo createuser = new TransferUserInfo();
						createuser.setUid(problem.getUser().getUid());
						createuser.setName(problem.getUser().getName());
						p.setCreateuser(createuser);
					}
					if (problem.getProblemtype() != null) {
						TransferProblemtypeInfo problemtype = new TransferProblemtypeInfo();
						problemtype.setUid(problem.getProblemtype().getUid());
						problemtype.setName(problem.getProblemtype().getName());
						p.setProblemtype(problemtype);
					}
					if (problem.getLocation() != null) {
						TransferLocationInfo location = new TransferLocationInfo();
						location.setUid(problem.getLocation().getUid());
						location.setName(problem.getLocation().getName());
						p.setLocation(location);
					}
					p.setTitle(problem.getTitle());
					p.setCreatetime(problem.getCreatetime());
					p.setStatus(problem.getStatus());
					if (problem.getSolve() != null) {
						TransferSolveInfo solved = new TransferSolveInfo();
						solved.setUid(problem.getSolve().getUid());
						p.setSolved(solved);
					}
					finalList.add(p);
				}
				TransferResultInfo<List<TransferProblemInfo>> rs = new TransferResultInfo<List<TransferProblemInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			case find_detail: {
				List<Problem> list_problem = problemDao.select(transferDbData);
				List<TransferProblemInfo> finalList = new ArrayList<TransferProblemInfo>();
				for (Problem problem : list_problem) {
					TransferProblemInfo p = new TransferProblemInfo();
					p.setUid(problem.getUid());
					if (problem.getUser() != null) {
						TransferUserInfo createuser = new TransferUserInfo();
						createuser.setUid(problem.getUser().getUid());
						createuser.setName(problem.getUser().getName());
						p.setCreateuser(createuser);
					}
					if (problem.getProblemtype() != null) {
						TransferProblemtypeInfo problemtype = new TransferProblemtypeInfo();
						problemtype.setUid(problem.getProblemtype().getUid());
						problemtype.setName(problem.getProblemtype().getName());
						p.setProblemtype(problemtype);
					}
					if (problem.getLocation() != null) {
						TransferLocationInfo location = new TransferLocationInfo();
						location.setUid(problem.getLocation().getUid());
						location.setName(problem.getLocation().getName());
						p.setLocation(location);
					}
					p.setTitle(problem.getTitle());
					p.setDescription(problem.getDescription());
					p.setStatus(problem.getStatus());
					p.setCreateip(problem.getCreateip());
					p.setCreatetime(problem.getCreatetime());
					p.setPreferday(problem.getPreferday());
					p.setPreferstart(problem.getPreferstart());
					p.setPreferend(problem.getPreferend());
					p.setResourceuid(problem.getResourceid());
					if (problem.getSolve() != null) {
						TransferSolveInfo solved = new TransferSolveInfo();
						solved.setUid(problem.getSolve().getUid());
						p.setSolved(solved);
					}
					finalList.add(p);
				}
				TransferResultInfo<List<TransferProblemInfo>> rs = new TransferResultInfo<List<TransferProblemInfo>>();
				rs.setMsgType(ResultCodeStorage.type_success);
				rs.setMsgCode(ResultCodeStorage.code_success);
				rs.setMsgContent(finalList);
				return rs;
			}
			case find_applicable: {
				List<Problem> list_problem = problemDao.selectApplicable(transferDbData);
				List<TransferProblemInfo> finalList = new ArrayList<TransferProblemInfo>();
				for (Problem problem : list_problem) {
					TransferProblemInfo p = new TransferProblemInfo();
					p.setUid(problem.getUid());
					TransferUserInfo createuser = new TransferUserInfo();
					createuser.setUid(problem.getUser().getUid());
					createuser.setName(problem.getUser().getName());
					p.setCreateuser(createuser);
					TransferProblemtypeInfo problemtype = new TransferProblemtypeInfo();
					problemtype.setUid(problem.getProblemtype().getUid());
					problemtype.setName(problem.getProblemtype().getName());
					p.setProblemtype(problemtype);
					TransferLocationInfo location = new TransferLocationInfo();
					location.setUid(problem.getLocation().getUid());
					location.setName(problem.getLocation().getName());
					location.setLatitude(problem.getLocation().getLatitude());
					location.setLongitude(problem.getLocation().getLongitude());
					p.setLocation(location);
					p.setPreferstart(problem.getPreferstart());
					p.setPreferend(problem.getPreferend());
					p.setTitle(problem.getTitle());
					p.setCreatetime(problem.getCreatetime());
					p.setStatus(problem.getStatus());
					finalList.add(p);
				}
				TransferResultInfo<List<TransferProblemInfo>> rs = new TransferResultInfo<List<TransferProblemInfo>>();
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
			problemDao.close();
		}
	}

}
