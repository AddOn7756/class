package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.msg.MessageDAO;
import model.msg.MessageVO;

public class NewmsgAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MessageDAO mDAO=new MessageDAO();
		MessageVO mVO=new MessageVO();
		mVO.setMsg(request.getParameter("msg"));
		mVO.setMemid(request.getParameter("memid"));
		if(!mDAO.insert(mVO)) {
			throw new IOException("newmsg에서 발생!");
		}

		forward.setRedirect(false);
		forward.setPath("main.do");
		return forward;
	}

}
