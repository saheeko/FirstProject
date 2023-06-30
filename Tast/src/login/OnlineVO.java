package login;

public class OnlineVO {

	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String Calendar;
	private String Month;
	private String TIME;
	private String MENTION;

	public OnlineVO() {

	}

	public OnlineVO(String sh, String password) {
		id = sh;
		pwd = password;
	}

	public OnlineVO(String joinsh, String joinpassword, String joinname, String joinphone) {
		id = joinsh;
		pwd = joinpassword;
		name = joinname;
		phone = joinphone;

	}

//	public OnlineVO(String joincalendar) {
//		Calendar = joincalendar;
//
//	}
	public OnlineVO(String joinsh, String joinpassword, String joinname, String joinphone, String joinmonth,
			String joincalendar, String jointime, String joinmention) {
		id = joinsh;
		pwd = joinpassword;
		name = joinname;
		phone = joinphone;
		Month = joinmonth;
		Calendar = joincalendar;
		TIME = jointime;
		MENTION = joinmention;

	}
//////////////////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCalendar() {
		return Calendar;
	}

	public void setCalendar(String Calendar) {
		this.Calendar = Calendar;

	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String Month) {
		this.Month = Month;

	}

	public String getTime() {
		return TIME;
	}

	public void setTime(String TIME) {
		this.TIME = TIME;
	}
	public String getMention() {
		return MENTION;
	}

	public void setMention(String MENTION) {
		this.MENTION = MENTION;
	}

}
