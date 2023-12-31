package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame {
	private OnlineDAO Dao;
	protected String msg;

	public static void main(String[] args) {
		new Login();
//		 new setGUI();
//		CalendarExample.main(args);
	}

//아이디 찾기창
	public void findid() {

//		dao = new stmt;

		JFrame jf1 = new JFrame();
		jf1.setLocation(600, 300);
		jf1.setSize(400, 250);
		jf1.setTitle("아이디 찾기");
		jf1.setLayout(null);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel shid = new JLabel("이름  ");
		shid.setSize(70, 30);
		shid.setLocation(10, 15);
		shid.setHorizontalAlignment(JLabel.CENTER);

		jf1.add(shid);

		JTextField shid1 = new JTextField(10);
		shid1.setSize(340, 30);
		shid1.setLocation(25, 40);

		jf1.add(shid1);

		JLabel shph = new JLabel("휴대번호 입력('-')제외 ");
		shph.setSize(130, 30);
		shph.setLocation(25, 65);
		shph.setHorizontalAlignment(JLabel.CENTER);

		jf1.add(shph);

		JTextField shph1 = new JTextField(10);
		shph1.setSize(340, 30);
		shph1.setLocation(25, 90);

		jf1.add(shph1);

		JButton bott = new JButton("확인");
		bott.setSize(340, 30);
		bott.setLocation(25, 150);

		jf1.add(bott);
		jf1.setVisible(true);

		bott.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OnlineDAO nail9 = new OnlineDAO();
				OnlineVO nail8 = new OnlineVO();
				String msg = "";

				nail8.setName(shid1.getText());
				nail8.setPhone(shph1.getText());
				nail8 = nail9.findidd(nail8);

				if (nail8.getName() == null || nail8.getPhone() == null) {
					msg = "입력 되지 않은 사항이 있습니다.";
				} else if (!nail8.getName().equals(shid1.getText())) {
					msg = "입력된 이름이 틀렸습니다. ";
				} else if (!nail8.getPhone().equals(shph1.getText())) {
					msg = "입력된 핸드폰번호가 틀렸습니다.";
				} else {
					msg = "귀하의 아이디는 " + " '" + nail8.getId() + " '" + " 입니다.";
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});

	}

//	OnlineVO findMember = nail.findpass(nail2);

//	nail2.setPwd(shph4.getText());

	// 아이디찾기 -휴대폰 인증창
	public void findphone() {
		JFrame jf4 = new JFrame();
		jf4.setLocation(600, 300);
		jf4.setSize(380, 270);
		jf4.setTitle("휴대폰인증");
		jf4.setLayout(null);
		jf4.setVisible(true);

		JLabel sk = new JLabel("SK");
		sk.setSize(70, 30);
		sk.setLocation(85, 15);
		sk.setFont(new Font("고딕", Font.BOLD, 15));

		jf4.add(sk);

		JCheckBox sk1 = new JCheckBox();
		sk1.setSize(20, 20);
		sk1.setLocation(55, 20);
		jf4.add(sk1);

		JLabel LG = new JLabel("LG");
		LG.setSize(70, 30);
		LG.setLocation(145, 15);
		LG.setFont(new Font("고딕", Font.BOLD, 15));

		jf4.add(LG);

		JCheckBox LG1 = new JCheckBox();
		LG1.setSize(20, 20);
		LG1.setLocation(115, 20);
		jf4.add(LG1);

		JLabel KT = new JLabel("KT");
		KT.setSize(70, 30);
		KT.setLocation(205, 15);
		KT.setFont(new Font("고딕", Font.BOLD, 15));

		jf4.add(KT);

		JCheckBox KT1 = new JCheckBox();
		KT1.setSize(20, 20);
		KT1.setLocation(175, 20);
		jf4.add(KT1);

		JLabel R = new JLabel("알뜰폰");
		R.setSize(70, 30);
		R.setLocation(265, 15);
		R.setFont(new Font("고딕", Font.BOLD, 15));

		jf4.add(R);

		JCheckBox R1 = new JCheckBox();
		R1.setSize(20, 20);
		R1.setLocation(235, 20);
		jf4.add(R1);

		JRadioButton rd = new JRadioButton("전체동의");
		rd.setSize(150, 30);
		rd.setLocation(30, 80);
		rd.setFont(new Font("고딕", Font.BOLD, 15));

		JRadioButton rd1 = new JRadioButton("개인정보이용동의");
		rd1.setSize(150, 30);
		rd1.setLocation(30, 110);

		JRadioButton rd2 = new JRadioButton("고유식별정보처리동의");
		rd2.setSize(150, 30);
		rd2.setLocation(180, 110);

		JRadioButton rd3 = new JRadioButton("서비스이용약관동의");
		rd3.setSize(150, 30);
		rd3.setLocation(30, 140);

		JRadioButton rd4 = new JRadioButton("통신사이용약관동의");
		rd4.setSize(150, 30);
		rd4.setLocation(180, 140);

		JButton SNSCK = new JButton("문자(SNS)로 인증");
		SNSCK.setSize(340, 30);
		SNSCK.setLocation(13, 190);
		SNSCK.setHorizontalAlignment(JLabel.CENTER);

		jf4.add(SNSCK);

//            // 1번 라디오 버튼 눌러져있도록
//            rd1.setSelected(true);

		// 라디오 버튼을 그룹화 하기위한 객체 생성
		ButtonGroup groupRd = new ButtonGroup();

		// 그룹에 라디오 버튼 포함시킨다.
		groupRd.add(rd1);
		groupRd.add(rd2);
		groupRd.add(rd3);
		groupRd.add(rd4);

		jf4.add(SNSCK);
		jf4.add(rd);
		jf4.add(rd1);
		jf4.add(rd2);
		jf4.add(rd3);
		jf4.add(rd4);

		// 윈도우 창 크기 설정(가로, 세로)
//            setSize(500, 500);
		SNSCK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				findsns();
			}
		});

	}

	// sns 인증창
	public void findsns() {
		JFrame jf6 = new JFrame();
		jf6.setLocation(600, 250);
		jf6.setSize(410, 300);
		jf6.setTitle("SNS 인증");
		jf6.setLayout(null);
//			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel SNS9 = new JLabel("SNS로 인증번호 전송하였습니다. ");
		SNS9.setSize(300, 50);
		SNS9.setLocation(45, 5);
		SNS9.setHorizontalAlignment(JLabel.CENTER);
		SNS9.setFont(new Font("고딕", Font.BOLD, 18));

		jf6.add(SNS9);

		JLabel SNSNUM = new JLabel("인증번호");
		SNSNUM.setSize(70, 30);
		SNSNUM.setLocation(35, 60);
		SNSNUM.setHorizontalAlignment(JLabel.CENTER);

		jf6.add(SNSNUM);

		JTextField SNSNUM1 = new JTextField(10);
		SNSNUM1.setSize(300, 30);
		SNSNUM1.setLocation(40, 90);

		jf6.add(SNSNUM1);

		JLabel SNSNUM2 = new JLabel("★인증문자가 오지 않으면 수신차단 메시지 또는 스팸함을 확인해 주세요.");
		SNSNUM2.setSize(370, 30);
		SNSNUM2.setLocation(40, 120);
		SNSNUM2.setForeground(Color.red);
//			SNSNUM2.setHorizontalAlignment(JLabel.CENTER);
		SNSNUM2.setFont(new Font("고딕", Font.BOLD, 9));

		jf6.add(SNSNUM2);

		JButton bott10 = new JButton("재전송");
		bott10.setSize(80, 30);
		bott10.setLocation(150, 150);

		jf6.add(bott10);

		JButton bott9 = new JButton("1확인");
		bott9.setSize(340, 30);
		bott9.setLocation(25, 210);

		jf6.add(bott9);

		jf6.setVisible(true);
	}

	// 비밀번호 찾기창

	public void findpass() {
		JFrame jf2 = new JFrame();
		jf2.setLocation(600, 300);
		jf2.setSize(400, 250);
		jf2.setTitle("비밀번호 찾기");
		jf2.setLayout(null);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel shid3 = new JLabel("아이디  ");
		shid3.setSize(70, 30);
		shid3.setLocation(10, 15);
		shid3.setHorizontalAlignment(JLabel.CENTER);

		jf2.add(shid3);

		JTextField shid4 = new JTextField(10);
		shid4.setSize(340, 30);
		shid4.setLocation(25, 40);

		jf2.add(shid4);

		JLabel shph3 = new JLabel("휴대번호 입력('-')제외 ");
		shph3.setSize(130, 30);
		shph3.setLocation(25, 65);
		shph3.setHorizontalAlignment(JLabel.CENTER);

		jf2.add(shph3);

		JTextField shph4 = new JTextField(10);
		shph4.setSize(340, 30);
		shph4.setLocation(25, 90);

		jf2.add(shph4);

		JButton bott1 = new JButton("확인");
		bott1.setSize(340, 30);
		bott1.setLocation(25, 150);

		jf2.add(bott1);

		jf2.setVisible(true);

		bott1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OnlineDAO nail = new OnlineDAO();
				OnlineVO nail2 = new OnlineVO();
				String msg = "";
//				JTextField shph4 = new JTextField(10);
//				shph4.setSize(340, 30);
//				shph4.setLocation(25, 90);
//
//				jf2.add(shph4);

				nail2.setId(shid4.getText());
				nail2.setPhone(shph4.getText());
				nail2 = nail.findpass(nail2);

//				OnlineVO findMember = nail.findpass(nail2);

//				nail2.setPwd(shph4.getText());

				if (nail2.getId() == null || nail2.getPhone() == null) {
					msg = "입력되지 않은 사항이 있습니다.";
				} else if (!nail2.getId().equals(shid4.getText())) {
					msg = "입력된 아이디가 틀렸습니다. ";
				} else if (!nail2.getPhone().equals(shph4.getText())) {
					msg = "입력된 핸드폰번호가 틀렸습니다.";
				} else {
					msg = "귀하의 비밀번호는 " + "' " + nail2.getPwd() + "' " + " 입니다.";
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});

	}

//회원가입 창
	public void findjoin() {
		JFrame jf3 = new JFrame();
		jf3.setLocation(600, 300);
		jf3.setSize(400, 400);
		jf3.setTitle("회원가입");
		jf3.setLayout(null);

		JLabel labeName = new JLabel("이름   ");
		labeName.setSize(70, 30);
		labeName.setLocation(8, 15);
		labeName.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(labeName);

		JTextField nameValue = new JTextField(10);
		nameValue.setSize(340, 30);
		nameValue.setLocation(25, 40);

		jf3.add(nameValue);

		JLabel id1 = new JLabel("아이디");
		id1.setSize(70, 30);
		id1.setLocation(10, 65);
		id1.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(id1);

		JTextField id2 = new JTextField(10);
		id2.setSize(340, 30);
		id2.setLocation(25, 90);

		jf3.add(id2);

		JLabel pass = new JLabel("비밀번호");
		pass.setSize(70, 30);
		pass.setLocation(15, 115);
		pass.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(pass);

		JTextField pass1 = new JTextField(20);
		pass1.setSize(340, 30);
		pass1.setLocation(25, 140);

		jf3.add(pass1);

		JLabel pass2 = new JLabel("비밀번호 재확인");
		pass2.setSize(100, 30);
		pass2.setLocation(18, 165);
		pass2.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(pass2);

		JTextField pass3 = new JTextField(20);
		pass3.setSize(340, 30);
		pass3.setLocation(25, 190);

		jf3.add(pass3);

		JLabel phone = new JLabel("휴대번호");
		phone.setSize(70, 30);
		phone.setLocation(13, 215);
		phone.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(phone);

		JTextField phone1 = new JTextField(10);
		phone1.setSize(340, 30);
		phone1.setLocation(25, 240);

		jf3.add(phone1);

		JLabel SNS = new JLabel("SNS발송");
		SNS.setSize(70, 30);
		SNS.setLocation(12, 265);
		SNS.setHorizontalAlignment(JLabel.CENTER);

		jf3.add(SNS);

		JLabel che = new JLabel("수신");
		che.setSize(70, 30);
		che.setLocation(250, 265);

		jf3.add(che);

		JCheckBox che1 = new JCheckBox();
		che1.setSize(20, 20);
		che1.setLocation(225, 270);
		jf3.add(che1);

		JLabel che2 = new JLabel("수신거부");
		che2.setSize(70, 30);
		che2.setLocation(310, 265);

		jf3.add(che2);

		JCheckBox che3 = new JCheckBox();
		che3.setSize(20, 20);
		che3.setLocation(285, 270);
		jf3.add(che3);

		JButton bott2 = new JButton("확인");
		bott2.setSize(70, 30);
		bott2.setLocation(290, 315);

		jf3.add(bott2);

		jf3.setVisible(true);

		bott2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tfid = id2.getText();
				String tfpass = pass1.getText();
				String tfname = nameValue.getText();
				String tfpho = phone1.getText();

				OnlineVO member = new OnlineVO(tfid, tfpass, tfname, tfpho);
				OnlineDAO dao = new OnlineDAO();
//				System.out.print("asdas");

				dao.joinId(member);
//				dao.join1(member);
//				dao.joinId(member);

//				CalendarExample cd = new CalendarExample();
//				cd.createAndShowGUI(tfid, tfpass, tfname, tfpho);
			}
		});
//		
	}

//로그인창에 들어간것들
	public Login() {

		Dao = new OnlineDAO();

		JFrame jf = new JFrame();
		jf.setLocation(600, 300);
		jf.setSize(550, 400);
		jf.setTitle("Login");
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel title1 = new JLabel("HaHee Nail");
		title1.setBounds(100, 45, 400, 60);
		title1.setFont(title1.getFont().deriveFont(50.0f));
		title1.setForeground(Color.blue);
		jf.add(title1);

		JLabel sh = new JLabel("ID  ");
		sh.setSize(80, 30);
		sh.setLocation(20, 125);
		sh.setHorizontalAlignment(JLabel.CENTER);

		jf.add(sh);

		JTextField id = new JTextField(10);
		id.setSize(350, 30);
		id.setLocation(50, 150);

		jf.add(id);

		JLabel password1 = new JLabel("Password ");
		password1.setSize(80, 30);
		password1.setLocation(50, 175);

		jf.add(password1);

		JTextField password11 = new JTextField(20);
		password11.setSize(350, 30);
		password11.setLocation(50, 200);

		jf.add(password11);

		JLabel sh2 = new JLabel("자동로그인");
		sh2.setSize(110, 25);
		sh2.setLocation(95, 300);

		jf.add(sh2);

		JCheckBox chk = new JCheckBox();
		chk.setSize(20, 20);
		chk.setLocation(70, 301);
		jf.add(chk);

//      JFrame frame = new JFrame();
//      frame.setSize(300,200);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      
////      JPanel pa = new JPanel();
//      
//      JCheckBox chk = new JCheckBox("자동로그인");
//      
//      frame.add(chk);
//      frame.setVisible(true);
//      jf.add(chk);

		// 로그인창에 나오는 버튼들
		JButton bot1 = new JButton("회원가입");
		bot1.setSize(90, 25);
		bot1.setLocation(420, 10);

		jf.add(bot1);

		JButton bot2 = new JButton("아이디찾기");
		bot2.setSize(110, 25);
		bot2.setLocation(175, 300);

		jf.add(bot2);

		JButton bot3 = new JButton("비밀번호찾기");
		bot3.setSize(110, 25);
		bot3.setLocation(290, 300);

		jf.add(bot3);

		JButton bot4 = new JButton("LOGIN");
		bot4.setSize(90, 90);
		bot4.setLocation(420, 140);
//		bot4.setIcon(new Icon("C:\\gggggg.png");
		jf.add(bot4);

		jf.setVisible(true);

//		AbstractButton bott;
		// 버튼-------------------------------------

		bot1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				findjoin();
			}
		});

		bot2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				findid();
			}
		});

		bot3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				findpass();
			}
		});

		bot4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(id.getText() + " : " + password11.getText());

				String strId = id.getText();
				Dao = new OnlineDAO();
				ArrayList<OnlineVO> list = Dao.list(strId);
				OnlineVO OB = new OnlineVO();

//				nail2.setId(shid4.getText());
//				nail2.setPhone(shph4.getText());
//				nail2 = nail.findpass(nail2);
//
////				OnlineVO findMember = nail.findpass(nail2);
//
////				nail2.setPwd(shph4.getText());
//
//				if (nail2.getId()==null || nail2.getPhone()==null) {
//					msg = "입력되지 않은 사항이 있습니다.";
//				} else if (!nail2.getId().equals(shid4.getText())) {
//					msg = "입력된 아이디가 틀렸습니다. ";
//				} else if (!nail2.getPhone().equals(shph4.getText())) {
//					msg = "입력된 핸드폰번호가 틀렸습니다.";
//				} else {
//					msg = "귀하의 비밀번호는 " + "' " + nail2.getPwd() + "' " + " 입니다.";
//				}
//				JOptionPane.showMessageDialog(null, msg);
//			}
//		});
//
//	}
				if (list.size()==0) {
					msg = "ID가 틀렸습니다. 다시 입력하세요.";
					JOptionPane.showMessageDialog(null, msg);
				
				}	else if (list.get(0).getPositions().equals("Master")) {
					System.out.println("마스터 프레임 오픈");
					Master.master(OB);
				} else {
//					new showTimeSelection(String selectedMonth, String selectedDate);

					if (list.size() == 1) {
						OnlineVO data = (OnlineVO) list.get(0);
						String id = data.getId();
						String pwd = data.getPwd();
						String name = data.getName();
						String phone = data.getPhone();
//						String name = data.getName();
						String msg = " ";
						System.out.println("DB ==> " + id + " : " + pwd);

						if (password11.getText().equals(pwd)) {
							msg = "로그인이 되었습니다!";
							JOptionPane.showMessageDialog(null, msg);

							JFrame picture = new JFrame();
							picture.setLocation(100, 100);
							picture.setSize(800, 800);
							picture.setTitle("★★★이달의 추천 디자인★★★");
							picture.setLayout(null);

							try {
								picture.setContentPane(
										new JLabel(new ImageIcon(ImageIO.read(new File("C:\\NAIL.jpg")))));
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							picture.setVisible(true);

							CalendarExample cd = new CalendarExample();

							cd.createAndShowGUI(data.getId(), data.getPwd(), data.getName(), data.getPhone());

						} 
						else if (!password11.getText().equals(pwd)) {
							msg = "비밀번호를 다시 입력하세요.";
							JOptionPane.showMessageDialog(null, msg);
						}
//						else if (!(sh.getText().equals(id))) {
//							msg = "ID가 틀렸습니다. 다시 입력하세요.";
//							JOptionPane.showMessageDialog(null, msg);
//						}

					}
				}

//				if (list.size() == 1) {
//					OnlineVO data = (OnlineVO) list.get(0);
//					String id = data.getId();
//					String pwd = data.getPwd();
//					String name = data.getName();
//					String phone = data.getPhone();
////					String name = data.getName();
//					String msg = " ";
//					System.out.println("DB ==> " + id + " : " + pwd);
//
//					if (password11.getText().equals(pwd)) {
//						msg = "로그인이 되었습니다!";
//						JOptionPane.showMessageDialog(null, msg);
//
//						JFrame picture = new JFrame();
//						picture.setLocation(100, 100);
//						picture.setSize(800, 800);
//						picture.setTitle("★★★이달의 추천 디자인★★★");
//						picture.setLayout(null);
//
//						try {
//							picture.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\NAIL.jpg")))));
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//
//						picture.setVisible(true);
//
//						CalendarExample cd = new CalendarExample();
//
//						cd.createAndShowGUI(data.getId(), data.getPwd(), data.getName(), data.getPhone());
//
//					} else if (!password11.getText().equals(pwd)) {
//						msg = "비밀번호를 다시 입력하세요.";
//						JOptionPane.showMessageDialog(null, msg);
//					}
//				} else if (!(id.getText().equals(id))) {
//					msg = "ID가 틀렸습니다. 다시 입력하세요.";
//					JOptionPane.showMessageDialog(null, msg);
//
//				}
			}
		});

	}

}
