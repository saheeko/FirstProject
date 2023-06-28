package login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import login.OnlineDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CalendarExample {
	private static JFrame frame;
	private static JPanel mainPanel;
	private static JPanel monthPanel;
	private static JPanel selectedMonthPanel;
	private static OnlineDAO Dao;
	private static String tfid;
	private static String tfpass;
	private static String tfname;
	private static String tfpho;
	
	
	
	public void createAndShowGUI(String tfid, String tfpass, String tfname, String tfpho) {
		// �޽��� ���� û����¥ �������ּ���/
		String msg = " ";
		msg = "���೯¥�� �������ּ���! ";
		JOptionPane.showMessageDialog(null, msg);
		this.tfid = tfid;
		this.tfpass = tfpass;
		this.tfname = tfname;
		this.tfpho = tfpho;
		
		// JFrame ����
		frame = new JFrame("2023 Calendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(600, 300);

		// JPanel�� ���� BorderLayout�� ����� ��ü �г� ����
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// 12���� �� �г��� ���� GridLayout�� ����� �� �г� ����
		monthPanel = new JPanel();
		monthPanel.setLayout(new GridLayout(3, 4));

		// 1������ 12������ �� �г� �����Ͽ� �� �гο� �߰�
		for (int month = 1; month <= 12; month++) {
			JButton monthButton = new JButton(getMonthName(month));
			monthButton.setActionCommand(String.valueOf(month)); // ���� ��ư�� �׼� ������� ����
			monthButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String command = e.getActionCommand();
					int selectedMonth = Integer.parseInt(command);
					showMonthCalendar(2023, selectedMonth); // ���õ� ���� �޷� ǥ��
				}
			});
			monthPanel.add(monthButton);
		}

		// �� �г��� ��ü �гο� �߰�
		mainPanel.add(monthPanel, BorderLayout.CENTER);

		// ��ü �г��� JFrame�� �߰�
		frame.add(mainPanel);

		// JFrame�� ������
		frame.setVisible(true);
	}

	// �� �г��� �����ϴ� �޼���
	public static void showMonthCalendar(int year, int month) {
		// ������ ǥ�õ� �� �г� ����
		mainPanel.remove(monthPanel);

		// ���õ� ���� �г� ����
		selectedMonthPanel = createMonthPanel(year, month);

		// ���õ� ���� �г��� �� �г� ��ġ�� �߰�
		mainPanel.add(selectedMonthPanel, BorderLayout.CENTER);

		// "Back" ��ư ���� �� �߰�
		JButton backButton = new JButton("�ڷΰ���");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(selectedMonthPanel); // ���õ� ���� �г� ����
				mainPanel.add(monthPanel, BorderLayout.CENTER); // �� �г��� �ٽ� �߰�
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		mainPanel.add(backButton, BorderLayout.SOUTH);

		// ����� �г��� �ٽ� �׸���
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	// �� �г��� �����ϴ� �޼���
	public static JPanel createMonthPanel(int year, int month) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// �� �̸� ǥ��
		JButton monthButton = new JButton(getMonthName(month));
		panel.add(monthButton, BorderLayout.NORTH);

		// ��¥�� ǥ���� GridLayout ����
		GridLayout gridLayout = new GridLayout(0, 7);
		JPanel datePanel = new JPanel();
		datePanel.setLayout(gridLayout);
		panel.add(datePanel, BorderLayout.CENTER);

		// �ش� ���� ù ��° ���� ������ ���� (0: �Ͽ���, 1: ������, ..., 6: �����)
		int startDayOfWeek = getDayOfWeek(year, month, 1);

		// ���� �̸� ǥ��
		String[] dayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (String dayName : dayNames) {
			JButton dayButton = new JButton(dayName);
			dayButton.setEnabled(false);
			datePanel.add(dayButton);
		}

		// �� ���� ä���
		for (int i = 0; i < startDayOfWeek; i++) {
			JButton emptyButton = new JButton("");
			emptyButton.setEnabled(false);
			datePanel.add(emptyButton);
		}

		// ��¥ ��ư���� ��¥ ǥ��
		int lastDayOfMonth = getLastDayOfMonth(year, month);
		for (int day = 1; day <= lastDayOfMonth; day++) {
			JButton button = new JButton(String.valueOf(day));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//               JOptionPane.showMessageDialog(frame, "û�� ��¥�� �����Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
					   JButton clickedButton = (JButton) e.getSource();
				        String selectedDate = clickedButton.getText();
				        String selectedMonth = getMonthName(month); // Get the selected month name
				        String message = "���� ��¥�� " + selectedMonth + " " + selectedDate + "�Ϸ� �����Ǿ����ϴ�.";
					
					
				
//					String test = Dao.join2(member);


				
								
					
					OnlineDAO onlineDAO = new OnlineDAO();
					OnlineVO member = new OnlineVO(tfid, tfpass, tfname, tfpho, selectedMonth, selectedDate);
					onlineDAO.join(member);
					
					///JOIN �����ؾߵ�
					JOptionPane.showMessageDialog(frame, message, "���� ��¥ ����", JOptionPane.INFORMATION_MESSAGE);
					
					
					
				}
			});
			datePanel.add(button);
		}

		return panel;
	}

	// ������ ����ϴ� �޼���
	public static int getDayOfWeek(int year, int month, int day) {
		int[] t = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
		if (month < 3) {
			year--;
		}
		return (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;
	}

	// �ش� ���� ������ ���� ���ϴ� �޼���
	public static int getLastDayOfMonth(int year, int month) {
		int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month == 2 && isLeapYear(year)) {
			return 29;
		}
		return daysInMonth[month - 1];
	}

	// �������� Ȯ���ϴ� �޼���
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	// �� �̸��� ��ȯ�ϴ� �޼���
	public static String getMonthName(int month) {
//      String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
//            "October", "November", "December" };
		String[] monthNames = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
		return monthNames[month - 1];
	}
}