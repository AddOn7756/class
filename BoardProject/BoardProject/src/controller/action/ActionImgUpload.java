package controller.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.common.Action;
import controller.common.ActionForward;
import model.users.UsersDAO;
import model.users.UsersVO;

public class ActionImgUpload implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		UsersDAO userDAO = new UsersDAO();
		UsersVO userVO = new UsersVO();

		//���� ���
		String filePath = request.getSession().getServletContext().getRealPath("images"); 
		String encoding = "UTF-8";


		// �ִ� 16Mbyte ����
		int maxSize = 16 * 1024 * 1024; 
		MultipartRequest multi;


		try {

			multi = new MultipartRequest(request, filePath, maxSize, encoding,	// ���� ����	
					new DefaultFileRenamePolicy()); // �����̸� �ڵ� ������		
			userVO.setIconId((String)multi.getFilesystemName("filename")); // ������ ������ �̸� ��������
			userVO.setUserNum(Integer.parseInt(multi.getParameter("usernum")));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UsersVO uvo = userDAO.getDBData(userVO);
		System.out.println(uvo);
		// reupload �� ���ϻ���
		if (uvo.getIconId() != null) {
			//���� ����
			filePath += "/"+uvo.getIconId();
			File file = new File(filePath);	//���� ����
			if(file.exists()) {				//������ ������ ���� 
				file.delete();
			}
		}
		
		if (userDAO.uploadImg(userVO)) {
			System.out.println("���ε� ����");
			forward.setRedirect(false);
			forward.setPath("main.do");
		}
		else {
			System.out.println("���ε� ����");
			//���ε� ���н� ���� ����
			filePath += "/" + userVO.getIconId();	//���
			File file = new File(filePath);			//���� ����
			if(file.exists()) {						//������ ������ ��
				file.delete();
			}
			forward.setRedirect(false);
			forward.setPath("form.do");
		}
		return forward;
	}
}
