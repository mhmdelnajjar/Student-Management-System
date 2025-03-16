import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class test {
	static Scanner scan = new Scanner(System.in);
	static treeTable table = new treeTable();

	public static <E> void main(String[] args) {
		table.loadFromFile("data.txt");
		boolean x = false;
		while (x != true) {
			int ch = menu();

			if (ch > 12 || ch < 1) {
				System.err.println(" Wrong Choice Pick From [1-12]!");
			} else {
				if (ch == 1) {
					System.out.print(" Enter Name: ");
					String name = scan.next();
					System.out.print(" Enter ID: ");
					int idd = scan.nextInt();
					System.out.print(" Enter Address: ");
					String addy = scan.next();
					System.out.print(" Enter GPA: ");
					double gpa = scan.nextDouble();
					Student temp = new Student(idd, name, addy, gpa);
					table.insert(temp);
				} else if (ch == 2) {
					System.out.print(" Enter ID: ");
					int idd = scan.nextInt();
					table.Find(idd);
				} else if (ch == 3) {
					System.out.print(" Enter ID: ");
					int idd = scan.nextInt();
					table.update(idd);
				} else if (ch == 4) {
					System.out.print(" Enter ID: ");
					int idd = scan.nextInt();
					table.remove(idd);
				} else if (ch == 5) {
					System.out.print(" Enter Year: ");
					int year = scan.nextInt();
					table.printStudent(year);

				} else if (ch == 6) {
					table.printAll();
				} else if (ch == 7) {
					System.out.print(" Enter GPA: ");

					double gpa = scan.nextDouble();
					ArrayList<Student> students = table.studentWithGPA(gpa);
					System.out.print(" Students With GPA Below " + gpa + ":\n");
					for (Student st : students) {
						System.out.println(st);

					}
				} else if (ch == 8) {
					ArrayList<Double> gpas = table.highestGpa();
					Collections.sort(gpas);
					int size = gpas.size() - 1;
					double max = gpas.get(size);
					System.out.println(" Highest GPA OverAll Is: " + max);
					
				}

				else if (ch == 9) {
					System.out.print(" Enter Year: ");
					int year = scan.nextInt();
					Student s = table.highestGPA(year);
					System.out.println(s);
				} else if (ch == 10) {
					System.out.print(" Number Of Students: ");
					System.out.println(table.numOfAllStudents());
				} else if (ch == 11) {
					System.out.print(" Enter Year: ");
					int year = scan.nextInt();
					System.out.print(" Number Of Students In " + year + ":\n");
					System.out.println(table.numOfStudents(year));
				} else {
					System.exit(0);
				}
			}
		}

	}

	public static int menu() {
		System.out.println();
		System.out.println(" [1] Insert A Student");
		System.out.println(" [2] Find A Student");
		System.out.println(" [3] Update A Student");
		System.out.println(" [4] Remove A Student");
		System.out.println(" [5] Print Students In A Year");
		System.out.println(" [6] Print All Students");
		System.out.println(" [7] Students With Below A Specfic GPA");
		System.out.println(" [8] Student With The Highest GPA");
		System.out.println(" [9] Print Highest GPA Student In A Year");
		System.out.println(" [10] The Number Of OverAll Students");
		System.out.println(" [11] The Number Of Student In A Specfic Year");
		System.out.println(" [12] Exit");
		System.out.print("\n Choice : ");

		int ch = scan.nextInt();
		return ch;
	}

}
