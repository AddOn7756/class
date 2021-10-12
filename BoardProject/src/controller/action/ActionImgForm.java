package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class ActionImgForm implements Action {			//���ε� ��ư Ŭ���� ���ε� �������� �̵�

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath("imgform.jsp");
		return forward;
	}
	
}
