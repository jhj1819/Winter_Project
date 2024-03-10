package shoeMarket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demandcheck extends JFrame implements ActionListener {

	// Jpanel
	JPanel toppanel = new JPanel();
	JPanel mainpanel = new JPanel();
	JPanel bottompanel = new JPanel();
	JPanel d_id_panel = new JPanel();
	JPanel d_title_panel = new JPanel();

	// JButton
	JButton check = new JButton("확인");

	// Jlabel
	JLabel demandidlb = new JLabel("id");
	JLabel demandtitlelb = new JLabel("제목");

	// JTextField
	JTextField demandidtf = new JTextField("", 40);
	JTextField demandtitletf = new JTextField("", 40);
	JTextArea demandta = new JTextArea("");

	String id;
	String title;
	String content;

	public Demandcheck(String id, String title, String content) {
		// TODO Auto-generated constructor stub
		this.setTitle("문의사항 확인");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.id = id;
		this.title = title;
		this.content = content;
		check.addActionListener(this);
		settoppanel();
		setmainpanel();
		setbottompanel();

		this.setVisible(true);
	}

	void settoppanel() {
		toppanel.setLayout(new GridLayout(0, 1, 5, 5));
		d_id_panel.setLayout(new FlowLayout());
		d_id_panel.add(demandidlb);
		demandidtf.setText(id);
		d_id_panel.add(demandidtf);
		toppanel.add(d_id_panel);
		d_title_panel.setLayout(new FlowLayout());
		d_title_panel.add(demandtitlelb);
		demandtitletf.setText(title);
		d_title_panel.add(demandtitletf);
		toppanel.add(d_title_panel);
		this.add(toppanel, BorderLayout.NORTH);

	}

	void setmainpanel() {
		demandta.setText(content);
		this.add(demandta, BorderLayout.CENTER);
	}

	void setbottompanel() {
		bottompanel.setLayout(new GridLayout(0, 5, 5, 5));
		for (int i = 0; i < 4; i++) {
			bottompanel.add(new JLabel(""));
		}
		bottompanel.add(check);
		this.add(bottompanel, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == check) {
			dispose();
		}
	}
}
