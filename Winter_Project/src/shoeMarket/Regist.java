package shoeMarket;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Regist extends JFrame {
	// Label
	String labelText[] = { "ID : ", "PASSWORD", "PASSWORD확인", "모드선택" };
	JLabel label[] = new JLabel[labelText.length];
	String modeText[] = { "관리자", "일반사용자" };
	JLabel empty = new JLabel("");

	// TextField
	JTextField loginTf = new JTextField("", 20);
	JPasswordField passwordTf = new JPasswordField(10);
	JPasswordField passwordCheck = new JPasswordField(10);

	// RadioButton
	JRadioButton mode[] = new JRadioButton[modeText.length];

	// JButton
	JButton okButton = new JButton("확인");
	JButton dupcheck = new JButton("중복확인");
	JButton exit = new JButton("나가기");

	// JPanel
	JPanel modecheck = new JPanel();
	JPanel iddupch = new JPanel();
	JPanel bottom = new JPanel();

	//
	public int usernum = 0;
	public ArrayList<UserInfo> userlist = new ArrayList<>();

	boolean idok;
	String newID = "";
	String newPw = "";
	String admingCode = "0919";// 관리자 코드
	boolean admin;

	Regist(int n, ArrayList user) {
		this.usernum = n;
		this.userlist = user;
		this.setTitle("회원가입");
		this.setSize(700, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(0, 2, 3, 3));

		for (int i = 0; i < labelText.length; i++) {
			label[i] = new JLabel(labelText[i]);
		}
		iddupch.setLayout(new GridLayout(0, 2, 3, 3));
		iddupch.add(loginTf);
		iddupch.add(dupcheck);

		this.add(label[0]);
		this.add(iddupch);
		this.add(label[1]);
		this.add(passwordTf);
		this.add(label[2]);
		this.add(passwordCheck);
		this.add(label[3]);
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < mode.length; i++) {
			mode[i] = new JRadioButton(modeText[i]);
			bg.add(mode[i]);
		}
		modecheck.setLayout(new GridLayout(0, 2, 2, 2));
		modecheck.add(mode[0]);
		modecheck.add(mode[1]);
		this.add(modecheck);

		bottom.setLayout(new FlowLayout());
		bottom.add(okButton);
		bottom.add(exit);
		this.add(empty);
		this.add(bottom);

		dupcheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idok = true;
				String id = loginTf.getText();
				if (id.equals("")) {
					empty.setText("ID 미입력");
					idok = false;
				} else {
					for (int i = 0; i < usernum; i++) {
						if (loginTf.getText().equals(userlist.get(i).id)) {
							empty.setText("ID 중복 : " + loginTf.getText());
							idok = false;
							loginTf.setText("");
							return;
						}
					}
					if (idok) {
						newID = loginTf.getText();
						empty.setText("사용가능 ID : " + newID);
						idok = true;
					}
				}

			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pw = new String(passwordTf.getPassword());
				String pw2 = new String(passwordCheck.getPassword());
				if (mode[0].isSelected()) {
					String newadcode = JOptionPane.showInputDialog(null, "AdminCode", "", JOptionPane.PLAIN_MESSAGE);
					if (idok == true && pw.equals(pw2) && admingCode.equals(newadcode)) {
						empty.setText("회원가입성공");
						admin = true;
						newPw = pw;
						userlist.add(new UserInfo(newID, newPw, admin));
						dispose();
						for (int i = 0; i < user.size(); i++) {
							System.out.println(userlist.get(i).id);
						}
					} else {
						empty.setText("회원가입실패");
					}
				} else if (mode[1].isSelected()) {
					if (idok == true && pw.equals(pw2)) {
						empty.setText("회원가입성공");
						admin = false;
						newPw = pw;
						userlist.add(new UserInfo(newID, newPw, admin));
						dispose();
						for (int i = 0; i < user.size(); i++) {
							System.out.println(userlist.get(i).id);
						}
					} else {
						empty.setText("회원가입실패");
					}
				} else {
					empty.setText("모드 미선택");
				}

			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		this.setVisible(true);
	}

}

class Admincheck {

}
