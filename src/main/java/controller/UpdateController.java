package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.BoardVO;
import service.ViewServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String saveFolder = "upload";

		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);

		File targetDir = new File(saveFolder);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		int maxSize = 10 * 1024 * 1024; // 10Mb
		String encType = "UTF-8";
		// 값 받아서 db 저장

		BoardVO vo = new BoardVO();
		//안되는 중
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		
		ViewServiceImpl service = new ViewServiceImpl();
		BoardVO bvo = service.read(vo);

		request.setAttribute("vo", bvo);

		response.sendRedirect("list"); 
		/*
		 * RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
		 * dispatcher.forward(request, response);
		 */
	}
}
