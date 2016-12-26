import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteForm extends JFrame{
	public DeleteForm(String DEL) {
		super();
		setTitle("Delete Subscriber");
		DeletePanel panel = new DeletePanel("");
		add(panel);
}
	class DeletePanel extends JPanel {

		TelecomTest tel = new TelecomTest();
		 String fileMain = "All.txt";
		 String fileDay = "DayInformation.txt";
		ArrayList<BasaSubscriber> basaDay;
		boolean flafg;
		ArrayList<BasaSubscriber> basaAll;

		public DeletePanel(String DEL) {
			super();
			setLayout(null);
			JButton DeleteTo = new JButton("Подтверждение");
			JLabel EnterDeleteNumber = new JLabel("Введите номер абонента : ");
			add(EnterDeleteNumber).setBounds(20, -120, 200, 300);
			JTextField TexDeleteNumber = new JTextField(70);
			add(TexDeleteNumber).setBounds(20, 40, 200, 20);
			add(DeleteTo).setBounds(20, 70, 200, 20);
			JLabel NotNumber = new JLabel("Такого абонента не существует.");

			DeleteTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelecomTest tel = new TelecomTest();
					String DEL = TexDeleteNumber.getText();
					JLabel Error = new JLabel();
					try {
						if (tel.SearchNumber(DEL) == true) {
							try {
								tel.DeleteAdmin(DEL);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								tel.writeMainFile(fileMain, basaAll);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								tel.writeFile(fileDay, basaDay);
								NotNumber.setText("Абонент удален");
								add(NotNumber).setBounds(20, 90, 200, 20);
								NotNumber.setForeground(Color.black);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							DeleteTo.setVisible(false);
							flafg = true;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if (tel.SearchNumber(DEL) == false) {
							NotNumber.setText("Такого абонента не существует.");
							add(NotNumber).setBounds(20, 90, 200, 20);
							NotNumber.setForeground(Color.RED);
						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			});
		}

	}
}
