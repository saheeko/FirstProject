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
	String password = "himedia";// ���� �̵�� ����

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
				String msg = "���̵� �ߺ� ���Ұ�";
				JOptionPane.showMessageDialog(null, msg);
			} else if (member.getId().equals("") || member.getPwd().equals("") || member.getName().equals("")
					|| member.getPhone().equals("")) {
//				System.out.println("�Էµ��� ���� ������ �ֽ��ϴ�.");
				String msg = "�Էµ��� ���� ������ �ֽ��ϴ�.";
				JOptionPane.showMessageDialog(null, msg);
			} else {
				join(member);
			}
//			else {
//				CalendarExample cd = new CalendarExample();
//				cd.createAndShowGUI(member.getId(),member.getPwd(),member.getName(),member.getPhone());
//			}
		} catch (Exception e1) {
			System.out.println("����");
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
			System.out.println("����� row : " + r);

			String msg = " ";

			if (r == 1) {
				System.out.println("ȸ������ ����");
				msg = "ȸ�������� ���ϵ帳�ϴ�!";
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
			System.out.println("����� row : " + r);


			if (r == 2) {
				System.out.println("��¥  �� �ð� ���� �Ϸ�");
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

	         pstmt = con.prepareStatement(query); //?�� �����͸� �ֱ����� �׳ɾ��°�

	         pstmt.setString(1, member.getId());
	         pstmt.setString(2, member.getPhone());
	         pstmt.setString(2, member.getPwd());

	         rs = pstmt.executeQuery(); //���� ����� rs�� ��ȯ������
	         rs.next();
	         while (rs.next()) {
	            myMember.setId(rs.getString("id"));
	            myMember.setPhone(rs.getString("phone"));
	            myMember.setPwd(rs.getString("pwd"));
	
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return myMember; // ��ȯ Id�� �������

	   }
/////////////////////////////////////

	/////////////////////////

	////////////////////////// ȸ������ ����ó��
	////////////////////////////
	// ���� �Էµ� id�� DB�� �����ϴ��� Ȯ��

	public void connDB() {
		// �̺κ��ΰ� ?

		System.out.println("");
		// �׷� ���߿� �����Ҷ� �� �˾ƾ���
		try {
			// ���⼭ DB �ɳ�Ʈ �����Ҷ� jar �����̶� �ؾ��ϳ� ���̺귯���� ��� ������ �ȵƴ��ž� ���ĸ�
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
