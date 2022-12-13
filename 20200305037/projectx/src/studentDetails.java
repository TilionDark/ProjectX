
public class studentDetails {
	

	private String StudentNumber;
	private String LastName;
	private String FirstName;
	

	public studentDetails(String studentNumber, String lastName, String firstName) {
		super();
		StudentNumber = studentNumber;
		LastName = lastName;
		FirstName = firstName;
	}
	

	public String getStudentNumber() {
		return StudentNumber;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	
}
