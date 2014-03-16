package newx.project;

import org.springframework.stereotype.Component;

@Component("CommonProjectInfo")
public class ProjectInfo implements IProjectInfo {
	private NewXProject project = new NewXProject();
	
	public ProjectInfo() {
		project.setId("Common");
		project.setName("Common系统");
	}
	
	public NewXProject getProject() {
		return project;
	}
}
