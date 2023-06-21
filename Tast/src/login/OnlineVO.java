package login;

public class OnlineVO {

   private String id;
   private String pwd;
   private String name;
   private String phone;
   private String phonechk;
   private String address;
   private String num;
  // private String point;
	public OnlineVO(String sh, String password) {
		id = sh;
		pwd = password;
		}
	public OnlineVO(String joinsh, String joinpassword, String joinname,  String joinphone,  String joinphonechk,  String joinaddress,  String joinnum) {
		id = joinsh;
		pwd = joinpassword;
		name = joinname;
		phone = joinphone;
		phonechk = joinphonechk;
		address = joinaddress;
		num = joinnum;
		}
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
	public String getPhonechk() {
		return phonechk;
	}
	public void setPhonechk(String phonechk) {
		this.phonechk = phonechk;
	}
	public String getAddress() {
		return address;
	}
	public void setAdress(String address) {
		this.address = address;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
//	public int getPoint() {
//		return point;
//	}
//	public void setPoint(int point) {
//		this.point = point;
//	}


// 여기에 선언이 되있어서 사용하는건줄알았어 

}
