package Market;

public class User {
	
	
	private String user; // 유저 이름
	//private String seller; // 판매자 명
	private String date; // 가입시간 제공x
	private String email; // 이메일 로그인할때 사용
	private String password; // 비밀번호
	private String address; // 고객 주소
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	/*public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
