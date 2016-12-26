import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class WelcomeForm extends JFrame {
	private FormManager manager;

	

	public WelcomeForm(final FormManager manager) {
		this.manager = manager;
		WelcomePanel panel = new WelcomePanel();
		add(panel);
		panel.setSize(680, 400);
         setTitle("TELECOM");

	}
	class WelcomePanel extends JPanel{
		public String welcome = "Welcome";
		public String toTelecom = "to Telecom";
		public String backgr = "17-Best.jpg";
		public  String tex;
		
		public void paintComponent(Graphics g) {
			Image im = null;
			try {
				im = ImageIO.read(new File(backgr));
			} catch (IOException e) {
			}
			g.drawImage(im, 0, 0, this);
		}

		public WelcomePanel() {
			setLayout(null);

			JLabel EnterNumber = new JLabel("Ваш номер : ");
			add(EnterNumber).setBounds(105, 290, 100, 20);

			JLabel InPas = new JLabel("Ведите пароль : ");
			JPasswordField password = new JPasswordField (70);

			JTextField Tex = new JTextField(70);
			add(Tex).setBounds(100, 320, 120, 20);

			JButton OKbutton = new JButton("Ок ");
			add(OKbutton).setBounds(100, 350, 120, 20);// 375
			JRadioButton Admn = new JRadioButton(" Вы являетесь админом ");

			add(Admn).setBounds(20, 510, 170, 20);
			JButton AdminButton = new JButton("Подтверждение");

			Admn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					add(InPas).setBounds(20, 465, 220, 20);
					add(password).setBounds(20, 485, 120, 20);
					add(AdminButton).setBounds(150, 485, 130, 20);
					Admn.setEnabled(false);
				}
			});

			AdminButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String texpass = password.getText();
					String RigthPas = "123";
					if (texpass.equals(RigthPas)) {
						manager.OpenAdmin();
						password.setEnabled(false);
					}
				}
			});

			OKbutton.addActionListener(new ActionListener() {

				private String fileMain;
				private boolean flagSearch;
				Telecom tel = new Telecom();
				public void actionPerformed(ActionEvent e) {
					
					tex = Tex.getText();
					flagSearch = false;
					try {
						if (tel.SearchNumber(tex) == true) {
							manager.toggleForms(tex);
						}
						{
							JLabel Error = new JLabel("Ваш номер не найдет." + "Введите еще раз");
							add(Error).setBounds(100, 375, 300, 20);
							Error.setForeground(Color.red);

						}
					} catch (IOException e1) {
					}

				}
			});

		}
		
		public void paint(Graphics k) {
			super.paint(k);
			Font myFont = new Font("Constantia", Font.TRUETYPE_FONT, 58);
			k.setFont(myFont);
			k.drawString(welcome, 70, 150);
			myFont = new Font("Constantia", Font.TRUETYPE_FONT, 38);
			k.setFont(myFont);
			k.drawString(toTelecom, 80, 200);

		}
	}
}


