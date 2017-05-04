package service;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cache.Configurations;
import bean.TransferSystemConfigurationInfo;
import util.FileOperationUtil;
import util.StringUtil;

public class PlatformService {
	
	public static void getConfigFromFile(String filePath, String fileName) {
		Gson gson = new Gson();
		Type type = new TypeToken<TransferSystemConfigurationInfo>(){}.getType();
		String confContent = FileOperationUtil.readFile(filePath, fileName);
		if (confContent == null) {
			writeConfigToFile(filePath, fileName);
			return;
		}
		try {
			TransferSystemConfigurationInfo sysConf = gson.fromJson(confContent, type);
			if (sysConf.getChatsystempath() != null && !"".equals(sysConf.getChatsystempath()))
				Configurations.setChatSystemPath(sysConf.getChatsystempath());
			if (sysConf.getDefaultuserroleuid() != null && !"".equals(sysConf.getDefaultuserroleuid()))
				Configurations.setDefaultUserRoleUid(sysConf.getDefaultuserroleuid());
			if (sysConf.getDefaultusercreditvalue() != Configurations.int_invalid)
				Configurations.setDefaultUserCreditValue(sysConf.getDefaultusercreditvalue());
			if (sysConf.getCreditvaluechangeforcreator() != Configurations.int_invalid)
				Configurations.setCreditValueChangeForCreator(sysConf.getCreditvaluechangeforcreator());
			if (sysConf.getCreditvaluechangeforsolver() != Configurations.int_invalid)
				Configurations.setCreditValueChangeForSolver(sysConf.getCreditvaluechangeforsolver());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}
	
	public static boolean writeConfigToFile(String filePath, String fileName) {
		TransferSystemConfigurationInfo sysConf = new TransferSystemConfigurationInfo();
		sysConf.setChatsystempath(Configurations.getChatSystemPath());
		sysConf.setDefaultusercreditvalue(Configurations.getDefaultUserCreditValue());
		sysConf.setDefaultuserroleuid(Configurations.getDefaultUserRoleUid());
		sysConf.setCreditvaluechangeforcreator(Configurations.getCreditValueChangeForCreator());
		sysConf.setCreditvaluechangeforsolver(Configurations.getCreditValueChangeForSolver());
		Gson gson = new Gson();
		System.out.println(StringUtil.formatJSON(gson.toJson(sysConf)));
		if (FileOperationUtil.writeStringToFile(StringUtil.formatJSON(gson.toJson(sysConf)), filePath, fileName))
			return true;
		else
			return false;
	}
}
