import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class treeTable<E> {
	// import HeapPriorityQueue.Node;
	private StudentTree[] hashArray = hashArray = new StudentTree[25];
	private StudentTree defunct;

	public treeTable() {

		defunct = new StudentTree(-1);
		ADDYEARS();
	}

	public int hashFunc(int year) {
		return year % hashArray.length;
	}

	public boolean isFull() {
		for (int i = 0; i < hashArray.length; i++)
			if (hashArray[i] == null || hashArray[i] == defunct)
				return false;
		return true;
	}

	public void displayTable() {
		System.out.println("\nContent of the Hash:");
		for (int j = 0; j < hashArray.length; j++)
			if (hashArray[j] != null)
				hashArray[j].display();
		// System.out.println(hashArray[j].getYear());

	}

	public void ADDYEARS() {

		for (int i = 2000; i < 2000 + hashArray.length; i++) {
			int hashVal = hashFunc(i);
			while (hashArray[hashVal] != null && hashArray[hashVal] != defunct) {
				++hashVal;
				hashVal %= hashArray.length;
			}
			hashArray[hashVal] = new StudentTree(i);

		}
	}

	public int extractYear(int id) {
		String StringID = Integer.toString(id);

		String StringYear = "";

		for (int i = 0; i < 4; i++) {
			char currentChar = StringID.charAt(i);
			StringYear += currentChar;

		}
		int integerYear = Integer.valueOf(StringYear);
		return integerYear;
	}

	public void insert(Student student) {

		int Year = extractYear(student.getId());

		for (int j = 0; j < hashArray.length; j++)
			if (hashArray[j].getYear() == Year) {
				hashArray[j].insert(student.getId(), student);
				System.out.println("Added successfully to the year " + hashArray[j].getYear());
				uploadToFile(student, "data.txt");
				break;
			}

	}

	public Student Find(int id) {
	    int year = extractYear(id);
	    int hashVal = hashFunc(year);
	    int start = hashVal;

	    while (hashArray[hashVal] != null && hashArray[hashVal].getYear() == year) {
	        Student foundStudent = (Student) hashArray[hashVal].search(id);

	        if (foundStudent != null) {
	            System.out.println(foundStudent);
	            System.out.println("Student Found.");
	            return foundStudent;
	        }
	        hashVal = (hashVal + 1) % hashArray.length;

	        if (hashVal == start) {
	           
	            break;
	        }
	    }

	    System.out.println("Student with ID " + id + " not found.");
	    return null;
	}

	public Student update(int id) {
	    Student student = Find(id);

	    if (student != null) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("1. Change id");
	        System.out.println("2. Change name");
	        System.out.println("3. Change Address");
	        System.out.println("4. Change GPA");
	        System.out.print("Enter your choice: ");

	        
	        if (scanner.hasNextInt()) {
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Write the new ID: ");
	                    if (scanner.hasNextInt()) {
	                        int newId = scanner.nextInt();
	                        student.setId(newId);
	                        System.out.println("ID updated successfully.");
	                    } else {
	                        System.out.println("Invalid input for ID. Update failed.");
	                    }
	                    break;
	                case 2:
	                    System.out.print("Write the new name: ");
	                    String newName = scanner.next();
	                    student.setName(newName);
	                    System.out.println("Name updated successfully.");
	                    break;
	                case 3:
	                    System.out.print("Write the new Address: ");
	                    String newAddress = scanner.next();
	                    student.setAddress(newAddress);
	                    System.out.println("Address updated successfully.");
	                    break;
	                case 4:
	                    System.out.print("Write the new GPA: ");
	                    if (scanner.hasNextDouble()) {
	                        double newGPA = scanner.nextDouble();
	                        student.setGpa(newGPA);
	                        System.out.println("GPA updated successfully.");
	                    } else {
	                        System.out.println("Invalid input for GPA. Update failed.");
	                    }
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please choose a valid option.");
	            }
	        } else {
	            System.out.println("Invalid input. Update failed.");
	        }

	        return student;
	    }

	    System.out.println("Student with ID " + id + " not found. Update failed.");
	    return null;
	}


	public boolean remove(int studentId) {
		int yearOfAdmission = extractYear(studentId);
		int index = hashFunc(yearOfAdmission);
		deleteFromFile(studentId);

		while (hashArray[index] != null && hashArray[index].getYear() != yearOfAdmission) {
			index = (index + 1) % 25;
		}

		if (hashArray[index] == null) {
			return false;
		}

		return hashArray[index].remove(studentId);
	}

	public void printStudent(int year) {
		int index = hashFunc(year);
		if (hashArray[index] != null && hashArray[index].getYear() == year) {
			hashArray[index].printInOrder();
		} else {
			System.out.println("No students found for the year " + year);
		}
	}

	public void printAll() {
		for (StudentTree bst : hashArray) {
			if (bst != null) {
				bst.printPreOrder();
			}
		}
	}

	public ArrayList<Student> studentWithGPA(double gpa) {
		ArrayList<Student> temp = new ArrayList<Student>();
		for (StudentTree tree : hashArray) {
			ArrayList<Student> sts = tree.getStudent();
			for (Student st : sts) {
				if (st.getGpa() < gpa) {
					temp.add(st);
				}
			}
		}
		
		return temp;
	}

	public ArrayList<Double> highestGpa() {
		ArrayList<Double> gpas = new ArrayList<>();
		double maxGPA = 0;
		for (StudentTree tree : hashArray) {
			ArrayList<Student> sts = tree.getStudent();
			for (Student s : sts) {
				maxGPA = Math.max(maxGPA, s.getGpa());
			}
			gpas.add(maxGPA);
		}
		
		return gpas;

	}

	public Student highestGPA(int year) {
		int hashVal = hashFunc(year);
		int start = hashVal;
		while (hashArray[hashVal] != null) {
			if (hashArray[hashVal].getYear() == year) {
				StudentTree tree = hashArray[hashVal];
				Student stMax = tree.GetMaxGpa();
				System.out.println(stMax);
				return stMax;

			}
		}
		return null;
	}

	public int numOfAllStudents() {
		int total = 0;
		for (int i = 2000; i < 2025; i++) {
			int index = hashFunc(i);

			StudentTree tree = hashArray[index];
			total += tree.count();

		}
		return total;

	}

	public int numOfStudents(int year) {
		int total = 0;
		int index = hashFunc(year);
		StudentTree t = hashArray[index];
		total = t.count();
		return total;

	}

	public void deleteFromFile(int idToRemove) {
		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 4) {
					String name = data[1];
					String address = data[2];
					double gpa = Double.parseDouble(data[3]);
					int year = extractYear(idToRemove);

					int id = Integer.parseInt(data[0]);

					if (id == idToRemove) {

					} else {
						Student st = new Student(id, name, address, gpa);
						uploadToFile(st, "temp.txt");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		File oldFile = new File("temp.txt");

		File newFile = new File("data.txt");

		newFile.delete();
		oldFile.renameTo(newFile);

	}

	public void uploadToFile(Student student, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

			writer.write("\n" + student.getId() + "," + student.getName() + "," + student.getAddress() + ","
					+ student.getGpa());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");
				if (data.length == 4) {
					int id = Integer.parseInt(data[0]);
					String name = data[1];
					String address = data[2];
					double gpa = Double.parseDouble(data[3]);
					int year = extractYear(id);

					Student student = new Student(id, name, address, gpa);
					int hash = year % 25;
					hashArray[hash].insert(student.getId(), student);
				} else {
					System.out.println("Invalid data format in the file");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
