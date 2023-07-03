package login;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Master {

//	 public static void master(OnlineVO member)  {
	// ArrayList<HairMemberVo> arrMember
	public static void master(OnlineVO oB) {
		// TODO Auto-generated method stub

		JFrame jf = new JFrame();
		JPanel pn = new JPanel();
		JTable table = new JTable();
		JScrollPane sp = new JScrollPane(table);
		OnlineDAO dao = new OnlineDAO();
		ArrayList<OnlineVO> list = dao.list("all");
//		      Object record[] = new Object[5];

		jf = new JFrame();
		jf.setLocation(700, 400);
		jf.setSize(680, 500);
		jf.setTitle("예약현황");
		jf.setLayout(null);

		JLabel jl13 = new JLabel("Name : " + oB.getName() + " Cellphone : " + oB.getPhone() + " Month : "
				+ oB.getMonth() + " Time : " + oB.getTime() + " Mention : " + oB.getMention());
		jl13.setSize(400, 30);
		jl13.setLocation(110, 20);
		jl13.setHorizontalAlignment(JLabel.CENTER);

		jf.add(jl13);
		jf.add(pn);

		table.setSize(200, 200);

		String[] heading = { "이름", "연락처", "예약일자", "예약시간", "기재란" };
		DefaultTableModel dt = new DefaultTableModel(heading, 0);

		for (OnlineVO vo : list) {
			Object[] row = new Object[list.size()];

			dt.addRow(new Object[] { vo.getName(), vo.getPhone(), vo.getMonth()+vo.getCalendar()+"일", vo.getTime(),
					vo.getMention() });
		}
		table.setModel(dt);

		pn.add(sp);

		pn.setBounds(35, 70, 600, 700);
		sp.setBounds(35, 70, 600, 700);

		pn.setVisible(true);

		jf.setVisible(true);
	}

}
