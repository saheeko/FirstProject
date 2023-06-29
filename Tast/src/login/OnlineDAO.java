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
	private PreparedStatement pstmt;
//	private Statement stmt;
	private ResultSet rs;

	public ArrayList<OnlineVO> list(String id) {
		ArrayList<OnlineVO> list = new ArrayList<OnlineVO>();

		try {
			connDB();

			String query = "SELECT * FROM MEMBER";
			if (id != null) {
				query += " where id=TRIM('" + id + "')";
			}
			System.out.println("SQL : " + query);
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			Statement stmt = conn.createStatement();
//			Connection conn = DriverManager.getConnection(url, user, password);
//			rs = stmt.executeQuery(query);
			rs = pstmt.executeQuery();
			rs.last();

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
//				CalendarExample cd = new CalendarExample();
//				OnlineVO data = new OnlineVO(query, query);
//				cd.createAndShowGUI(data.getId(),data.getPwd(),data.getName(),data.getPhone());
			}
//				CalendarExample cd = new CalendarExample();
//				OnlineVO data = new OnlineVO(query, query);
//				cd.createAndShowGUI(data.getId(),data.getPwd(),data.getName(),data.getPhone());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public void joinId(OnlineVO member) {
		try {
			connDB();

			String query = "SELECT * FROM MEMBER where id=TRIM('" + member.getId() + "')";
			System.out.println("SQL : " + query);
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			Statement stmt = conn.createStatement();
//			Connection conn = DriverManager.getConnection(url, user, password);
//			rs = stmt.executeQuery(query);
			rs = pstmt.executeQuery();
			rs.last();

			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() != 0) {
				String msg = "아이디 중복 사용불가";
				JOptionPane.showMessageDialog(null, msg);
			} else if (member.getId().equals("") || member.getPwd().equals("") || member.getName().equals("")
					|| member.getPhone().equals("")) {
//				System.out.println("입력되지 않은 사항이 있습니다.");
				String msg = "입력되지 않은 사항이 있습니다.";
				JOptionPane.showMessageDialog(null, msg);
			} else {
				join(member);
			}
//			else {
//				CalendarExample cd = new CalendarExample();
//				cd.createAndShowGUI(member.getId(),member.getPwd(),member.getName(),member.getPhone());
//			}
		} catch (Exception e1) {
			System.out.println("오류");
		}
	}

	public void join(OnlineVO member) {
		try {
			connDB();

			String sql = "insert into Member(ID,PASSWORD,NAME,PHONE) values(?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());

			int r = pstmt.executeUpdate();
			System.out.println("변경된 row : " + r);

			String msg = " ";

			if (r == 1) {
				System.out.println("회원가입 성공");
				msg = "회원가입을 축하드립니다!";
				JOptionPane.showMessageDialog(null, msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SelectDateInputDB(String month, String date, String id, String time) {
		try {
			connDB();
			
			String sql = "UPDATE MEMBER SET MONTH = '"+ month +"', CALENDAR = '" + date + "', TIME = '" + time + "' WHERE id = '" + id + "'";

			pstmt = con.prepareStatement(sql);

//			pstmt.setString(1, month);
//			pstmt.setString(2, date);

			int r = pstmt.executeUpdate();
			System.out.println("변경된 row : " + r);


			if (r == 2) {
				System.out.println("날짜  및 시간 선택 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OnlineVO findpass(OnlineVO member) {
		OnlineVO myMember = new OnlineVO();
		
	      //System.out.println(member + "asdsadsad");
	      try {
	         connDB();
	         String query ="select * from Member where NAME = ? and PHONE = ?";

	         pstmt = con.prepareStatement(query); //?에 데이터를 넣기위해 그냥쓰는거

	         pstmt.setString(1, member.getId());
	         pstmt.setString(2, member.getPhone());
	         pstmt.setString(2, member.getPwd());

	         rs = pstmt.executeQuery(); //쿼리 결과를 rs에 반환시켜줌
	         rs.next();
	         while (rs.next()) {
	            myMember.setId(rs.getString("id"));
	            myMember.setPhone(rs.getString("phone"));
	            myMember.setPwd(rs.getString("pwd"));
	
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return myMember; // 반환 Id가 들어있음

	   }
/////////////////////////////////////

	/////////////////////////

	////////////////////////// 회원가입 예외처리
	////////////////////////////
	// 현재 입력된 id가 DB에 존재하는지 확인

	public void connDB() {
		// 이부분인가 ?

		System.out.println("");
		// 그래 나중에 셋팅할때 또 알아야지
		try {
			// 여기서 DB 케넥트 연결할때 jar 파일이라 해야하나 라이브러리가 없어서 연결이 안됐던거야 어디냐면
			Class.forName(driver);
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
