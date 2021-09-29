package model.board;

import java.sql.Date;

public class BoardVO {

	private int bId;
	private int userNum;
	private String bCtgr;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private Date bDate;
	private int bHit;
	private String bLang;
	private int reCnt;
	private int boardCnt;
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getbCtgr() {
		return bCtgr;
	}
	public void setbCtgr(String bCtgr) {
		this.bCtgr = bCtgr;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public String getbLang() {
		return bLang;
	}
	public void setbLang(String bLang) {
		this.bLang = bLang;
	}
	public int getReCnt() {
		return reCnt;
	}
	public void setReCnt(int reCnt) {
		this.reCnt = reCnt;
	}
	
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	/*@Override
	public String toString() {
		return "BoardVO [�Խñ�ID : " + bId + " ������ȣ : " + userNum + " ī�װ� : " + bCtgr + " ���� : " + bTitle
				+ " ���� : " + bContent + "�ۼ��� : " + bWriter + " �ۼ��� : " + bDate + " ��ȸ�� : " + bHit + " ��� : "
				+ bLang + " ��ۼ� : " + reCnt + "]\n";
	}*/
	@Override
	public String toString() {
		return "���� : " + bTitle
				+ " ���� : " + bContent + "�ۼ��� : " + bWriter + " �ۼ��� : " + bDate + " ��ȸ�� : " + bHit + " ��ۼ� : " + reCnt + "]\n";
	}
	

}
