package cs356.bronconetwork;

public class Course {
	
	private String major;
	private String courseNum;
	
	public Course(String major, String courseNum) {
		this.major = major;
		this.courseNum = courseNum;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String getCourseNum() {
		return courseNum;
	}
}
