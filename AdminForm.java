import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;



public class AdminForm extends JFrame {
	private FormManager manager;

	

	public AdminForm(final FormManager manager) {
		this.manager = manager;
		AdminPanel panel = new AdminPanel();
		add(panel);
		panel.setSize(680, 400);
         setTitle("TELECOM");

	 

}
	class AdminPanel extends JPanel {
		public String backgr = "17-Best2.jpg";
//		ArrayList<BasaSubscriber> basaAll;

		public void paintComponent(Graphics g) {
			Image im = null;
			try {
				im = ImageIO.read(new File(backgr));
			} catch (IOException e) {
			}
			g.drawImage(im, 0, 0, this);
		}

		public AdminPanel() {
			super();
			setLayout(null);

			Telecom tel = new Telecom();

			JTextPane Allnf = new JTextPane();
			add(Allnf).setBounds(70, 20, 210, 390);
			try {
				Allnf.setDisabledTextColor(Color.BLACK);
				Allnf.setText(tel.SearchPeopleInformationAllAdmin());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Allnf.setEnabled(isPaintingForPrint());

			JButton AllPrint = new JButton("Распечатать весь списко абонентов");
			add(AllPrint).setBounds(300, 20, 250, 20);

			JButton Delete = new JButton("Удалить абонента");
			add(Delete).setBounds(300, 50, 250, 20);

			JButton DeleteTo = new JButton("Удалить");
			String DEL = null;
			JTextField TexDeleteName = new JTextField(70);

			JButton NewSub = new JButton("Добавить нового абонента");
			add(NewSub).setBounds(300, 80, 250, 20);

			JButton Change = new JButton("Изменить информацию");
			add(Change).setBounds(300, 110, 250, 20); 
			
			JButton Return = new JButton("Вернуться");
			add(Return).setBounds(300, 140, 250, 20);

			

			NewSub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddNewForm NewSubscriber = new AddNewForm();
					NewSubscriber.setSize(450, 250);
					NewSubscriber.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					NewSubscriber.setResizable(false);
					NewSubscriber.setVisible(true);

				}

			});
			
			Change.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
ChangeIInfo NewSubscriber = new ChangeIInfo ();
					NewSubscriber.setSize(450, 250);
					NewSubscriber.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					NewSubscriber.setResizable(false);
					NewSubscriber.setVisible(true);

				}

			});

			AllPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JScrollPane scrollPane = new JScrollPane(Allnf);

					scrollPane.setPreferredSize(new Dimension(40, 70));
					add(scrollPane, BorderLayout.BEFORE_LINE_BEGINS);
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					add(Allnf).setBounds(70, 20, 210, 390);
					scrollPane.setVisible(true);
					try {
						Allnf.setDisabledTextColor(Color.BLACK);
						Allnf.setText(tel.SearchPeopleInformationAllAdmin());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Allnf.setEnabled(isPaintingForPrint());
				}
			});

			Return.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					manager.Neww();
				}
			});

			Delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					DeleteForm Sure= new DeleteForm(DEL);

					Sure.setSize(250, 250);
					Sure.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					Sure.setVisible(true);
					Sure.setResizable(false);

				}
			});

		}

	}

}
