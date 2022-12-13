import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public class StudentsAttendanceSystem {

	// Oluşturulan raporu depolamak için dosya
	static File generatedReport = new File("AttendanceReport.txt");

	public static void main(String[] args) {

		System.out.println("           WELCOME TO PRU STUDENTS ATTENDANCE SYSTEM                     ");
		System.out.println("_________________________________________________________________________");
		System.out.println(" READ the following information before proceeding.");
		System.out.println("_________________________________________________________________________");
		System.out.println("1. Enter (1) to Print the Students Attendance Report for the day.");
		System.out.println("2. Enter (2) to update or Edit the current data.");
		System.out.println("_________________________________________________________________________");


		Scanner sc = new Scanner(System.in);

		// iki giriş değişkeni bildirme ve sıfıra başlatma
		int input1 = 0;
		int input2 = 0;

		do { // işlemi tekrar tekrar çalıştırmak için döngü
			try { // InputMismatchException'ı yakalama


				System.out.print("Please Enter here: ");
				input1 = sc.nextInt();

				// tamsayı girişlerini doğrulamak için bir if koşulu
				if ((input1 <= 0) || (input1 >= 3)) {

					// Hata mesajı
					System.err.println("Please enter a valid number.");
				}

			} catch (InputMismatchException e) { // giriş uyuşmazlığını yakalama


				// istisnaya hata mesajı göster
				System.err.println("Please enter a valid character.");
			}

			// Sonraki line
			sc.nextLine();

			// Girilen girdilerin doğrulanması
		} while ((input1 != 1) && (input1 != 2));


		if (input1 == 1) {


			printingSeveralReports();
			System.out.println("-Completed-");


		} else if (input1 == 2) {


			System.out.println("_________________________________________________________________________");
			System.out.println(" READ the following information before proceeding.");
			System.out.println("_________________________________________________________________________");
			System.out.println("1. Enter (1) to add a new student record.");
			System.out.println("2. Enter (2) to edit an existing student record.");
			System.out.println("3. Enter (3) to delete an existing student record.");
			System.out.println("4. Enter (4) to add a new lecturer record.");
			System.out.println("5. Enter (5) to edit an existing lecturer record.");
			System.out.println("6. Enter (6) to delete an existing lecturer record.");
			System.out.println("_________________________________________________________________________");

			do {
				try {


					System.out.print("Please Enter here: ");
					input2 = sc.nextInt();


					if ((input2 <= 0) || (input2 >= 7)) {


						System.err.println("Please enter a valid number.");
					}

				} catch (InputMismatchException e) {



					System.err.println("Please enter a valid character.");
				}

				sc.nextLine();


			} while ((input2 != 1) && (input2 != 2) && (input2 != 3) && (input2 != 4) && (input2 != 5)
					&& (input2 != 6));


			if (input2 == 1) {


				addStudentRecord();
				System.out.println("The Data have been successfully stored.");


			} else if (input2 == 2) {


				editStudentRecord();
				System.out.println("The Data have been successfully edited.");


			} else if (input2 == 3) {


				deleteExistingStudentRecords();
				System.out.println("The Data have been successfully deleted.");


			} else if (input2 == 4) {


				addLectureRecord();
				System.out.println("The Data have been successfully stored.");


			} else if (input2 == 5) {


				editLecturerRecords();
				System.out.println("The Data have been successfully edited.");

				// if input2 is 6
			} else if (input2 == 6) {

				// calling the delete Existing lecture record
				deleteExistingLecturerRecords();
				System.out.println("The Data have been successfully deleted.");

			}
		}
		// Scanner kapama
		sc.close();
	}

	// ders için öğrencileri yazdırmak için yöntem oluşturma
	// dosyalardan değeri rapor yöntemine döndürme
	public static int attendanceListInReport(String unitID, String date, String weekNo) {


		int count = 0;

		try {

			// öğrencilerin ayrıntılarını okumak için bir dosya nesnesi oluşturma
			File studentDetailsFile = new File("studentdetails.txt");
			Scanner sc = new Scanner(studentDetailsFile);


			sc.nextLine();

			// verileri otomatik olarak yazmak için dosya yazıcı nesnesi
			// boolean değeri
			FileWriter fw = new FileWriter(generatedReport, true);
			PrintWriter pw = new PrintWriter(fw, true);

			// çıktı başlıklarının konsolda sekme boşluklarıyla oluşturulması
			System.out.println("Student No. \tLast Name \tFirstName \tPresent ");
			System.out.println(); // sonraki satıra geçiş için
			pw.println("Student No. \tLast Name \t\tFirstName \t\tPresent ");
			pw.println(); // sonraki satıra geçiş

			// girilen veriler arasında gezinmek için bir while döngüsü oluşturma

			while (sc.hasNextLine()) {

				// bir String değişkeni yaratıyoruz ve girdiyi kullanarak alıyoruz
				// veri girişini belirtmek için bir String Tokenizer nesnesi oluşturma

				String data = sc.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				//  studentDetails için object oluşturma
				// object'e token eklemek
				studentDetails object = new studentDetails(token.nextToken(), token.nextToken(), token.nextToken());


				String present1 = "";


				// Nesneden bir alıcı kullanarak öğrenci numarasını alma
				boolean present = attendanceCheck(object.getStudentNumber(), unitID, date, weekNo);

				// Sonuçlar doğruysa
				if (present == true) {

					present1 = "Y";
					count++; // Toplama 1 ekleme

				} else {

					present1 = "N";

				}


				boolean commencingUnit = false;

				// moduleRegistration ayrıntılarını okumak için bir dosya nesnesi oluşturma
				// bir scanner oluşturma ve onu dosya nesnesine sarma
				File moduleRegistrationFile = new File("moduleRegistration.txt");
				Scanner input = new Scanner(moduleRegistrationFile);


				input.nextLine();


				while (input.hasNextLine()) {

					// bir String değişkeni yaratma ve girdiyi kullanarak alma
                    // verileri tokenize etmek için bir String Tokenizer nesnesi oluşturma

					String data1 = input.nextLine();
					StringTokenizer token1 = new StringTokenizer(data1);


					moduleRegistration object1 = new moduleRegistration(token1.nextToken(), token1.nextToken());

					//bir if kullanarak oluşturulan nesnedeki ve nesne1'deki değerleri karşılaştırma
					if ((object.getStudentNumber().equals(object1.getStudentNumber())
							&& (object1.getUnitID().equals(unitID)))) {
						commencingUnit = true;

					}

				}

				if (commencingUnit == true) {


					System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", object.getStudentNumber(), object.getLastName(),
							object.getFirstName(), present1);
					pw.printf("%s\t\t%s\t\t%s\t\t%s\n", object.getStudentNumber(), object.getLastName(),
							object.getFirstName(), present1);
					pw.println();
				}

				input.close();
			}

			sc.close();
			pw.close();

		} catch (FileNotFoundException e) { //  File not Found olursa

			// Filenotfound mesajı
			JOptionPane.showMessageDialog(null, "File not found.");

		} catch (IOException ex) {  //IO yakalama
			ex.printStackTrace();

		}
		return count;

	}


	// printingSeveralReports methodu
	public static void report(String moduleName, String date, String weekNo) {

		try {

			int numberOfStudentsEnrolled = 0;
			int roomCapacity = 0;

			// Öğretmen ayrıntılarını okumak için bir dosya nesnesi oluşturma

			File lecturerFile = new File("lecturer.txt");
			Scanner sc = new Scanner(lecturerFile);


			sc.nextLine();

			// verileri otomatik olarak yazmak için dosya yazarı nesnesini oluşturma

			FileWriter fw = new FileWriter(generatedReport, true);
			PrintWriter pw = new PrintWriter(fw, true);

			// Konsolda Başlık çıktısını oluşturma
			System.out.println();
			System.out.println("_________________________________________________________________________");
			System.out.println("Attendance Report\n");
			System.out.println("-------------------------------------------------------------------------");
			pw.println();
			pw.println("________________________________________________________________");
			pw.println("Attendance Report");
			pw.println("----------------------------------------------------------------");
			pw.println();


			//  hasNextLine kullanma
			while (sc.hasNextLine()) {

				String data = sc.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				// öğretmen sınıfını kullanarak öğretmen nesnesini oluşturma
				// token ekledim
				lecturer object = new lecturer(token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()));

				// öğretmendeki öğe içe aktarılan öğeye eşitse
				// parametre ekledim
				if (object.getUnitId().equals(moduleName)) {

					//yazdırma
					System.out.println("Unit \t\t\t:\t" + object.getUnitId());
					System.out.println("Unit Name \t\t:\t" + object.getUnitName());
					System.out.println("Room Number \t\t:\t" + object.getRoomNumber());
					System.out.println("Room Name \t\t:\t" + object.getRoomName());
					System.out.println("Lecturer \t\t:\t" + object.getLecturerName());
					System.out.println("Week# \t\t\t:\t" + weekNo);
					System.out.println("Date \t\t\t:\t" + date + "\n");
					System.out.println("-------------------------------------------------------------------------");

					// Dosya yazma
					pw.println("Unit \t\t\t:\t" + object.getUnitId());
					pw.println("Unit Name \t\t:\t" + object.getUnitName());
					pw.println("Room Number \t:\t" + object.getRoomNumber());
					pw.println("Room Name \t\t:\t" + object.getRoomName());
					pw.println("Lecturer \t\t:\t" + object.getLecturerName());
					pw.println("Week# \t\t:\t" + weekNo);
					pw.println("Date \t\t\t:\t" + date + "\n");
					pw.println("----------------------------------------------------------------");
					pw.println();

					// object değerlerinin atanması
					// doluluk yüzdesi hesaplama
					numberOfStudentsEnrolled = object.getNumberOfStudentsEnrolled();
					roomCapacity = object.getRoomCapacity();
				}
			}

			// variable başlatma
			// attendanceListInReport
			int attendanceCheck = attendanceListInReport(moduleName, date, weekNo);
			System.out.println();
			pw.println();
			//konsol
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Lecturer Confirmed Attendance \t : YES/NO");
			System.out.println("Students Enrolled \t\t : " + numberOfStudentsEnrolled);
			System.out.println("Attendance \t\t\t : " + attendanceCheck);
			System.out.println("Room Capacity \t\t\t : " + roomCapacity);
			System.out.println("Percentage Occupancy \t\t : "
					+ (((double) Math.round(((attendanceCheck * 1.0) / roomCapacity) * 100.0))) + "%");
			System.out.println("_________________________________________________________________________");

			//dosyaya uyan format oluşturma
			pw.println("----------------------------------------------------------------");
			pw.println("Lecturer Confirmed Attendance \t : YES/NO");
			pw.println("Students Enrolled \t\t\t : " + numberOfStudentsEnrolled);
			pw.println("Attendance \t\t\t\t : " + attendanceCheck);
			pw.println("Room Capacity \t\t\t\t : " + roomCapacity);
			pw.println("Percentage Occupancy \t\t\t : "
					+ (((double) Math.round(((attendanceCheck * 1.0) / roomCapacity) * 100.0))) + "%");
			pw.println("________________________________________________________________");


			pw.println();
			pw.println();


			sc.close();
			pw.close();

		} catch (FileNotFoundException e) {


			JOptionPane.showMessageDialog(null, "File not found.");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// bir dönüş değeri olmadan birden çok raporu yazdırmak için bir yöntem oluşturma
	public static void printingSeveralReports() {

		try {

			//attandadancelog okumak için bir dosya nesnesi oluşturma
			File attendanceLogFile = new File("attendancelog.txt");
			Scanner sc = new Scanner(attendanceLogFile);


			sc.nextLine();


			PrintWriter pw = new PrintWriter(generatedReport);


			ArrayList<String> arr = new ArrayList<String>();


			while (sc.hasNextLine()) {

				String data = sc.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				// attandanceclass kullanarak katılım nesnesini oluşturma

				attendance object = new attendance(token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken(), token.nextToken());

				//Bir String değişkeninin başlatılması ve object ile elde edilen UnitID, tarihin ve hafta numarasının saklanması
				String checkingLines = (object.getUnitID() + " " + object.getDate() + " " + object.getWeekNo());
				arr.add(checkingLines);

			}
			// Raporları sırasıyla sıralamak için TreeSet'i kullanma

			TreeSet<String> treeSetList = new TreeSet<String>(arr);

			for (String count : treeSetList) {

				// Yeni bir String tokenizer oluşturma ve sayım değerini atama
				StringTokenizer token1 = new StringTokenizer(count);

				// her bileşeni ayrı ayrı Tokenleme
				String moduleName = token1.nextToken();
				String date = token1.nextToken();
				String weekNo = token1.nextToken();

				// Method raporu çağırma kodu
				report(moduleName, date, weekNo);


				System.out.println();
				pw.println();

			}

			sc.close();
			pw.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found.");
		}
	}

	// öğrencilerin katılımını bir geri dönüşle kontrol etmek için bir yöntem oluşturma
	//  attendanceListInReport için boolean değerleme
	public static boolean attendanceCheck(String studentId, String unitId, String date, String weekNo) {

		// bir boolean başlatmak için onu false atama
		boolean present = false;

		try {

			File attendanceLogFile = new File("attendancelog.txt");
			Scanner sc = new Scanner(attendanceLogFile);


			sc.nextLine();


			while (sc.hasNextLine()) {


				String data = sc.nextLine();
				StringTokenizer token = new StringTokenizer(data);


				attendance object = new attendance(token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken(), token.nextToken());

				// object parametrelerinin eşit olup olmadığını karşılaştırma
				if ((object.getStudentID().equals(studentId)) && (object.getUnitID().equals(unitId))
						&& (object.getDate().equals(date)) && (object.getWeekNo().equals(weekNo))) {

					present = true;
					return present;// değer döndürme

				}

			}
		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(null, "File not found.");
		}
		return present;
	}


	public static void addStudentRecord() {

		try {

			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                         ADD A NEW STUDENT DATA                          ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the student details as follows,");
			System.out.println();
			System.out.println("Eg :StudentID(2022xxx) LastName(Durmaz) FirstName(Melih) UnitID(ECSIXXX)");
			System.out.println();
			System.out.println("Make sure that above data entered are followed by a SPACE.");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String studentNumber = sc.next();
			String lastName = sc.next();
			String firstName = sc.next();
			String unitID = sc.next();


			File studentDetailsFile = new File("studentdetails.txt");


			FileWriter fw = new FileWriter(studentDetailsFile, true);
			PrintWriter pw = new PrintWriter(fw, true);


			pw.println(studentNumber + "\t\t" + lastName + "\t\t\t" + firstName);
			pw.close();


			File moduleRegistrationFile = new File("moduleRegistration.txt");


			FileWriter fw1 = new FileWriter(moduleRegistrationFile, true);
			PrintWriter pw1 = new PrintWriter(fw1, true);


			pw1.println(studentNumber + "\t\t\t" + unitID);
			pw1.close();
			sc.close();

		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(null, "File not found.");

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}


	public static void addLectureRecord() {

		try {


			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                         ADD A NEW LECTURER DATA                         ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the Lecturer as follows,");
			System.out.println();
			System.out.println("Eg :UnitID(ECISXXX) UnitName(SDP01) RoomNumber(201) \n   "
					+ " RoomName(BreakdownRoom) LecturerName(Dr.Ali) RoomCapacty(6) \n    "
					+ "NumberOfStudentsEnrolled(5)");
			System.out.println();
			System.out.println("Make sure that above data entered are followed by a SPACE, \n"
					+ "and should be in one single LINE.");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String unitID = sc.next();
			String unitName = sc.next();
			String roomNumber = sc.next();
			String roomName = sc.next();
			String lecturerName = sc.next();
			int roomCapacity = sc.nextInt();
			int numberOfStudentsEnrolled = sc.nextInt();


			File lecturerFile = new File("lecturer.txt");


			FileWriter fw = new FileWriter(lecturerFile, true);
			PrintWriter pw = new PrintWriter(fw, true);


			pw.println(unitID + "\t\t" + unitName + "\t\t" + roomNumber + "\t\t\t\t" + roomName + "\t\t" + lecturerName
					+ "\t\t\t" + roomCapacity + "\t\t\t\t\t" + numberOfStudentsEnrolled);
			pw.close();
			sc.close();

		} catch (FileNotFoundException e) {


			JOptionPane.showMessageDialog(null, "File not found.");

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	//mevcut öğrenci kayıtlarını düzenlemek için bir yöntem oluşturma
	public static void editStudentRecord() {

		try {



			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                      EDIT AN EXISTING STUDENT DATA                      ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the student details as follows,");
			System.out.println();
			System.out.println("Eg :StudentID(2022xxx) LastName(Durmaz) FirstName(Melih) UnitID(ECSIXXX)");
			System.out.println();
			System.out.println("Make sure that above data entered are followed by a SPACE.");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String studentNumber = sc.next();
			String lastName = sc.next();
			String firstName = sc.next();
			String unitID = sc.next();



			File studentdetailsFile = new File("studentdetails.txt");
			Scanner sc1 = new Scanner(studentdetailsFile);


			sc1.nextLine();


			//Geçici dosya
			File tempFile = new File("temp.txt");
			PrintWriter pw = new PrintWriter(tempFile);

			// heading oluşturma
			pw.println("StudentNo\tLastName\tFirstName");

			// girilen veriler arasında gezinmek için bir loop kullanma
			// using hasNextLine function
			while (sc1.hasNextLine()) {


				String data = sc1.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				// studentDetails için obje oluşturma ve token ekleme

				studentDetails object = new studentDetails(token.nextToken(), token.nextToken(), token.nextToken());

				// object ayrıntılarının giriş verilerine eşit olup olmadığının karşılaştırma
				if (object.getStudentNumber().equals(studentNumber)) {
					// geçici dosyaya  verileri yazdırma
					pw.println(studentNumber + "\t\t" + lastName + "\t\t" + firstName);

				} else {

					pw.println(
							object.getStudentNumber() + "\t\t" + object.getLastName() + "\t\t" + object.getFirstName());
				}
			}

			pw.close();
			sc.close();
			sc1.close();

			// öğrenci detayları dosyasının silinmesi
            // geçici dosyanın adını ve  öğrenci ayrıntıları değiştirme
			studentdetailsFile.delete();
			tempFile.renameTo(studentdetailsFile);

			File moduleRegistrationFile = new File("moduleRegistration.txt");
			Scanner sc2 = new Scanner(moduleRegistrationFile);


			sc2.nextLine();

			// Geçici dosya ve Printwriter

			File tempFile1 = new File("temp1.txt");
			PrintWriter pw1 = new PrintWriter(tempFile1);

			// heading oluşturma
			pw1.println("StudentNo\tUnitID");

			// hasNextline ve loop kullanma

			while (sc2.hasNextLine()) {

				// bir String değişkeni oluşturma ve girdiyi alma

				String data1 = sc2.nextLine();
				StringTokenizer token1 = new StringTokenizer(data1);

				//moduleRegistration object oluşturma ve class'ı
				moduleRegistration object1 = new moduleRegistration(token1.nextToken(), token1.nextToken());

				if (object1.getStudentNumber().equals(studentNumber)) {

					pw1.println(studentNumber + "\t\t" + unitID);

				} else {

					pw1.println(object1.getStudentNumber() + "\t\t" + object1.getUnitID());
				}
			}

			pw1.close();
			sc2.close();

			// öğrenci ayrıntıları dosyasını silme
			// Geçici doyanında adını değiştirme
			moduleRegistrationFile.delete();
			tempFile1.renameTo(moduleRegistrationFile);

		} catch (FileNotFoundException e) {


			JOptionPane.showMessageDialog(null, "File not found.");
		}
	}

	// Mevcut öğretmen kayıtlarını düzenlemek için bir yöntem oluşturma
	public static void editLecturerRecords() {

		try {

			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                     EDIT AN EXISTING LECTURER DATA                      ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the Lecturer as follows,");
			System.out.println();
			System.out.println("Eg :UnitID(ECISXXX) UnitName(SDP01) RoomNumber(201) \n   "
					+ " RoomName(BreakdownRoom) LecturerName(Dr.Furious) RoomCapacty(6) \n    "
					+ "NumberOfStudentsEnrolled(5)");
			System.out.println();
			System.out.println("Make sure that above data entered are followed by a SPACE, \n"
					+ "and should be in one single LINE.");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String unitID = sc.next();
			String unitName = sc.next();
			String roomNumber = sc.next();
			String roomName = sc.next();
			String lecturerName = sc.next();
			int roomCapacity = sc.nextInt();
			int numberOfStudentsEnrolled = sc.nextInt();

			// modül öğretmen ayrıntılarını okumak için object oluşturma

			File lecturerFile = new File("lecturer.txt");
			Scanner sc1 = new Scanner(lecturerFile);


			sc1.nextLine();


			File tempFile = new File("temp.txt");
			PrintWriter pw = new PrintWriter(tempFile);

			// headings
			pw.println("UnitID\tUnitName\tRoomNumber\tRoomName\tLecturerName\tRoomCapacity\tNumberOfStudentsEnrolled");


			while (sc1.hasNextLine()) {

				String data = sc1.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				// öğretmen sınıfını kullanarak öğretmen object oluşturma

				lecturer object = new lecturer(token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()));

				// veri karşılaştırma
				if (object.getUnitId().equals(unitID)) {
					// geçici dosyaya verileri yazdırma
					pw.println(unitID + "\t\t" + unitName + "\t\t" + roomNumber + "\t\t" + roomName + "\t\t"
							+ lecturerName + "\t\t" + roomCapacity + "\t\t" + numberOfStudentsEnrolled);

				} else {

					pw.println(object.getUnitId() + "\t\t" + object.getUnitName() + "\t\t" + object.getRoomNumber()
							+ "\t\t" + object.getRoomName() + "\t\t" + object.getLecturerName() + "\t\t"
							+ object.getRoomCapacity() + "\t\t\t" + object.getNumberOfStudentsEnrolled());
				}
			}

			pw.close();
			sc.close();
			sc1.close();

			// silme ve değiştirme
			lecturerFile.delete();
			tempFile.renameTo(lecturerFile);

		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(null, "File not found.");
		}
	}

	// mevcut öğrenci kayıtlarını silmek için bir yöntem oluşturma
	public static void deleteExistingStudentRecords() {

		try {



			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                     DELETE AN EXISTING STUDENT DATA                      ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the student detail as follows,");
			System.out.println();
			System.out.println("Eg :StudentID(2022xxx)");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String studentNumber = sc.next();


			File studentdetailsFile = new File("studentdetails.txt");
			Scanner sc1 = new Scanner(studentdetailsFile);


			sc1.nextLine();


			File tempFile = new File("temp.txt");
			PrintWriter pw = new PrintWriter(tempFile);


			pw.println("StudentNo\tLastName\tFirstName");


			while (sc1.hasNextLine()) {


				String data = sc1.nextLine();
				StringTokenizer token = new StringTokenizer(data);


				studentDetails object = new studentDetails(token.nextToken(), token.nextToken(), token.nextToken());


				if (!(object.getStudentNumber().equals(studentNumber))) {

					pw.println(
							object.getStudentNumber() + "\t\t" + object.getLastName() + "\t\t" + object.getFirstName());
				}
			}

			pw.close();
			sc.close();
			sc1.close();


			studentdetailsFile.delete();
			tempFile.renameTo(studentdetailsFile);


			File moduleRegistrationFile = new File("moduleRegistration.txt");
			Scanner sc2 = new Scanner(moduleRegistrationFile);


			sc2.nextLine();


			File tempFile1 = new File("temp1.txt");
			PrintWriter pw1 = new PrintWriter(tempFile1);


			pw1.println("StudentNo\tUnitID");


			while (sc2.hasNextLine()) {


				String data1 = sc2.nextLine();
				StringTokenizer token1 = new StringTokenizer(data1);


				moduleRegistration object1 = new moduleRegistration(token1.nextToken(), token1.nextToken());


				if (!(object1.getStudentNumber().equals(studentNumber))) {

					pw1.println(object1.getStudentNumber() + "\t\t" + object1.getUnitID());

				}

			}

			pw1.close();
			sc2.close();

			// silme değiştirme
			moduleRegistrationFile.delete();
			tempFile1.renameTo(moduleRegistrationFile);

		} catch (FileNotFoundException e) {


			JOptionPane.showMessageDialog(null, "File not found.");
		}

	}

	// mevcut öğretmen kayıtlarını silmek için bir yöntem oluşturma
	public static void deleteExistingLecturerRecords() {

		try {



			Scanner sc = new Scanner(System.in);


			System.out.println("_________________________________________________________________________");
			System.out.println("                   DELETE AN EXISTING LECTURER DATA                      ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please Enter the Unit ID as follows,");
			System.out.println();
			System.out.println("Eg :UnitID(ECISXXX).");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("Enter the data : ");


			String unitID = sc.next();


			File lecturerFile = new File("lecturer.txt");
			Scanner sc1 = new Scanner(lecturerFile);


			sc1.nextLine();


			File tempFile = new File("temp.txt");
			PrintWriter pw = new PrintWriter(tempFile);


			pw.println(
					"UnitID\t\tUnitName\tRoomNumber\tRoomName\t\t\tLecturerName\tRoomCapacity\tNumberOfStudentsEnrolled");

			// while loop
			while (sc1.hasNextLine()) {


				String data = sc1.nextLine();
				StringTokenizer token = new StringTokenizer(data);

				// tokenler
				lecturer object = new lecturer(token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken()));


				if (!(object.getUnitId().equals(unitID))) {

					pw.println(object.getUnitId() + "\t\t" + object.getUnitName() + "\t\t" + object.getRoomNumber()
							+ "\t\t\t" + object.getRoomName() + "\t\t\t" + object.getLecturerName() + "\t\t\t"
							+ object.getRoomCapacity() + "\t\t\t\t\t" + object.getNumberOfStudentsEnrolled());
				}
			}

			pw.close();
			sc.close();
			sc1.close();


			lecturerFile.delete();
			tempFile.renameTo(lecturerFile);

		} catch (FileNotFoundException e) {


			JOptionPane.showMessageDialog(null, "File not found.");
		}
	}
}
