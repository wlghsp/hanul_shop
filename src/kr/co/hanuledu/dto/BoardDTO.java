package kr.co.hanuledu.dto;

import java.sql.Timestamp;
import java.net.URLEncoder;


public class BoardDTO {
	private int articleNo;  //글 번호 
	private String subject; //글 제목 
	private String content; //글 내용
	private String passwd;  //글 비밀번호
	private Timestamp reg_date; //글 작성일 
	private int readcount; // 글 조회수 
	private int ref;		//글 그룹번호
	private int re_step;	//제목글과 답변글의 순서 
	private int re_level;	//글 레벨
	private String fileName;//파일명 
	private String id;		//아이디 
	private String lock_post;  //비밀글 설정 
	private String show; //글 삭제 여부 n이 삭제 상태. 
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoardDTO(int articleNo, String subject, String content, String passwd, Timestamp reg_date, int readcount,
			int ref, int re_step, int re_level, String fileName, String id, String lock_post, String show) {
		super();
		this.articleNo = articleNo;
		this.subject = subject;
		this.content = content;
		this.passwd = passwd;
		this.reg_date = reg_date;
		this.readcount = readcount;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
		this.fileName = fileName;
		this.id = id;
		this.lock_post = lock_post;
		this.show = show;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLock_post() {
		return lock_post;
	}

	public void setLock_post(String lock_post) {
		this.lock_post = lock_post;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "BoardDTO [articleNo=" + articleNo + ", subject=" + subject + ", content=" + content + ", passwd="
				+ passwd + ", reg_date=" + reg_date + ", readcount=" + readcount + ", ref=" + ref + ", re_step="
				+ re_step + ", re_level=" + re_level + ", fileName=" + fileName + ", id=" + id + ", lock_post="
				+ lock_post + ", show=" + show + "]";
	}

	
	

	

	
}
