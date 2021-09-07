package model.board;

public class BoardVO {
	
	private int bornum;
	private String title;
	private String content;
	private String writer;
	
	public int getBornum() {
		return bornum;
	}
	public void setBornum(int bornum) {
		this.bornum = bornum;
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
	@Override
	public String toString() {
		return "BoardVO [bornum=" + bornum + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
	
}
