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
				// ���⼭ DB �ɳ�Ʈ �����Ҷ� jar �����̶� �ؾ��ϳ� ���̺귯���� ��� ������ �ȵƴ��ž� ���ĸ�
				Class.forName(driver); // �̺κ��ε� �̺κ��� ����Ϸ��� ���̺귯���� ��

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
					System.out.println("ȸ������ ����");
					msg = "ȸ������ ����";
					JOptionPane.showMessageDialog(null, msg);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				// if(���ܰ� ORA-00001 �� ������ ��){
//					����â �߰� ���弼��.
//				} else (�ٸ� ������ �߸� ����ó�� �ϼ���.) {
//					ȭ����
//				}
			} catch (Exception e) {
				e.printStackTrace();
				// ����� ���μ���.
			}

			// �׸��� ���⿡ �Ʊ� (?,?,?,?) �̷��� �س��� �׷��� �ϸ� �ȵ�
//			stmt = con.prepareStatement(query);
//			stmt = con.prepareStatement();
//			stmt.setString(1, member.getId());
//			stmt.setString(2, member.getPwd());
//	        stmt.setString(3, member.getName());
//	        stmt.setString(4, member.getPhone());
//	        stmt.setString(5, member.getPhonechk());
//	        stmt.setString(6, member.getAddress());
//	        stmt.setString(7, member.getNum());
			// �±� ?¥�ܱ׷� ���� ȸ������â�� ����� ȸ�������ѻ������ �̷��� �����Բ��Ҷ� ��񿡵� �߰��ϰ� ���⿡��
			// �װ� ���� �ʰ� ������ȭ �Ϸ��� ������ �޾Ƽ� �ϸ� �Ǵ°Ű�
			// �ʰ� ���� setName ���� �� ����Ʈ ������ ���ݾ�

//			result = stmt.execute(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void connDB() {
		// �̺κ��ΰ� ?

		System.out.println("");
		// �׷� ���߿� �����Ҷ� �� �˾ƾ���
		try {
			// ���⼭ DB �ɳ�Ʈ �����Ҷ� jar �����̶� �ؾ��ϳ� ���̺귯���� ��� ������ �ȵƴ��ž� ���ĸ�
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
