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
		System.setProperty(Configurations.config_platform_property_webroot_key, webAppRootKey);
		System.setProperty(Configurations.config_platform_property_conf_key, webAppRootKey + "WEB-INF" + File.separator + "conf" + File.separator);
		System.setProperty(Configurations.config_platform_property_upload_key, webAppRootKey + "WEB-INF" + File.separator + "upload" + File.separator);
		PlatformService.getConfigFromFile(System.getProperty(Configurations.config_platform_property_conf_key), Configurations.config_platform_property_file_name);
	}

}
