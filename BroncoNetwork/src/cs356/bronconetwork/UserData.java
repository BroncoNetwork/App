package cs356.bronconetwork;

import java.util.ArrayList;

import android.app.Application;

public class UserData extends Application{

	private String userName = "";
	private String email = "";
	private String[] courses = new String[7]; 
	private String currentCourse = "";
	
	public UserData(){}
	
	public void setUserName(String newUserName)
	{
		userName = newUserName;
	}
	
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	
	public void setCourse(String newCourse,int index)
	{
		courses[index] = newCourse;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String[] getCourses()
	{
		return courses;
	}
	
	public String getCurrentCourse()
	{
		return currentCourse;
	}
	
	public void setCurrent(String course)
	{
		currentCourse = course;
	}
}
