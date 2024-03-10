package shoeMarket;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener {

	// Label
	String labelText[] = { "ID : ", "PASSWORD : " };
	JLabel label[] = new JLabel[labelText.length];
	JLabel state = new JLabel();
	JLabel empty = new JLabel("");

	// TextField
	JTextField loginTf = new JTextField(10);
	JPasswordField passwordTf = new JPasswordField(10);

	// Button
	JButton loginBt = new JButton("LOGIN");
	JButton registBt = new JButton("회원가입");

	// list
	public ArrayList<UserInfo> user = new ArrayList<UserInfo>();
	public ArrayList<Shoeslist> Shoeslist = new ArrayList<>();
	public ArrayList<Purchaselist> purchaselist = new ArrayList<>();
	public ArrayList<Demandlist> demandlist = new ArrayList<>();

	public LoginPanel() {
		user.add(new UserInfo("kws06009", "nam", true)); // 관리자 계정
		user.add(new UserInfo("kws", "123", false)); // 사용자 계정

		setMachine();
		// LoginPanel
		this.setLayout(new GridLayout(0, 2, 5, 5));
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(labelText[i], 0);
		}
		this.add(label[0]);
		this.add(loginTf);
		this.add(label[1]);
		this.add(passwordTf);
		this.add(state);
		this.add(empty);
		this.add(loginBt);
		this.add(registBt);

		loginBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String inputpw = new String(passwordTf.getPassword());
				boolean idcheck = false;
				boolean pwcheck = false;

				for (UserInfo i : user) {
					String ipw = new String(i.getPassword());
					if (loginTf.getText().equals(i.getId()) && inputpw.equals(ipw)) {
						if (i.admin == true) {// 관리자 모드
							state.setText("");
							Admin admin = new Admin(Shoeslist, purchaselist, user, demandlist);
						} else if (i.admin == false) {// 일반 사용자 모드
							state.setText("");
							User user = new User(Shoeslist, purchaselist, demandlist, i.getId());
						}
						return;

					}

					else {
						state.setText("로그인 실패");

					}

				}

			}

		});

		registBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(user.size());
				Regist r = new Regist(user.size(), user);

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	void setMachine() {
		// String name, String brand, String target, String sex, String image, int
		// num_of_purchases, int price
		Shoeslist.add(new Shoeslist("나이키 프리미어 3", "나이키", "축구화", "남성", "Winter_Project/img/nike_premier_3.png", 7, 119000));
		Shoeslist.add(new Shoeslist("나이키 에어 조던 1 하이 OG", "나이키", "농구화", "남성", "Winter_Project/img/nike_air_jordan_1.jpg", 5, 219000));
		Shoeslist.add(new Shoeslist("나이키 인빈서블 3", "나이키", "러닝화", "남성", "Winter_Project/img/nike_invincible_3.jpg", 1, 209000));
		Shoeslist.add(new Shoeslist("아디다스 프레데터 애큐러시.1 AG", "아디다스", "축구화", "남성", "Winter_Project/img/adidas_predator.png", 7, 299000));
		Shoeslist.add(new Shoeslist("아디다스 리스폰스", "아디다스", "러닝화", "남성", "Winter_Project/img/adidas_response.png", 5, 89000));
		Shoeslist.add(new Shoeslist("아디다스 포럼 미드", "아디다스", "농구화", "남성", "Winter_Project/img/adidas_forum_mid.png", 1, 139000));
		Shoeslist.add(new Shoeslist("푸마 슈퍼텍 제로", "푸마", "러닝화", "남성", "Winter_Project/img/puma_Supertec-zero.png", 7, 34500));
		Shoeslist.add(new Shoeslist("푸마 블랙탑 라이더", "푸마", "러닝화", "남성", "Winter_Project/img/puma_Blktop-Rider.png", 5, 97300));
		Shoeslist.add(new Shoeslist("언더아머 UA 마그네티코 셀렉트 3 FG", "언더아머", "축구화", "남성", "Winter_Project/img/underarmour_UA-magnetico.png",
				1, 109000));
		Shoeslist.add(new Shoeslist("언더아머 커리 8", "언더아머", "농구화", "남성", "Winter_Project/img/underarmour_cury-8.png", 7, 119000));

	}

}
