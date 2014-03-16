package newx.framework;

import java.util.ArrayList;
import java.util.List;

import newx.project.NewXProject;

public class StrutsConfigService {
	
	private static StrutsConfigService service = null;
	private List<NewXProject> projectList = new ArrayList<NewXProject>();
	
	public void setProjectList(List<NewXProject> projectList) {
		this.projectList = projectList;
	}
	
	public List<NewXProject> getProjectList() {
		return projectList;
	}
	
	private StrutsConfigService(){
	}
	
	public static StrutsConfigService getInstance() {
		if (service == null) {
			service = new StrutsConfigService();
		}
		return service;
	}
}
