import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Telecom {
	static String fileMain = "All.txt";
	static String fileDay = "DayInformation.txt";
	private static ArrayList<BasaSubscriber> basanew = new ArrayList<BasaSubscriber>(10);
	static ArrayList<BasaSubscriber> basaAll = new ArrayList<BasaSubscriber>(10);
	static ArrayList<BasaSubscriber> basaDay = new ArrayList<BasaSubscriber>(10);
	public static String line;
	public static String number = "";
	public static String data = "";
	public static String addres = "";
	public static String w = "";
	public static String mainline = "";
	public static String money = "";
	private static double y;

	static void NewAdd(String Name, String Number, String Adress, double Opa) throws IOException {
		basaAll.add(new BasaSubscriber(Number, Name, Adress, Opa));
		writeMainFile(fileMain, basaAll);
		writeFile(fileDay, basaDay);
	}

	static void writeFile(String file, ArrayList<BasaSubscriber> basa) throws IOException {
		try {
			FileWriter out = new FileWriter(new File(fileDay).getAbsoluteFile());
			try {
				for (int j = 0; j < basaAll.size(); j++) {
					out.write(basaAll.get(j).getPhoneNumber());
					out.write(";");
					String str = Double.toString(basaAll.get(j).getDebt());
					out.write(str);
					out.write(";");
					out.write("\r\n");

				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);

		}

	}

	static void writeMainFile(String file, ArrayList<BasaSubscriber> basa) throws IOException {
		try {
			FileWriter outmain = new FileWriter(new File(file).getAbsoluteFile());
			FileWriter out = new FileWriter(new File(fileDay).getAbsoluteFile());
			try {
				for (int j = 0; j < basaAll.size(); j++) {
					outmain.write(basaAll.get(j).getPhoneNumber());
					outmain.write(";");
					outmain.write(basaAll.get(j).getSubscriberData());
					outmain.write(";");
					outmain.write(basaAll.get(j).getAddress());
					outmain.write(";");
					String str = Double.toString(basaAll.get(j).getDebt());
					outmain.write(str);
					outmain.write(";");
					outmain.write("\r\n");

					out.write(basaAll.get(j).getPhoneNumber());
					out.write(";");
					str = Double.toString(basaAll.get(j).getDebt());
					out.write(str);
					out.write(";");
					out.write("\r\n");
				}
			} finally {
				outmain.close();
				out.close();

			}
		} catch (IOException e) {
			throw new RuntimeException(e);

		}

		try {
			readMainFile(fileMain, basanew);
			readDayFile(fileDay, basaDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readMainFile(String fileName, ArrayList<BasaSubscriber> basa) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		line = "";
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");

		double debt = 0;
		for (int i = 0; i < lines.length; i++) {
			number = lines[i];
			data = lines[i + 1];
			addres = lines[i + 2];
			money = lines[i + 3];
			debt = Double.parseDouble(money);
			basanew.add(new BasaSubscriber(number, data, addres, debt));
			i = i + 3;
		}
		br.close();
		fr.close();
	}

	static void readDayFile(String fileName, ArrayList<BasaSubscriber> basa) throws IOException {
		basaDay.clear();

		File file = new File(fileDay);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		line = "";
		mainline = "";

		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}

		String[] lines = mainline.split(";");

		double debt = 0;

		for (int i = 0; i < lines.length; i++) {
			number = lines[i];
			money = lines[i + 1];
			debt = Double.parseDouble(money);
			basaDay.add(new BasaSubscriber(number, debt));
			i = i + 1;
		}
		//System.out.println();
		br.close();
		fr.close();
	}

	private boolean flagSearch;

	public String SearchPeopleInformation(String tex) throws IOException {
		File file = new File(fileDay);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");
		//System.out.println();
		line = "";
		for (int i = 0; i < basaDay.size(); i++) {
			if (basaDay.get(i).getPhoneNumber().equals(tex) || (basaAll.get(i).getPhoneNumber().equals(tex))) {
				flagSearch = true;
				line = line + "" + basaDay.get(i).getPhoneNumber() + "                                 "
						+ basaAll.get(i).getSubscriberData() + "                                    "
						+ basaAll.get(i).getAddress() + "                                     "
						+ basaDay.get(i).getDebt();
			}
		}
		;
		return line;
	}

	public boolean SearchNumber(String tex) throws IOException {
		File file = new File(fileMain);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");
		//System.out.println();
		for (int i = 0; i < basaDay.size(); i++) {
			if (basaDay.get(i).getPhoneNumber().equals(tex) || (basaAll.get(i).getPhoneNumber().equals(tex))) {
				flagSearch = true;
			}
		}
		;
		return flagSearch;
	}
//////////////////////////
	static void changeInf( String nownumber,String Data,String Addressf) throws IOException {
		File file = new File(fileDay);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");

		for (int i = 0; i < basaDay.size(); i++) {
			if (basaDay.get(i).getPhoneNumber().equals(nownumber)
					|| (basaAll.get(i).getPhoneNumber().equals(nownumber))) {
				
				//basaDay.get(i).setAddress(Addressf);
				basaAll.get(i).setSubscriberData(Data);
				basaAll.get(i).setAddress(Addressf);
				break;
			}
		}
		br.close();
		fr.close();
	}
	////////////////
	static void changeDebg(String fileName, String nownumber, double money) throws IOException {
		File file = new File(fileDay);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");

		for (int i = 0; i < basaDay.size(); i++) {
			if (basaDay.get(i).getPhoneNumber().equals(nownumber)
					|| (basaAll.get(i).getPhoneNumber().equals(nownumber))) {
				//System.out.println(money);
				y = basaDay.get(i).getDebt();

				y = y + money;
				basaDay.get(i).setDebt(y);
				basaAll.get(i).setDebt(y);
				break;
			}
		}
		br.close();
		fr.close();
	}

	static void changeDebgDay(String fileName, double money) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");
		//System.out.println();
		for (int i = 0; i < basaDay.size(); i++) {
			y = basaDay.get(i).getDebt();
			y = y + money;
			basaDay.get(i).setDebt(y);
			basaAll.get(i).setDebt(y);
			y = 0;
		}

		br.close();
		fr.close();

	}

	public String SearchPeopleInformationAllAdmin() throws IOException {
		File file = new File(fileDay);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		line = "";
		double k;
		mainline = "";
		while ((line = (br).readLine()) != null) {
			mainline = mainline + line;
		}
		String[] lines = mainline.split(";");
	//	System.out.println();
		line = "";
		for (int i = 0; i < basaDay.size(); i++) {
			flagSearch = true;
			line = line + "" + basaDay.get(i).getPhoneNumber() + "                                 "
					+ basaAll.get(i).getSubscriberData() + "                                        "
					+ basaAll.get(i).getAddress() + "                      " + basaDay.get(i).getDebt()
					+ "                                           ";
		}
		return line;
	}

	public void DeleteAdmin(String Number) throws IOException {
		for (int i = 0; i < basaAll.size(); i++) {
			if ((basaAll.get(i).getPhoneNumber().equals(Number))) {
				basaAll.remove(i);
				basaDay.remove(i);
				break;
			}
		}
	}
}
