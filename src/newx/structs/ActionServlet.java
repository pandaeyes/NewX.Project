package newx.structs;

import java.util.List;

import javax.servlet.ServletException;

import newx.framework.StrutsConfigService;
import newx.project.NewXProject;

import org.apache.log4j.Logger;

public class ActionServlet extends org.apache.struts.action.ActionServlet {

	private static final Logger log = Logger.getLogger(ActionServlet.class);
		    
	protected void initOther() throws ServletException {
		super.initOther();
		List<NewXProject> list = StrutsConfigService.getInstance().getProjectList();
		if (list != null) {
			for (NewXProject info : list) {
				log.info("加载模块:" + info.getName() + "(" + info.getId() + ")");
				this.config += "," + "/WEB-INF/struts-config-" + info.getId().toLowerCase() + ".xml";
			}
		}
	}
}
