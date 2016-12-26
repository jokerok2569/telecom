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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class InformationForm extends JFrame {
	private final FormManager manager;

	public  InformationForm (final FormManager manager, String tex) {
	    this.manager = manager;
	    setTitle("Information");
	    InformationPanel panell = new  InformationPanel(tex);
		add(panell);
	
	}

class  InformationPanel extends JPanel{
	public String backgr = "17-Best.jpg";
	public  String information = "Your information";
	
	public void paintComponent(Graphics g) {
		Image im = null;
		try {
			im = ImageIO.read(new File(backgr));
		} catch (IOException e) {
		}
		g.drawImage(im, 0, 0, this);

		Font myFont = new Font("Constantia", Font.TRUETYPE_FONT, 38);
		g.setFont(myFont);
		g.drawString(information, 10, 100);
	}
	
	public  InformationPanel(String tex) {
		super();
		setLayout(null);

		TelecomTest tel = new TelecomTest();

		JLabel Debg = new JLabel("Сумма к оплате : ");
		JTextField TexDebg = new JTextField(70);

		JLabel DebgRep = new JLabel("Подтвердите,если это Ваши данные : ");
		add(DebgRep).setBounds(40, 190, 320, 20);

		JButton toPay = new JButton("Заплатить");

		JButton OK = new JButton("Окей");
		add(OK).setBounds(50, 330, 140, 20);

		JButton Return = new JButton("Вернуться");
		add(Return).setBounds(100, 520, 110, 20);

		JTextPane Error = new JTextPane();
		add(Error).setBounds(50, 230, 180, 90);

		try {
			Error.setDisabledTextColor(Color.BLACK);
			Error.setText(tel.SearchPeopleInformation(tex));
			Error.doLayout();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Error.setEnabled(isPaintingForPrint());

		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(Debg).setBounds(50, 370, 160, 20);
				add(toPay).setBounds(50, 420, 120, 20);
				add(TexDebg).setBounds(50, 390, 140, 20);
				Error.setEnabled(isPaintingForPrint());
				OK.setEnabled(false);
			}
		});

		toPay.addActionListener(new ActionListener() {
			boolean flag = false;
			String fileDay;
			ArrayList<BasaSubscriber> basaDay;

			public void actionPerformed(ActionEvent e) {

				if (TexDebg.getText().equals("")) {
					JLabel NOT = new JLabel("Введите сумму к оплате!");
					add(NOT).setBounds(50, 445, 200, 20);
					NOT.setForeground(Color.red);
					flag = true;
				}
				if (flag == false) {
					String texDebg = TexDebg.getText();
					double ded = Double.parseDouble(texDebg);

					try {
						tel.changeDebg(fileDay, tex, ded);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						tel.writeFile(fileDay, basaDay);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						tel.readDayFile(fileDay, basaDay);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					try {
						Error.setText(tel.SearchPeopleInformation(tex));
						Error.doLayout();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Error.setEnabled(isPaintingForPrint());
					TexDebg.setEnabled(false);
				}

				toPay.setEnabled(false);
			}
		});

		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				 manager.Neww();
			}
		});
	}


}
}