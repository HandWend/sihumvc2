package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardInfo;
import domain.BoardVO;
import service.ListServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/List")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //바로 보여주면 되므로 get방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//mapper 갔다 와야 함.
		ListServiceImpl service = new ListServiceImpl();
		
		//paging
		int pageCount = 5;	//아래에 보여줄 쪽수의 갯수
		int pageRow = 5;	//한 페이지에 보여줄 게시글 갯수
		int pageNum =  1;	//페이지 넘버
		int pagingNum = 5;	//
		
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int startPage = (pageNum - 1) * pageRow;
		
		BoardInfo boardInfo = service.boardInfo(startPage, pageRow);
		
		//글번호
		int totalNum = boardInfo.getTotalRow() -((pageNum - 1) * pageRow);
		
		//페이징
		int startNum = ((pageNum - 1) / pagingNum) * pagingNum +1;
		
		request.setAttribute("pagingNum", pagingNum);
		request.setAttribute("startNum", startNum);
		
		request.setAttribute("pageRow", pageRow);
		
		request.setAttribute("totalNum", totalNum);
		request.setAttribute("boardInfo", boardInfo);
		request.setAttribute("pageNum", pageNum);
		//parameter값 가져갈 거 없음
		/*
		 * Collection<BoardVO> list = service.read();
		 * 
		 * //전체 행 int totalRow = service.totalRow();
		 * 
		 * //listmapper에 있는 list값이 넘어갈 것이다. request.setAttribute("list", list);
		 * request.setAttribute("totalRow", totalRow);
		 */
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
