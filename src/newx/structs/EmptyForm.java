package newx.structs;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmptyForm  extends ActionForm
{
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = null;
		return errors;
	}
}
