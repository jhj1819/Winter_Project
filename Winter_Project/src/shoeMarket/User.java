package shoeMarket;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User extends JFrame implements ActionListener, MouseListener {
	// JPanel
	JPanel mainpanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	JPanel toppanel = new JPanel();
	JPanel botpanel = new JPanel();
	JPanel column = new JPanel();

	// JLable
	JLabel empty1 = new JLabel("");
	JLabel empty2 = new JLabel("");
	JLabel cancelch = new JLabel("");
	JLabel demandch = new JLabel("");

	// JButton
	String buttontext[] = { "쇼핑하기", "구매하기", "장바구니", "구매 취소", "구매목록", "문의하기" };
	JButton button[] = new JButton[buttontext.length];
	JButton searchbutton = new JButton("검색");
	JButton brandbutton = new JButton("브랜드");
	JButton typebutton = new JButton("종류");
	JButton sortbutton = new JButton("정렬");
	JButton buybutton[]; // 구매버튼
	JButton cancelbutton[]; // 취소버튼
	JButton ibutton[]; // 정보버튼
	JButton cartbutton[]; // 정보버튼
	JButton cartbuybutton = new JButton("구매"); // 정보버튼
	JButton demandbutton = new JButton("등록");// 문의사항 등록버튼
	JButton incoin = new JButton("현금넣기");
	JButton change = new JButton("거스름돈");

	int bnum; // button option 선택시
	// JTextField
	JTextField search = new JTextField("", 20);
	JTextField demandtitletf = new JTextField("제목", 40);
	JTextArea demandta = new JTextArea("내용");

	// list
	public ArrayList<Shoeslist> shoeslist = new ArrayList<>();
	static ArrayList<Shoeslist> shoesSearchlist = new ArrayList<>();
	public ArrayList<Purchaselist> buylist = new ArrayList<>();
	public ArrayList<Purchaselist> salelist = new ArrayList<>();
	public ArrayList<Demandlist> demandlist = new ArrayList<>();
	public ArrayList<JLabel> display_label;
	public ArrayList<Purchaselist> cartlist = new ArrayList<>();

	//

	int buttoncnt;
	int coin;// 입금금액
	int rest;// 나머지
	int totalbuy;
	int cartbuy;
	String userid;
	String search_brand = "";
	String search_type = "";
	int sortind = 0;

	User(ArrayList<Shoeslist> m_list, ArrayList<Purchaselist> p_list, ArrayList<Demandlist> d_list, String userid) {
		this.setTitle("사용자");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// list 저장
		shoeslist = m_list;
		salelist = p_list;
		this.userid = userid;
		demandlist = d_list;

		// button 초기화
		buybutton = new JButton[shoeslist.size()];
		cartbutton = new JButton[shoeslist.size()];
		ibutton = new JButton[shoeslist.size()];
		cancelbutton = new JButton[shoeslist.size()];

		// mainpanel
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setBorder(BorderFactory.createTitledBorder("result"));

		// toppanel
		toppanel.setLayout(new FlowLayout());
		toppanel.add(search);
		toppanel.add(searchbutton);
		toppanel.add(brandbutton);
		toppanel.add(typebutton);
		toppanel.add(sortbutton);
		this.add(toppanel, BorderLayout.NORTH);
		toppanel.setVisible(false);

		// buttonpanel
		buttonpanel.setLayout(new GridLayout(0, 1, 5, 10));
		buttonpanel.setBorder(BorderFactory.createTitledBorder("메뉴"));
		buttonpanel.add(new JLabel(""));
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(buttontext[i]);
			button[i].addActionListener(this);
			buttonpanel.add(button[i]);
		}
		buttonpanel.add(new JLabel(""));

		this.add(buttonpanel, BorderLayout.WEST);

		// botpanel
		botpanel.setLayout(new GridLayout(0, 6, 5, 5));
		botpanel.add(new JLabel(""));
		botpanel.add(empty1);
		botpanel.add(incoin);
		botpanel.add(new JLabel(""));
		empty2.addMouseListener(this);
		botpanel.add(empty2);
		botpanel.add(change);
		botpanel.setBorder(BorderFactory.createTitledBorder("지갑"));
		this.add(botpanel, BorderLayout.SOUTH);
		botpanel.setVisible(false);

		// ActionListener
		searchbutton.addActionListener(this);
		brandbutton.addActionListener(this);
		typebutton.addActionListener(this);
		sortbutton.addActionListener(this);
		incoin.addActionListener(this);
		change.addActionListener(this);
		demandbutton.addActionListener(this);
		cartbuybutton.addActionListener(this);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 쇼핑하기
		if (e.getSource() == button[0]) {
			bnum = 0;
			toppanel.setVisible(true);
			botpanel.setVisible(false);
			mainpanel.removeAll();
			search_brand = "";
			search_type = "";
			sortind = 0;

		}

		// 쇼핑 검색창
		if (e.getSource() == searchbutton && bnum == 0) {
			mainpanel.removeAll();

			column.removeAll();
			column = new JPanel();
			column.setLayout(Gbag);

			JScrollPane scrollPane = new JScrollPane(column);
			scrollPane.setBounds(0, 0, 1200, 1000);

			shoesSearchlist.clear();
			// 검색 결과 리스트 만들기
			String name = search.getText();

			// 조건에 맞게 필터링/정렬 필요
			for (int i = 0; i < shoeslist.size(); i++) {
				if (!search_brand.equals("") && !search_brand.equals(shoeslist.get(i).brand))
					continue;
				if (!search_type.equals("") && !search_type.equals(shoeslist.get(i).shoesCategory))
					continue;
				String shoesname = shoeslist.get(i).name.replace(" ", "");
				if (shoesname.contains(name) || name.equals("")) {
					shoesSearchlist.add(shoeslist.get(i));
				}
			}
			sortlist(sortind);
			// 정렬된 순서대로 UI
			display_label = new ArrayList<>();
			int count = 0;
			for (int i = 0; i < shoesSearchlist.size(); i++) {
				display_label.add(new JLabel());
				display_label.get(i).setBorder(BorderFactory.createTitledBorder("상품_" + (i + 1)));
				display_label.get(i).setLayout(new BorderLayout());
				display_label.get(i).setPreferredSize(new Dimension(200, 300));
				display_label.get(i).setVisible(true);
				display_label.get(i).addMouseListener(this);

				int iconSize = 200;
				JLabel imageLabel = new JLabel();
				ImageIcon originalIcon = new ImageIcon(shoesSearchlist.get(i).image);
				Image scaledImage = originalIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(scaledImage));
				display_label.get(i).add(imageLabel, "Center");

				display_label.get(i).add(new JLabel(
						shoesSearchlist.get(i).name + "\n\n " + Integer.toString(shoesSearchlist.get(i).price) + " 원"),
						"South");

				create_form(display_label.get(i), column, (count % 2) * 1000, (int) (count / 2) * 1000, 200, 300);

				count++;
			}
			mainpanel.add(scrollPane);
			this.add(mainpanel, BorderLayout.CENTER);
			reset();
		}

		// 정보 확인
//		for (int i = 0; i < shoesSearchlist.size(); i++) {
//			if (e.getSource() == ibutton[i]) {
//				int i_m = JOptionPane.showConfirmDialog(null, shoesSearchlist.get(i).info, "info",
//						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
//			}
//		}

		// 구매하기
		if (e.getSource() == button[1]) {
			bnum = 1;
			toppanel.setVisible(true);
			botpanel.setVisible(true);
			mainpanel.removeAll();
			rest = 0;
			search_brand = "";
			search_type = "";
			sortind = 0;
			empty2.setText("");

		}

		// 구매하기 리스트
		if (e.getSource() == searchbutton && bnum == 1) {
			buttoncnt = 0;
			mainpanel.removeAll();
			String name = search.getText();
			JPanel column = new JPanel();
			column.setLayout(new GridLayout(0, 4, 1, 1));

			String[] buytext = { "이름", "브랜드", "가격", "구매" };
			JLabel[] buylable = new JLabel[buytext.length];

			for (int i = 0; i < buytext.length; i++) {
				buylable[i] = new JLabel(buytext[i], 0);
				column.add(buylable[i]);
			}

			// 머신 검색
			shoesSearchlist.clear();

			for (int i = 0; i < shoeslist.size(); i++) {
				if (!search_brand.equals("") && !search_brand.equals(shoeslist.get(i).brand))
					continue;
				if (!search_type.equals("") && !search_type.equals(shoeslist.get(i).shoesCategory))
					continue;
				String shoesname = shoeslist.get(i).name.replace(" ", "");
				if (shoesname.contains(name) || name.equals("")) {
					shoesSearchlist.add(shoeslist.get(i));
				}
			}
			sortlist(sortind);

			for (int i = 0; i < shoesSearchlist.size(); i++) {
				JLabel[] m_info = new JLabel[3];
				m_info[0] = new JLabel(shoesSearchlist.get(i).name, 0);
				column.add(m_info[0]);
				m_info[1] = new JLabel(shoesSearchlist.get(i).brand, 0);
				column.add(m_info[1]);
				m_info[2] = new JLabel(Integer.toString(shoesSearchlist.get(i).price), 0);
				column.add(m_info[2]);
				buybutton[buttoncnt] = new JButton("구매");
				buybutton[buttoncnt].addActionListener(this);
				column.add(buybutton[buttoncnt]);
				buttoncnt++;

			}

			for (int i = buttoncnt; i < shoeslist.size(); i++) {
				for (int j = 0; j < buylable.length; j++) {
					buylable[j] = new JLabel(" ", 0);
					column.add(buylable[j]);
				}
			}

			mainpanel.add(column);
			this.add(mainpanel, BorderLayout.CENTER);
			reset();

		}
		// 구매버튼 클릭시
		for (int i = 0; i < buttoncnt; i++) {
			if (e.getSource() == buybutton[i]) {
				if (coin >= shoesSearchlist.get(i).price) {
					coin = coin - shoesSearchlist.get(i).price;
					totalbuy = totalbuy + shoesSearchlist.get(i).price;
					buylist.add(new Purchaselist(shoesSearchlist.get(i).name, shoesSearchlist.get(i).brand, userid,
							shoesSearchlist.get(i).price));
					salelist.add(new Purchaselist(shoesSearchlist.get(i).name, shoesSearchlist.get(i).brand, userid,
							shoesSearchlist.get(i).price));
					empty1.setText(Integer.toString(coin));

				}
			}

		}
		// 장바구니
		if (e.getSource() == button[2]) {
			bnum = 1;
			toppanel.setVisible(false);
			botpanel.setVisible(true);
			mainpanel.removeAll();
			rest = 0;
			search_brand = "";
			search_type = "";
			sortind = 0;
			setcartpanel();

		}

		// 장바구니 취소버튼 클릭시 액션추가
		for (int j = 0; j < cartlist.size(); j++) {
			if (e.getSource() == cartbutton[j]) {
				int minus = cartlist.get(j).price;
				String mname = cartlist.get(j).name;
				cartbuy -= minus;
				cartlist.remove(j);
				mainpanel.removeAll();
				setcartpanel();

			}
		}

		// 카트 구매버튼 클릭시
		if (e.getSource() == cartbuybutton) {
			if (coin >= cartbuy) {
				coin = coin - cartbuy;
				totalbuy = totalbuy + cartbuy;
				for (int i = 0; i < cartlist.size(); i++) {
					buylist.add(new Purchaselist(cartlist.get(i).name, cartlist.get(i).brand, userid,
							cartlist.get(i).price));
					salelist.add(new Purchaselist(cartlist.get(i).name, cartlist.get(i).brand, userid,
							cartlist.get(i).price));
				}
				mainpanel.removeAll();
				cartlist.clear();
				setcartpanel();
				empty1.setText(Integer.toString(coin));

			}

		}

		// 구매 취소
		if (e.getSource() == button[3]) {
			bnum = 1;
			toppanel.setVisible(false);
			botpanel.setVisible(false);
			mainpanel.removeAll();

			cancelch.setText("");
			setcalcelpanel();

		}

		// 취소버튼 클릭시 액션추가
		for (int j = 0; j < buylist.size(); j++) {
			if (e.getSource() == cancelbutton[j]) {
				int minus = buylist.get(j).price;
				String mname = buylist.get(j).name;
				totalbuy = totalbuy - minus;
				salelist.remove(salelist.size() - (buylist.size() - j));
				buylist.remove(j);

				mainpanel.removeAll();
				setcalcelpanel();

			}
		}

		// 구매목록 확인
		if (e.getSource() == button[4]) {
			bnum = 2;
			toppanel.setVisible(false);
			botpanel.setVisible(false);
			mainpanel.removeAll();
			JPanel check = new JPanel();
			check.setLayout(new GridLayout(0, 4, 5, 5));
			JLabel empty;
			JLabel l1 = new JLabel("총 구매금액", 0);
			JLabel l2 = new JLabel("", 0);

			check.add(new JLabel(" "));
			check.add(new JLabel(" "));
			check.add(l1);
			check.add(l2);
			DefaultListModel<String> jbuylist = new DefaultListModel<>();
			for (int i = 0; i < buylist.size(); i++) {
				jbuylist.addElement(buylist.get(i).toString());
			}
			JList<String> list = new JList<String>(jbuylist);
			list.setBounds(100, 100, 75, 75);
			l2.setText(Integer.toString(totalbuy));
			mainpanel.add(list, BorderLayout.CENTER);
			mainpanel.add(check, BorderLayout.SOUTH);
			this.add(mainpanel, BorderLayout.CENTER);

			reset();

		}

		// 문의하기
		if (e.getSource() == button[5]) {
			toppanel.setVisible(false);
			botpanel.setVisible(false);
			mainpanel.removeAll();
			demandtitletf.setText("제목");
			demandta.setText("내용");
			// demand toppanel
			JPanel dtoppanel = new JPanel();
			dtoppanel.add(demandtitletf);
			mainpanel.add(dtoppanel, BorderLayout.NORTH);

			// demand mainpanel

			mainpanel.add(demandta, BorderLayout.CENTER);

			// demand botpanel
			JPanel dbotpanel = new JPanel();
			dbotpanel.setLayout(new GridLayout(0, 3, 10, 10));
			dbotpanel.add(new JLabel(""));
			dbotpanel.add(demandbutton);
			demandch.setText("");
			dbotpanel.add(demandch);
			mainpanel.add(dbotpanel, BorderLayout.SOUTH);

			this.add(mainpanel, BorderLayout.CENTER);
			reset();
			;

		}

		// 문의 등록버튼 클릭시
		if (e.getSource() == demandbutton) {
			String title = demandtitletf.getText();
			String contents = demandta.getText();
			if (!title.equals("") && !title.equals("제목") && !contents.equals("") && !contents.equals("내용")) {
				demandlist.add(new Demandlist(userid, title, contents));
				demandch.setText("등록완료");
				System.out.println(title);
				System.out.println(contents);
			}

			else {
				demandch.setText("등록실패");

			}
			demandtitletf.setText("");
			demandta.setText("");

		}

		if (e.getSource() == incoin) {
			String num[] = { "10000", "3000", "50000", "100000" };
			int price = JOptionPane.showOptionDialog(null, "입금 (원 단위)", "insertcoin", 0, JOptionPane.PLAIN_MESSAGE,
					null, num, num[3]);
			coin = coin + Integer.parseInt(num[price]);
			empty1.setText(Integer.toString(coin));
		}

		if (e.getSource() == change) {
			rest = rest + coin;
			empty2.setText(Integer.toString(rest));
			coin = 0;
			empty1.setText(Integer.toString(coin));
		}
		// 검색 조건 버튼
		if (e.getSource() == brandbutton) {
			String brand[] = { "전체", "나이키", "아디다스", "푸마", "언더아머" };
			int ind = JOptionPane.showOptionDialog(null, "브랜드 선택", "Brand", 0, JOptionPane.PLAIN_MESSAGE, null, brand,
					brand[4]);
			search_brand = brand[ind];
			if (ind == 0)
				search_brand = "";
			System.out.println(search_brand);
		}
		if (e.getSource() == typebutton) {
			String type[] = { "전체", "축구화", "농구화", "러닝화", "배드민턴" };
			int ind = JOptionPane.showOptionDialog(null, "종류 선택", "Type", 0, JOptionPane.PLAIN_MESSAGE, null, type,
					type[4]);
			search_type = type[ind];
			if (ind == 0)
				search_type = "";
			System.out.println(search_type);
		}
		// 정렬
		if (e.getSource() == sortbutton) {
			String sort[] = { "이름순", "가격(올)", "가격(내)", "좋아요(올)", "좋아요(내)" };
			sortind = JOptionPane.showOptionDialog(null, "정렬 선택", "Sort", 0, JOptionPane.PLAIN_MESSAGE, null, sort,
					sort[4]);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == empty2) {
			rest = 0;
			empty2.setText(Integer.toString(rest));
		}
		for (int i = 0; i < display_label.size(); i++) {
			if (e.getSource() == display_label.get(i)) {
				ImageIcon originalIcon = new ImageIcon(shoesSearchlist.get(i).getImage());
				Image scaledIcon = originalIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
				ImageIcon changedIcon = new ImageIcon(scaledIcon);
				int i_m = JOptionPane.showConfirmDialog(null, shoesSearchlist.get(i).toString() + "\n\n장바구니에 담으시겠습니까?",
						"상세정보", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, changedIcon);
				if (i_m == 0) {
					cartlist.add(new Purchaselist(shoesSearchlist.get(i).name, shoesSearchlist.get(i).brand, userid,
							shoesSearchlist.get(i).price));
					/*
					 * buylist.add(new Purchaselist(shoesSearchlist.get(i).name,
					 * shoesSearchlist.get(i).brand, userid, shoesSearchlist.get(i).price));
					 * salelist.add(new Purchaselist(shoesSearchlist.get(i).name,
					 * shoesSearchlist.get(i).brand, userid, shoesSearchlist.get(i).price));
					 */
				}
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

	void setcartpanel() {
		String[] carttext = { "이름", "브랜드", "가격", "취소" };
		JLabel[] cartlable = new JLabel[carttext.length];
		cartbuy = 0;
		// 취소 패널
		JPanel cartpanel = new JPanel();
		cartpanel.setLayout(new GridLayout(0, 4, 5, 5));

		JPanel botmainpanel = new JPanel();
		botmainpanel.setLayout(new GridLayout(0, 4, 5, 5));
		for (int i = 0; i < 3; i++) {
			botmainpanel.add(new JLabel(""));
		}
		botmainpanel.add(cancelch);

		mainpanel.add(botmainpanel, BorderLayout.SOUTH);

		for (int i = 0; i < carttext.length; i++) {
			cartlable[i] = new JLabel(carttext[i], 0);
			cartpanel.add(cartlable[i]);
		}

		// list 만들기
		for (int i = 0; i < cartlist.size(); i++) {
			cartlable[0] = new JLabel(cartlist.get(i).name, 0);
			cartpanel.add(cartlable[0]);
			cartlable[1] = new JLabel(cartlist.get(i).brand, 0);
			cartpanel.add(cartlable[1]);
			cartlable[2] = new JLabel(Integer.toString(cartlist.get(i).price), 0);
			cartbuy += cartlist.get(i).price;
			cartpanel.add(cartlable[2]);
			cartbutton[i] = new JButton("취소");
			cartbutton[i].addActionListener(this);
			cartpanel.add(cartbutton[i]);
		}
		// 구매버튼
		cartlable[0] = new JLabel(" ", 0);
		cartpanel.add(cartlable[0]);
		cartlable[1] = new JLabel("총 금액 ", 0);
		cartpanel.add(cartlable[1]);
		cartlable[2] = new JLabel(Integer.toString(cartbuy), 0);
		cartpanel.add(cartlable[2]);
		cartpanel.add(cartbuybutton);

		for (int i = cartlist.size() + 1; i < 15; i++) {
			for (int j = 0; j < carttext.length; j++) {
				cartlable[j] = new JLabel(" ", 0);
				cartpanel.add(cartlable[j]);
			}
		}

		mainpanel.add(cartpanel, BorderLayout.CENTER);
		this.add(mainpanel, BorderLayout.CENTER);
		reset();
	}

	void setcalcelpanel() {
		String[] canceltext = { "이름", "브랜드", "가격", "취소" };
		JLabel[] cancellable = new JLabel[canceltext.length];

		// 취소 패널
		JPanel cancelpanel = new JPanel();
		cancelpanel.setLayout(new GridLayout(0, 4, 5, 5));

		JPanel botmainpanel = new JPanel();
		botmainpanel.setLayout(new GridLayout(0, 4, 5, 5));
		for (int i = 0; i < 3; i++) {
			botmainpanel.add(new JLabel(""));
		}
		botmainpanel.add(cancelch);

		mainpanel.add(botmainpanel, BorderLayout.SOUTH);

		for (int i = 0; i < canceltext.length; i++) {
			cancellable[i] = new JLabel(canceltext[i], 0);
			cancelpanel.add(cancellable[i]);
		}

		// list 만들기
		for (int i = 0; i < buylist.size(); i++) {
			cancellable[0] = new JLabel(buylist.get(i).name, 0);
			cancelpanel.add(cancellable[0]);
			cancellable[1] = new JLabel(buylist.get(i).brand, 0);
			cancelpanel.add(cancellable[1]);
			cancellable[2] = new JLabel(Integer.toString(buylist.get(i).price), 0);
			cancelpanel.add(cancellable[2]);
			cancelbutton[i] = new JButton("취소");
			cancelbutton[i].addActionListener(this);
			cancelpanel.add(cancelbutton[i]);
		}

		for (int i = buylist.size(); i < 15; i++) {
			for (int j = 0; j < canceltext.length; j++) {
				cancellable[j] = new JLabel(" ", 0);
				cancelpanel.add(cancellable[j]);
			}
		}

		mainpanel.add(cancelpanel, BorderLayout.CENTER);
		this.add(mainpanel, BorderLayout.CENTER);
		reset();
	}

	void reset() {
		this.revalidate();// 새로고침
		this.repaint(); // 새로고침
	}

	GridBagLayout Gbag = new GridBagLayout();
	GridBagConstraints gbc1;

	public void create_form(Component cmpt, JPanel jp_label, int x, int y, int w, int h) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		this.Gbag.setConstraints(cmpt, gbc);
		jp_label.add(cmpt);
		jp_label.updateUI();

	}

	static void sortlist(int n) {
		if (n == 0) {
			shoesSearchlist.sort(new Comparator<Shoeslist>() {
				@Override
				public int compare(Shoeslist o1, Shoeslist o2) {
					// TODO Auto-generated method stub
					return o1.name.compareTo(o2.name);
				}
			});
		} else if (n == 1) {
			shoesSearchlist.sort(new Comparator<Shoeslist>() {

				@Override
				public int compare(Shoeslist o1, Shoeslist o2) {
					// TODO Auto-generated method stub
					return o1.price - o2.price;
				}
			});
		} else if (n == 2) {
			shoesSearchlist.sort(new Comparator<Shoeslist>() {

				@Override
				public int compare(Shoeslist o1, Shoeslist o2) {
					// TODO Auto-generated method stub
					return o2.price - o1.price;
				}
			});
		} else if (n == 3) {
			shoesSearchlist.sort(new Comparator<Shoeslist>() {

				@Override
				public int compare(Shoeslist o1, Shoeslist o2) {
					// TODO Auto-generated method stub
					return o1.num_of_purchases - o2.num_of_purchases;
				}
			});
		}

		else {
			shoesSearchlist.sort(new Comparator<Shoeslist>() {

				@Override
				public int compare(Shoeslist o1, Shoeslist o2) {
					// TODO Auto-generated method stub
					return o2.num_of_purchases - o1.num_of_purchases;
				}
			});
		}
	}
}