package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class NewUserAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		MemberDAO uDAO=new MemberDAO();
		MemberVO uVO=new MemberVO();
		uVO.setMemid(request.getParameter("memid"));
		uVO.setPasswd(request.getParameter("passwd"));
		uVO.setName(request.getParameter("name"));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(uDAO.insert(uVO)){
			out.println("<script>alert('축하합니다!');window.close();</script>");
		}
		else{
			out.println("<script>alert('실패');window.close();</script>");
		}
		
		return forward;
	}

}
