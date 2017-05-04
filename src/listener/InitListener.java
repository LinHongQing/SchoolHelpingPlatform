package listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cache.Configurations;
import service.PlatformService;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("context 已销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("context 已创建");
		String webAppRootKey = sce.getServletContext().getRealPath("/");
		System.setProperty("ssm.root", webAppRootKey);
		System.setProperty("ssm.conf", webAppRootKey + "WEB-INF" + File.separator + "conf" + File.separator);
		PlatformService.getConfigFromFile(System.getProperty(Configurations.config_platform_property_key), Configurations.config_platform_property_file_name);
	}

}
