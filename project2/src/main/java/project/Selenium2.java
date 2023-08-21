package project;

public class Selenium2 {
	private int id;
	private String img;
	private String date;
	private String 종목이름;
	private String 시가총액;
	private String 상장주식수;
	private String _52주최고52주최저;
	public String get종목이름() {
		return 종목이름;
	}
	
	public void set종목이름(String 종목이름) {
		this.종목이름=종목이름;
	}
	public String get시가총액() {
		return 시가총액;
	}
	public void set시가총액(String 시가총액) {
		this.시가총액 = 시가총액;
	}
	public String get상장주식수() {
		return 상장주식수;
	}
	public void set상장주식수(String 상장주식수) {
		this.상장주식수 = 상장주식수;
	}
	public String get_52주최고52주최저() {
		return _52주최고52주최저;
	}
	public void set_52주최고52주최저(String _52주최고52주최저) {
		this._52주최고52주최저 = _52주최고52주최저;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
