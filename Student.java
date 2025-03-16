
public class Student {

	private int id;
	private String name;
	private double gpa;
	private String address;
	
	

	public Student(int id, String address,String name,double gpa) {
		super();
		this.address = address; 
		this.id = id;
		this.name = name;
		this.gpa = gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	public double getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) { 
		this.gpa = gpa;
	}
	@Override 
	public String toString() {
		return "[id=" + id + ", name=" + name + ", Address=" + address +", gpa=" + gpa + "]"+"\n"; 
	}

}


