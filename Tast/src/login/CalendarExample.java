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
		// 메시지 띄우기 청구날짜 지정해주세요/
		String msg = " ";
		msg = "예약날짜를 선택해주세요! ";
		JOptionPane.showMessageDialog(null, msg);
		this.tfid = tfid;
		this.tfpass = tfpass;
		this.tfname = tfname;
		this.tfpho = tfpho;
		
		// JFrame 생성
		frame = new JFrame("2023 Calendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(600, 300);

		// JPanel을 담을 BorderLayout을 사용한 전체 패널 생성
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// 12개의 월 패널을 담을 GridLayout을 사용한 월 패널 생성
		monthPanel = new JPanel();
		monthPanel.setLayout(new GridLayout(3, 4));

		// 1월부터 12월까지 월 패널 생성하여 월 패널에 추가
		for (int month = 1; month <= 12; month++) {
			JButton monthButton = new JButton(getMonthName(month));
			monthButton.setActionCommand(String.valueOf(month)); // 월을 버튼의 액션 명령으로 설정
			monthButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String command = e.getActionCommand();
					int selectedMonth = Integer.parseInt(command);
					showMonthCalendar(2023, selectedMonth); // 선택된 월의 달력 표시
				}
			});
			monthPanel.add(monthButton);
		}

		// 월 패널을 전체 패널에 추가
		mainPanel.add(monthPanel, BorderLayout.CENTER);

		// 전체 패널을 JFrame에 추가
		frame.add(mainPanel);

		// JFrame을 보여줌
		frame.setVisible(true);
	}

	// 월 패널을 생성하는 메서드
	public static void showMonthCalendar(int year, int month) {
		// 기존에 표시된 월 패널 삭제
		mainPanel.remove(monthPanel);

		// 선택된 월의 패널 생성
		selectedMonthPanel = createMonthPanel(year, month);

		// 선택된 월의 패널을 월 패널 위치에 추가
		mainPanel.add(selectedMonthPanel, BorderLayout.CENTER);

		// "Back" 버튼 생성 및 추가
		JButton backButton = new JButton("뒤로가기");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(selectedMonthPanel); // 선택된 월의 패널 제거
				mainPanel.add(monthPanel, BorderLayout.CENTER); // 월 패널을 다시 추가
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		mainPanel.add(backButton, BorderLayout.SOUTH);

		// 변경된 패널을 다시 그리기
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	// 월 패널을 생성하는 메서드
	public static JPanel createMonthPanel(int year, int month) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// 월 이름 표시
		JButton monthButton = new JButton(getMonthName(month));
		panel.add(monthButton, BorderLayout.NORTH);

		// 날짜를 표시할 GridLayout 생성
		GridLayout gridLayout = new GridLayout(0, 7);
		JPanel datePanel = new JPanel();
		datePanel.setLayout(gridLayout);
		panel.add(datePanel, BorderLayout.CENTER);

		// 해당 월의 첫 번째 날의 요일을 구함 (0: 일요일, 1: 월요일, ..., 6: 토요일)
		int startDayOfWeek = getDayOfWeek(year, month, 1);

		// 요일 이름 표시
		String[] dayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (String dayName : dayNames) {
			JButton dayButton = new JButton(dayName);
			dayButton.setEnabled(false);
			datePanel.add(dayButton);
		}

		// 빈 공백 채우기
		for (int i = 0; i < startDayOfWeek; i++) {
			JButton emptyButton = new JButton("");
			emptyButton.setEnabled(false);
			datePanel.add(emptyButton);
		}

		// 날짜 버튼으로 날짜 표시
		int lastDayOfMonth = getLastDayOfMonth(year, month);
		for (int day = 1; day <= lastDayOfMonth; day++) {
			JButton button = new JButton(String.valueOf(day));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//               JOptionPane.showMessageDialog(frame, "청구 날짜가 지정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					   JButton clickedButton = (JButton) e.getSource();
				        String selectedDate = clickedButton.getText();
				        String selectedMonth = getMonthName(month); // Get the selected month name
				        String message = "예약 날짜는 " + selectedMonth + " " + selectedDate + "일로 설정되었습니다.";
					
					
				
//					String test = Dao.join2(member);


				
								
					
					OnlineDAO onlineDAO = new OnlineDAO();
					OnlineVO member = new OnlineVO(tfid, tfpass, tfname, tfpho, selectedMonth, selectedDate);
					onlineDAO.join(member);
					
					///JOIN 지정해야됨
					JOptionPane.showMessageDialog(frame, message, "예약 날짜 지정", JOptionPane.INFORMATION_MESSAGE);
					
					
					
				}
			});
			datePanel.add(button);
		}

		return panel;
	}

	// 요일을 계산하는 메서드
	public static int getDayOfWeek(int year, int month, int day) {
		int[] t = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
		if (month < 3) {
			year--;
		}
		return (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;
	}

	// 해당 월의 마지막 날을 구하는 메서드
	public static int getLastDayOfMonth(int year, int month) {
		int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month == 2 && isLeapYear(year)) {
			return 29;
		}
		return daysInMonth[month - 1];
	}

	// 윤년인지 확인하는 메서드
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	// 월 이름을 반환하는 메서드
	public static String getMonthName(int month) {
//      String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
//            "October", "November", "December" };
		String[] monthNames = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
		return monthNames[month - 1];
	}
}