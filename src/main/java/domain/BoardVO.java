package domain;

import java.sql.Timestamp;

public class BoardVO {

	private int num;
	private String title;
	private String content;
	private String writer;
	private Timestamp writerDate;
	private String realFileName;
	private String realSaveFileName;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWriterDate() {
		return writerDate;
	}
	public void setWriterDate(Timestamp writerDate) {
		this.writerDate = writerDate;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getRealSaveFileName() {
		return realSaveFileName;
	}
	public void setRealSaveFileName(String realSaveFileName) {
		this.realSaveFileName = realSaveFileName;
	}
	//객체에 값을 주고 어떻게 들어있나 확인하기 위함.
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writerDate=" + writerDate + ", realFileName=" + realFileName + ", realSaveFileName="
				+ realSaveFileName + "]";
	}
	
}
