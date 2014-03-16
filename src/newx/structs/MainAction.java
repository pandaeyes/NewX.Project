package newx.structs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MainAction extends Action {
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        m_Mapping = mapping;
        m_Form = form;
        m_Request = request;
        m_Response = response;

//        ISecureUser secUser = (ISecureUser) request.getSession(true)
//                .getAttribute("secureuser");
//        if (secUser == null) {
//            DebugUtil.println("NO LOGIN!");
//            return mapping.findForward("nologin");
//        }

        String actionType = request.getParameter("actionType");
        ActionForward af = mapping.findForward(actionType);
        if (af == null) {
        	af = mapping.findForward("not_found");
        }
        return af;
    }

    protected ActionMapping m_Mapping = null;

    protected ActionForm m_Form = null;

    protected HttpServletRequest m_Request = null;

    protected HttpServletResponse m_Response = null;
}