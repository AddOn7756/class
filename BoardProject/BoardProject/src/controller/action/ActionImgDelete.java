package controller.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.ActionForward;
import model.users.UsersDAO;
import model.users.UsersVO;

public class ActionImgDelete {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		UsersDAO userDAO = new UsersDAO();
		UsersVO userVO = new UsersVO();

		String filePath = request.getSession().getServletContext().getRealPath("images"); //���� �̹��� ���
		userVO.setUserNum(Integer.parseInt(request.getParameter("userNum")));
		userVO.setIconId(request.getParameter("filename"));

		if(userDAO.deleteImg(userVO)) {
			//���� ����
			filePath += "/"+userVO.getIconId();
			File file = new File(filePath);	//���� ����
			if(file.exists()) {				//������ ������ ���� 
				file.delete();
			}
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		else {
			System.out.println("���� ���� ����");
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		return forward;
	}


}
