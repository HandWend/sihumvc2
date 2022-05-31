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
import service.WriterServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/writer")
public class WriterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //바로 보여주면 되므로 get방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("writer.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String saveFolder = "upload";
		
		ServletContext context = request.getServletContext();
	    String realFolder = context.getRealPath(saveFolder);
		
		File targetDir = new File(saveFolder);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		int maxSize = 10*1024*1024;	//10Mb
		String encType = "UTF-8";
		//값 받아서 db 저장
		MultipartRequest multi = 
				new MultipartRequest(request, realFolder , maxSize , encType , new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		
		String realSaveFileName = multi.getFilesystemName("upfile");
		String realFileName = multi.getOriginalFileName("upfile");
		
		
		
		//vo에 저장
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setRealFileName(realFileName);
		vo.setRealSaveFileName(realSaveFileName);
		System.out.println(vo);
		
		WriterServiceImpl service = new WriterServiceImpl();
		//vo값 들고 가야 한다.
		service.insert(vo);
		
		//페이지 이동
		response.sendRedirect("list");
	}

}
