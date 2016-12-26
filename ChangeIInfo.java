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


public class ChangeIInfo extends JFrame  {
	public ChangeIInfo() {
		//setTitle("Add new subscriber");
		ChangePanel  panell5 = new ChangePanel ();
		add(panell5);
	}
	class ChangePanel extends JPanel {
		 String fileMain = "All.txt";
		 String fileDay = "DayInformation.txt";
		ArrayList<BasaSubscriber> basaDay;
		boolean flafg;
		ArrayList<BasaSubscriber> basaAll;

		ChangePanel () {
			super();
			setLayout(null);

			TelecomTest tel = new TelecomTest();

			JLabel NameNew = new JLabel("Введите имя : ");
			JLabel NumberNew = new JLabel("Введите номер :");
			JLabel AdressNew = new JLabel("Введите адрес :");
			JButton AddEnd = new JButton("Изменить");
			JTextField NewName = new JTextField(70);
			JTextField NewNumber = new JTextField(70);
			JTextField NewAdress = new JTextField(70);

			add(NameNew).setBounds(10, 10, 100, 20);
			add(NewName).setBounds(10, 30, 100, 20);
			add(NumberNew).setBounds(10, 55, 100, 20);
			add(NewNumber).setBounds(10, 75, 100, 20);
			add(AdressNew).setBounds(10, 95, 100, 20);
			add(NewAdress).setBounds(10, 115, 100, 20);
			add(AddEnd).setBounds(10, 145, 150, 20);// 400 330 150 20
			JLabel Error = new JLabel();
			
			AddEnd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean flag = false;
					
					if (NewName.getText().equals("") || NewNumber.getText().equals("") || NewAdress.getText().equals("")) {
						Error.setText("Есть пустое поле,заполните,пожалуйста");
						add(Error).setBounds(10, 165, 300, 20);
						Error.setForeground(Color.red);
						flag = true;
					}
					if (flag == false) {
						String Name = NewName.getText();
						String Number = NewNumber.getText();
						String Adress = NewAdress.getText();
						try {
							if (tel.SearchNumber(Number) == false) {
								Error.setText("Такого абонента не существует.");
								add(Error).setBounds(10, 165, 300, 20);
								Error.setForeground(Color.RED);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							tel.changeInf(Number, Name, Adress);
							Error.setText("Информация изменина");
							add(Error).setBounds(10, 165, 300, 20);
							NewName.setEnabled(false);
							NewNumber.setEnabled(false);
							NewAdress.setEnabled(false);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						AddEnd.setVisible(false);
					}
				
				try {
					tel.writeMainFile(fileMain, basaAll);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				try {
					tel.writeFile(fileDay, basaDay);
					
				} catch (IOException e11) {
					// TODO Auto-generated catch block
					e11.printStackTrace();
				}
				}
			});

		}
	}
}



