<%@page import="domain.BoardInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="domain.BoardVO"%>
<%@page import="java.util.Collection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BoardInfo boardInfo = (BoardInfo) request.getAttribute("boardInfo");
Collection<BoardVO> list = boardInfo.getList();
int totalRow = boardInfo.getTotalRow();

int totalNum = (Integer) request.getAttribute("totalNum");
int pageNum = (Integer) request.getAttribute("pageNum");
int pageRow = (Integer) request.getAttribute("pageRow");

int pagingNum = (Integer) request.getAttribute("pagingNum");
int startNum = (Integer) request.getAttribute("startNum");

String field = (String) request.getAttribute("field");
String keyWord = (String) request.getAttribute("keyWord");

String keyWordT = (String) request.getAttribute("keyWordT");
String keyWordC = (String) request.getAttribute("keyWordC");
String keyWordW = (String) request.getAttribute("keyWordW");

int lastPage = totalRow / pageRow + ((totalRow % pageRow == 0) ? 0 : 1);
if (pageNum > lastPage || pageNum < 1) {
	//response.sendRedirect("http://www.naver.com");
}

//Collection<BoardVO> list = (Collection)request.getAttribute("list");
//int totalRow = (Integer)request.getAttribute("totalRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<div class="pull-left">
				전체글수 :
				<%=totalRow%>
			</div><br/>
			<div class="pull-left">
				<a href="writer" class="btn btn-default">글쓰기</a>
			</div>
			
			<div class="pull-right" style="width: 310px;">
				<form>		
					<select name="field" class="form-control" style="display: inline-block; width: 30%">
						<option value="">전체</option>
						<option value="title"<%="title".equals(field) ? "selected='selected'" : ""%>>제목</option>
						<option value="content"<%="content".equals(field) ? "selected='selected'" : ""%>>내용</option>
						<option value="titleContent"<%="titleContent".equals(field) ? "selected='selected'" : ""%>>제목+내용</option>
					</select> 
					<input type="text" name="keyWord" value="<%=keyWord%>"class="form-control" style="display: inline-block; width: 50%">
					 <button class="btn btn-default">검색</button> 
				</form>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>첨부파일</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<%
				Iterator<BoardVO> it = list.iterator();
				while (it.hasNext()) {
					BoardVO vo = it.next();
				%>
				<tr>
					<td><%=vo.getNum()%></td>
					<td><a href="view?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></td>
					<td><%=vo.getWriter()%></td>
					<td><%=vo.getRealFileName()!=null?"<img src='img/paper-clip.png' style='width:24px;'/>":""%></td>
					<td><%=vo.getWriterDate()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<nav style="text-align: center;">
			<ul class="pagination">
				<%
				if (pageNum == 1) {
				%>
				<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
				<%
				} else {
				%>
				<li><a href="?pageNum=<%=pageNum - 1%>" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
				<%
				}
				%>

				<%
				for (int i = startNum; i <= (startNum + pagingNum) - 1; i++) {
					if (i > lastPage)
						break;
					if (pageNum == i) {
				%>
				<li class="active"><a><%=i%></a></li>
				<%
				} else {
				%>
				<li><a
					href="?pageNum=<%=i%>&field=<%=field%>&keyWord=<%=keyWord%>"><%=i%></a></li>
				<%
				}
				}
				%>

				<%
				if (lastPage == pageNum) {
				%>
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
				<%
				} else {
				%>
				<li><a href="?pageNum=<%=pageNum + 1%>" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
				<%
				}
				%>
			</ul>
		</nav>
		<input type="text" name="keyWord" value="<%=keyWord%>"class="form-control" style="display: inline-block; width: 50%">
	<form class="form-inline" style="text-align:center">
  		<div class="form-group">
    		<label for="titleT">제목</label>
    		<input type="text" class="form-control" name="keyWordT" value="<%=keyWordT%>">
  		</div>
  		<div class="form-group">
    		<label for="contentC">내용</label>
    		<input type="text" class="form-control" name="keyWordC" value="<%=keyWordC%>">
  		</div>
  		<div class="form-group">
    		<label for="writerW">작성자</label>
    		<input type="text" class="form-control" name="keyWordW" value="<%=keyWordW%>">
  		</div>
  <button type="submit" class="btn btn-default">검색</button>
</form>
		
	</div>
	
	
</body>
</html>
