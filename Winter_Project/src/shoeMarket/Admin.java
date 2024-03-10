package shoeMarket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Admin extends JFrame implements ActionListener, MouseListener {

	// panel
	JPanel buttonpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	JPanel insertpanel = new JPanel();
	JPanel sbutton = new JPanel();
	JPanel salepanel = new JPanel();
	JPanel usertoppanel = new JPanel();
	JPanel botdeletepanel = new JPanel();

	// list
	public ArrayList<Shoeslist> shoeslist = new ArrayList<>();
	public ArrayList<Purchaselist> saleslist = new ArrayList<>();
	public ArrayList<UserInfo> userlist = new ArrayList<>();
	public ArrayList<Demandlist> demandlist = new ArrayList<>();
	ArrayList<Integer> index = new ArrayList<>(); // 사용자 삭제시 사용
	String insertinfotext[] = { "이름 : ", "브랜드 : ", "카테고리 : ", "성별 : ", "가격 : ", "구매횟수 : " };
	String buttontext[] = { "상품정보확인", "상품정보등록", "상품정보삭제", "판매목록", "회원정보", "문의 확인" };

	// textfield

	JTextField infotf[] = new JTextField[insertinfotext.length];

	// JLabel
	JLabel inputinfo[] = new JLabel[insertinfotext.length];
	JLabel err = new JLabel("");
	JLabel empty1 = new JLabel("");
	JLabel deletech = new JLabel("");
	JLabel demandid[];
	JLabel demandtitle[];

	// button

	JButton button[] = new JButton[buttontext.length];
	String usertext[] = { "모두보기", "관리자", "사용자" };
	JButton userbutton[] = new JButton[usertext.length];
	JButton deletebutton[]; // user정보 삭제 버튼
	JButton delbut = new JButton("삭제"); // 기구 삭제 버튼
	JButton savebutton = new JButton("등록");
	JButton demanddelbutton[]; // 문의사항 삭제 버튼

	// RadioButton
	JRadioButton delete_rbutton[];
	//
	int deletebuttoncnt;

	Admin(ArrayList<Shoeslist> m_list, ArrayList<Purchaselist> p_list, ArrayList<UserInfo> user,
			ArrayList<Demandlist> demand) {
		this.setTitle("관리자");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		// list 초기 설정
		shoeslist = m_list;
		saleslist = p_list;
		userlist = user;
		demandlist = demand;
		// deletebutton = new JButton[userlist.size()]; // 사용자 정보 삭제 버튼

		// demandid = new JLabel[demandlist.size()];
		// demandtitle = new JLabel[demandlist.size()];
		// demanddelbutton = new JButton[demandlist.size()];

		// buttonpanel
		buttonpanel.setLayout(new GridLayout(0, 1, 5, 20));
		buttonpanel.setBorder(BorderFactory.createTitledBorder("메뉴"));
		buttonpanel.add(new JLabel(""));
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(buttontext[i]);
			button[i].addActionListener(this);
			buttonpanel.add(button[i]);

		}
		buttonpanel.add(new JLabel(""));
		this.add(buttonpanel, BorderLayout.WEST);

		// mainpanel
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setBorder(BorderFactory.createTitledBorder("main"));

		// 기구 정보 등록 기본
		JPanel minfo;// 기구 정보
		insertpanel.setLayout(new GridLayout(0, 1, 5, 5));
		for (int i = 0; i < insertinfotext.length; i++) {
			inputinfo[i] = new JLabel(insertinfotext[i]);
			infotf[i] = new JTextField("", 20);
		}

		// save 버튼 패널
		sbutton.setLayout(new GridLayout(0, 3, 30, 5));
		sbutton.add(empty1);
		sbutton.add(savebutton);
		savebutton.addActionListener(this);
		sbutton.add(new JLabel(""));
		// 기구 정보 받는 패널
		for (int i = 0; i < insertinfotext.length; i++) {
			minfo = new JPanel();
			minfo.setLayout(new FlowLayout());
			minfo.add(inputinfo[i]);
			minfo.add(infotf[i]);
			insertpanel.add(minfo);
		}

		// 기구 정보 삭제 버튼 패널
		botdeletepanel.add(new JLabel(""));
		botdeletepanel.add(new JLabel(""));
		botdeletepanel.add(delbut);
		delbut.addActionListener(this);
		botdeletepanel.add(new JLabel(""));
		botdeletepanel.add(deletech);

		// user 정보확인 top 패널
		for (int i = 0; i < usertext.length; i++) {
			userbutton[i] = new JButton(usertext[i]);
			userbutton[i].addActionListener(this);
		}
		usertoppanel.setLayout(new GridLayout(0, 3, 10, 10));
		for (int i = 0; i < usertext.length; i++) {
			usertoppanel.add(userbutton[i]);
		}
		usertoppanel.setBorder(BorderFactory.createTitledBorder("선택"));
		usertoppanel.setVisible(false);
		this.add(usertoppanel, BorderLayout.NORTH);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 기구 정보 확인
		if (e.getSource() == button[0]) {
			usertoppanel.setVisible(false);
			mainpanel.removeAll();
			DefaultListModel<String> mlist = new DefaultListModel<>();
			for (int i = 0; i < shoeslist.size(); i++) {
				mlist.addElement(shoeslist.get(i).toString());

			}

			JList<String> list2 = new JList<String>(mlist);
			list2.setBounds(100, 100, 75, 75);
			mainpanel.add(list2);
			this.add(mainpanel, BorderLayout.CENTER);

			reset();

		}

		// 기구 정보 등록
		if (e.getSource() == button[1]) {
			usertoppanel.setVisible(false);
			mainpanel.removeAll();
			empty1.setText("");
			mainpanel.add(sbutton, BorderLayout.SOUTH);
			mainpanel.add(insertpanel, BorderLayout.CENTER);
			this.add(mainpanel, BorderLayout.CENTER);

			reset();
		}

		// 기구 등록 저장 버튼
		if (e.getSource() == savebutton) {
			boolean ok = false;
			String name = infotf[0].getText();
			String brand = infotf[1].getText();
			String shoesCategory = infotf[2].getText();
			String sex = infotf[3].getText();

			try {

				int price = Integer.parseInt(infotf[4].getText());
				ok = true;
				if (ok == true) {

					if (!name.equals("") && !brand.equals("") && !shoesCategory.equals("") && !sex.equals("")) {
						shoeslist.add(new Shoeslist(name, brand, shoesCategory, sex, price));
						empty1.setText("등록성공");
					} else {
						empty1.setText("등록실패");
					}
				}
				for (int i = 0; i < infotf.length; i++) {
					infotf[i].setText("");
				}
			} catch (Exception ex) {
				empty1.setText("가격은 숫자");
				for (int i = 0; i < infotf.length; i++) {
					infotf[i].setText("");
				}

			}
		}

		// 기구 정보삭제
		if (e.getSource() == button[2]) {
			usertoppanel.setVisible(false);
			mainpanel.removeAll();
			deletech.setText("");
			setdeletepanel();
		}

		//
		if (e.getSource() == delbut) {
			for (int i = 0; i < shoeslist.size(); i++) {
				if (delete_rbutton[i].isSelected()) {
					String deletemachine = shoeslist.get(i).name;

					shoeslist.remove(i);
					deletech.setText(deletemachine + " 삭제");
					setdeletepanel();
				}
			}
		}
		// 판매목록
		if (e.getSource() == button[3]) {
			usertoppanel.setVisible(false);
			mainpanel.removeAll();
			JPanel salestop = new JPanel();
			salestop.setLayout(new GridLayout(0, 4, 5, 5));
			salestop.setBorder(BorderFactory.createTitledBorder(""));

			// salespanel
			JPanel salespanel = new JPanel();
			salespanel.setLayout(new GridLayout(0, 4, 5, 5));

			// 판매목록 toppanel
			String[] saleinfotext = { "이름", "브랜드", "가격", "id" };
			JLabel saleinfo[] = new JLabel[saleinfotext.length];
			for (int i = 0; i < saleinfotext.length; i++) {
				saleinfo[i] = new JLabel(saleinfotext[i]);
				salestop.add(saleinfo[i]);
			}
			mainpanel.add(salestop, BorderLayout.NORTH);

			// 판매금액 패널
			int totalsales = 0;
			JPanel salesbottom = new JPanel();
			salesbottom.setLayout(new GridLayout(0, 4, 10, 10));
			JLabel salesprice = new JLabel(" ", 0);
			JLabel tsalesprice = new JLabel("판매금액 : ", 0);
			salesbottom.add(new JLabel(" "));
			salesbottom.add(tsalesprice);
			salesbottom.add(salesprice);
			salesbottom.add(new JLabel(" "));
			salesbottom.setBorder(BorderFactory.createTitledBorder("판매금액"));
			mainpanel.add(salesbottom, BorderLayout.SOUTH);

			// 판매목록 리스트
			for (int i = 0; i < saleslist.size(); i++) {
				saleinfo[0] = new JLabel(saleslist.get(i).name);
				salespanel.add(saleinfo[0]);
				saleinfo[1] = new JLabel(saleslist.get(i).brand);
				salespanel.add(saleinfo[1]);
				saleinfo[2] = new JLabel(Integer.toString(saleslist.get(i).price));
				totalsales = totalsales + saleslist.get(i).price;
				salespanel.add(saleinfo[2]);
				saleinfo[3] = new JLabel(saleslist.get(i).id);
				salespanel.add(saleinfo[3]);
			}
			//
			salesprice.setText(Integer.toString(totalsales));

			for (int i = saleslist.size(); i < 12; i++) {
				for (int j = 0; j < saleinfotext.length; j++) {
					saleinfo[j] = new JLabel(" ");
					salespanel.add(saleinfo[j]);
				}
			}

			mainpanel.add(salespanel, BorderLayout.CENTER);

			this.add(mainpanel, BorderLayout.CENTER);

			reset();
		}

		// 회원 정보 확인
		if (e.getSource() == button[4]) {
			deletebutton = new JButton[userlist.size()]; // 사용자 정보 삭제 버튼
			mainpanel.removeAll();
			// top
			usertoppanel.setVisible(true);

			reset();

		}

		// userbutton 모드 누를때마다 option 변경
		for (int i = 0; i < userbutton.length; i++) {
			if (e.getSource() == userbutton[i]) {
				// mainpanel
				JPanel ulistpanel = new JPanel();
				String ulisttext[] = { "id", "pw", "관리자여부", "삭제" };
				JLabel ulistlabel[] = new JLabel[4];
				int cnt = 0;
				mainpanel.removeAll();
				if (i == 0 || i == 1) {

					ulistpanel.setLayout(new GridLayout(0, 3, 5, 5));
					for (int j = 0; j < 3; j++) {
						ulistlabel[j] = new JLabel(ulisttext[j]);
						ulistlabel[j].setBorder(BorderFactory.createTitledBorder(""));
						ulistpanel.add(ulistlabel[j]);
					}
					// 모두보기
					if (i == 0) {
						for (int j = 0; j < userlist.size(); j++) {
							ulistlabel[0] = new JLabel(userlist.get(j).id);
							ulistpanel.add(ulistlabel[0]);
							ulistlabel[1] = new JLabel(userlist.get(j).password);
							ulistpanel.add(ulistlabel[1]);
							ulistlabel[2] = new JLabel(Boolean.toString(userlist.get(j).admin));
							ulistpanel.add(ulistlabel[2]);
							cnt++;
						}
					}
					// 관리자
					else {
						for (int j = 0; j < userlist.size(); j++) {
							if (userlist.get(j).admin == true) {
								ulistlabel[0] = new JLabel(userlist.get(j).id);
								ulistpanel.add(ulistlabel[0]);
								ulistlabel[1] = new JLabel(userlist.get(j).password);
								ulistpanel.add(ulistlabel[1]);
								ulistlabel[2] = new JLabel(Boolean.toString(userlist.get(j).admin));
								ulistpanel.add(ulistlabel[2]);
								cnt++;
							}
						}
					}

					for (int j = cnt; j < 20; j++) {
						for (int z = 0; z < 3; z++) {
							ulistpanel.add(new JLabel(" "));
						}
					}
					mainpanel.add(ulistpanel, BorderLayout.CENTER);
					this.add(mainpanel, BorderLayout.CENTER);
					reset();

				}
				// 사용자 보기
				else {
					ulistpanel.setLayout(new GridLayout(0, 4, 5, 5));
					index.clear();
					mainpanel.removeAll();
					deletebuttoncnt = 0;
					for (int j = 0; j < 4; j++) {
						ulistlabel[j] = new JLabel(ulisttext[j]);
						ulistlabel[j].setBorder(BorderFactory.createTitledBorder(""));
						ulistpanel.add(ulistlabel[j]);
					}

					for (int j = 0; j < userlist.size(); j++) {
						if (userlist.get(j).admin == false) {
							ulistlabel[0] = new JLabel(userlist.get(j).id);
							ulistpanel.add(ulistlabel[0]);
							ulistlabel[1] = new JLabel(userlist.get(j).password);
							ulistpanel.add(ulistlabel[1]);
							ulistlabel[2] = new JLabel(Boolean.toString(userlist.get(j).admin));
							ulistpanel.add(ulistlabel[2]);
							deletebutton[deletebuttoncnt] = new JButton("삭제");
							deletebutton[deletebuttoncnt].addActionListener(this);
							ulistpanel.add(deletebutton[deletebuttoncnt]);
							index.add(j);
							cnt++;
							deletebuttoncnt++;
						}
					}
					for (int j = deletebuttoncnt; j < 15; j++) {
						for (int z = 0; z < 4; z++) {
							ulistpanel.add(new JLabel(" "));
						}
					}

					mainpanel.add(ulistpanel, BorderLayout.CENTER);
					this.add(mainpanel, BorderLayout.CENTER);
					reset();
				}
			}
		}

		for (int i = 0; i < deletebuttoncnt; i++) {
			if (e.getSource() == deletebutton[i]) {
				userlist.remove((int) index.get(i));
			}
		}

		// 문의하기 확인
		if (e.getSource() == button[5]) {
			demandid = new JLabel[demandlist.size()];
			demandtitle = new JLabel[demandlist.size()];
			demanddelbutton = new JButton[demandlist.size()];

			usertoppanel.setVisible(false);
			mainpanel.removeAll();
			setdemandpanel();
			reset();
		}

		// 문의하기 삭제
		for (int i = 0; i < demandlist.size(); i++) {
			if (e.getSource() == demanddelbutton[i]) {
				mainpanel.removeAll();
				demandlist.remove(i);
				setdemandpanel();
				reset();
			}
		}
	}

	// 요구 패널 리스트 작성
	void setdemandpanel() {
		// toppanel
		JPanel dtoppanel = new JPanel();
		dtoppanel.setLayout(new GridLayout(0, 3, 5, 5));
		String[] demandtext = { "id", "제목", "삭제 버튼" };
		JLabel demandlabel[] = new JLabel[demandtext.length];
		JLabel did = new JLabel("id", 0);
		for (int i = 0; i < demandtext.length; i++) {
			demandlabel[i] = new JLabel(demandtext[i]);
			dtoppanel.add(demandlabel[i]);
			demandlabel[i].setBorder(BorderFactory.createTitledBorder(""));
		}
		mainpanel.add(dtoppanel, BorderLayout.NORTH);

		// mainpanel

		JPanel dmainpanel = new JPanel();
		dmainpanel.setLayout(new GridLayout(0, 3, 5, 5));
		System.out.println(demandlist.size());
		for (int i = 0; i < demandlist.size(); i++) {
			demandid[i] = new JLabel(demandlist.get(i).id);
			dmainpanel.add(demandid[i]);
			demandtitle[i] = new JLabel(demandlist.get(i).title);
			demandtitle[i].addMouseListener(this);
			dmainpanel.add(demandtitle[i]);
			demanddelbutton[i] = new JButton("삭제");
			demanddelbutton[i].addActionListener(this);
			dmainpanel.add(demanddelbutton[i]);
		}

		for (int i = demandlist.size(); i < 15; i++) {
			for (int z = 0; z < demandtext.length; z++) {
				dmainpanel.add(new JLabel(""));
			}
		}

		mainpanel.add(dmainpanel, BorderLayout.CENTER);
		this.add(mainpanel, BorderLayout.CENTER);
		reset();
	}

	void setdeletepanel() {

		ButtonGroup bg = new ButtonGroup();
		mainpanel.removeAll();

		mainpanel.add(botdeletepanel, BorderLayout.SOUTH);
		delete_rbutton = new JRadioButton[shoeslist.size()];// 기구 정보 삭제 라디오 버튼
		// 삭제 panel
		JPanel deletepanel = new JPanel();
		// 삭제 label
		String[] deletetext = { "이름", "브랜드", "삭제버튼" };
		JLabel deletelabel[] = new JLabel[deletetext.length];
		deletepanel.setLayout(new GridLayout(0, deletetext.length, 5, 5));

		for (int i = 0; i < deletetext.length; i++) {
			deletelabel[i] = new JLabel(deletetext[i]);
			deletepanel.add(deletelabel[i]);
		}

		// list 만들기
		for (int i = 0; i < shoeslist.size(); i++) {
			deletelabel[0] = new JLabel(shoeslist.get(i).name);
			deletepanel.add(deletelabel[0]);
			deletelabel[1] = new JLabel(shoeslist.get(i).brand);
			deletepanel.add(deletelabel[1]);
			delete_rbutton[i] = new JRadioButton("삭제");
			bg.add(delete_rbutton[i]);
			deletepanel.add(delete_rbutton[i]);
		}

		for (int i = shoeslist.size(); i < 15; i++) {
			for (int z = 0; z < deletetext.length; z++) {
				deletelabel[z] = new JLabel(" ", 0);
				deletepanel.add(deletelabel[z]);
			}
		}
		mainpanel.add(deletepanel, BorderLayout.CENTER);
		this.add(mainpanel, BorderLayout.CENTER);
		reset();

	}

	void reset() {
		this.revalidate();// 새로고침
		this.repaint(); // 새로고침
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < demandlist.size(); i++) {
			if (e.getSource() == demandtitle[i]) {
				Demandcheck dc = new Demandcheck(demandlist.get(i).id, demandlist.get(i).title,
						demandlist.get(i).demand);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
