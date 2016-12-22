import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
		
		static void writeFile(String file,ArrayList<BasaSubscriber> basa) throws IOException {
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
}
