package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OnlineDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##himedia";
	String password = "himedia";// 하이 미디어 ㅋㅋ

	private Connection con;
	private PreparedStatement stmt;
//	private Statement stmt;
	private ResultSet rs;

	public ArrayList<OnlineVO> list(String id) {
		ArrayList<OnlineVO> list = new ArrayList<OnlineVO>();

		try {
			connDB();

			String query = "SELECT * FROM MEMBER";
			if (id != null) {
				query += " where id=TRIM('" + id + ")";
//				query = "insert into Member(ID,PASSWORD)values(?,?)"; 
			}
			System.out.println("SQL : " + query);
//			PreparedStatement test = con.prepareStatement(query);
//			rs = test.executeQuery();
			rs = stmt.executeQuery(query);
			rs.last();
//			System.out.println("rs :: " + rs);

			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String sh = rs.getString("ID");
					String password11 = rs.getString("PASSWORD");

					OnlineVO data = new OnlineVO(sh, password11);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int join(OnlineVO member) {
		int result = 0;
		try {

//			connDB();
			try {
				// 여기서 DB 케넥트 연결할때 jar 파일이라 해야하나 라이브러리가 없어서 연결이 안됐던거야 어디냐면
				Class.forName(driver); // 이부분인데 이부분을 사용하려면 라이브러리가 필

				System.out.println("jdbc driver loading success.");
				con = DriverManager.getConnection(url, user, password);
				System.out.println("oracle connection success.");

				String sql = "insert into Member(ID,PASSWORD,NAME,PHONE,PHONECHK,ADDRESS,NUM) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPwd());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getPhone());
				pstmt.setString(5, member.getPhonechk());
				pstmt.setString(6, member.getAddress());
				pstmt.setString(7, member.getNum());

				ResultSet rs = pstmt.executeQuery();
				rs.next();

				String msg = " ";

				if (rs.getRow() == 1) {
					System.out.println("회원가입 성공");
					msg = "회원가입 성공";
					JOptionPane.showMessageDialog(null, msg);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				// if(예외가 ORA-00001 이 나왔을 때){
//					오류창 뜨게 만드세요.
//				} else (다른 오류가 뜨면 예외처리 하세요.) {
//					화이팅
//				}
			} catch (Exception e) {
				e.printStackTrace();
				// 여기는 놔두세요.
			}

			// 그리고 여기에 아까 (?,?,?,?) 이렇게 해놔서 그렇게 하면 안돼
//			stmt = con.prepareStatement(query);
//			stmt = con.prepareStatement();
//			stmt.setString(1, member.getId());
//			stmt.setString(2, member.getPwd());
//	        stmt.setString(3, member.getName());
//	        stmt.setString(4, member.getPhone());
//	        stmt.setString(5, member.getPhonechk());
//	        stmt.setString(6, member.getAddress());
//	        stmt.setString(7, member.getNum());
			// 굿굿 ?짜잔그럼 내가 회원가입창을 만들고 회원가입한사람들의 이력이 나오게끔할때 디비에도 추가하고 여기에도
			// 그건 이제 너가 데이터화 하려는 정보들 받아서 하면 되는거고
			// 너가 지금 setName 부터 폰 포인트 저런거 있잖아

//			result = stmt.execute(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void connDB() {
		// 이부분인가 ?

		System.out.println("");
		// 그래 나중에 셋팅할때 또 알아야지
		try {
			// 여기서 DB 케넥트 연결할때 jar 파일이라 해야하나 라이브러리가 없어서 연결이 안됐던거야 어디냐면
//			Class.forName(driver);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
//			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
