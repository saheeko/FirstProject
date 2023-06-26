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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

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
		} catch (SQLException e1) {
			if (e1.getMessage().contains("ORA-00001")) {
//				System.out.println("���̵� �ߺ��Ǿ� ���Ұ�");
				String msg = "���̵� �ߺ� ���Ұ�";
				JOptionPane.showMessageDialog(null, msg);
			} else if (member.getId().equals("") || member.getPwd().equals("")|| member.getName().equals("")
					|| member.getPhone().equals("")) { 
//				System.out.println("�Էµ��� ���� ������ �ֽ��ϴ�.");
				String msg = "�Էµ��� ���� ������ �ֽ��ϴ�.";
				JOptionPane.showMessageDialog(null, msg);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
