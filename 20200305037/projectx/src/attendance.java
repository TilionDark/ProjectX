
public class attendance {
	

	private String RoomNo;
	private String StudentID;
	private String UnitID;
	private String LecturerName;
	private String Date;
	private String WeekNo;
	

	public attendance(String roomNo, String studentID, String unitID, String lecturerName, String date, String weekNo) {
		
		super();
		RoomNo = roomNo;
		StudentID = studentID;
		UnitID = unitID;
		LecturerName = lecturerName;
		Date = date;
		WeekNo = weekNo;
		
	}
	

	public String getRoomNo() {
		return RoomNo;
	}
	
	public String getStudentID() {
		return StudentID;
	}
	
	public String getUnitID() {
		return UnitID;
	}
	
	public String getLecturerName() {
		return LecturerName;
	}
	
	public String getDate() {
		return Date;
	}
	
	public String getWeekNo() {
		return WeekNo;
	}
	
	

}
