import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends Telecom implements FormManager {
	private JFrame form1;
	private JFrame form2;
	private JFrame form3;
	private String tex = "";

	public static void main(String args[]) throws IOException {
		basaAll.add(new BasaSubscriber("380668970170", "Kotyash A.S", "Car street 16/9", -40));
		basaAll.add(new BasaSubscriber("380509157269", "Kotyash E.V", "Car street 16/9", 60));
		basaAll.add(new BasaSubscriber("380665525433", "Vanin B.V", "Oshepkova street 16/42", 20));
		basaAll.add(new BasaSubscriber("380508008450", "Horobetz M.S", "Slava Ukraine 14/88", 60));
		writeMainFile(fileMain, basaAll);

		Telecom tel = new Telecom();

		new Thread(new Runnable() {
			public void run() {
				while (true) { // бесконечно крутим
					try {

						Thread.sleep(47000); // 4 секунды в милисекундах
						try {
							double money = -50;
							tel.changeDebgDay(fileDay, money);
						} catch (IOException e) {
							e.printStackTrace();
						}

						tel.writeFile(fileDay, basaDay);
						try {
							tel.readDayFile(fileDay, basaDay);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

	public Main() {
		form1 = (JFrame) new WelcomeForm(this);
		form1.setSize(850, 600);
		form1.setResizable(false);
		form2 =new InformationForm(this, tex);
		form2.setSize(680, 400);
		form1.setVisible(true);
		form3 = (JFrame)new AdminForm(this);
	}
	public void OpenAdmin(){
		form3=(JFrame) new AdminForm(this);
		form3.setSize(850, 600);
		form3.setVisible(true);
		form3.setVisible(true);
		form1.setVisible(!form1.isVisible());
	}
public void Neww(){
	
	form1 =new  WelcomeForm(this);
	form1.setSize(850, 600);
	form1.setResizable(false);
	form1.setVisible(!form1.isVisible());
	form3.setVisible(false);
	form2.setVisible(false);
}


	@Override
	public void toggleForms(String tex) {
		form1.setVisible(!form1.isVisible());
		form2 = new InformationForm(this, tex);
		form2.setSize(850, 600);
		form2.setResizable(false);
		form2.setVisible(!form2.isVisible());

	};
}