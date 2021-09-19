package model.users;

public class UsersVO {

	private int userNum;
	private String name;
	private String id;
	private String pw;
	private String hp;
	private String gender;
	private String email;
	private String addr;
	private String birth;
	private String iconId;
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
	
	@Override
	public String toString() {
		return "UsersVO [userNum=" + userNum + ", name=" + name + ", id=" + id + ", pw=" + pw + ", hp=" + hp
				+ ", gender=" + gender + ", email=" + email + ", addr=" + addr + ", birth=" + birth + ", iconId="
				+ iconId + "]";
	}
	
	
}
