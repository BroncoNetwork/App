package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import cs356.bronconetwork.Course;
import cs356.bronconetwork.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment implements NetworkFragment{
	
	private TextView  firstName, lastName, major, email;
	private ListView courseList;
	private ArrayList<Course> courseArray;
	private ImageView profilePic;
	private String name = "Profile";
	private int icon = R.drawable.icon_profile;
	private Context c;
	
	  public ProfileFragment(Context c) {
		  this.c = c;
	  }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("PROFILE");
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.profile, container, false);
		firstName = (TextView)fragView.findViewById(R.id.first_Name);
		lastName = (TextView)fragView.findViewById(R.id.last_Name);
		major = (TextView)fragView.findViewById(R.id.major_);
		email = (TextView)fragView.findViewById(R.id.email_);
		courseList = (ListView)fragView.findViewById(R.id.course_list);
		courseArray = new ArrayList<Course>();
		getData();
		return fragView;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	//get data from databases
	public void getData() {
		//test, fill in with sql queries later
		firstName.setText("Steve");
		lastName.setText("Nham");
		major.setText("Computer Science");
		email.setText("snham@csupomona.edu");
		Course course1 = new Course("CS", "130");
		Course course2 = new Course("CS", "140");
		Course course3 = new Course("CS","128");
		courseArray.add(course1);
		courseArray.add(course2);
		courseArray.add(course3);
		courseList.setAdapter(new ProfileCourseAdapter(courseArray, getActivity()));
		courseList.invalidateViews();
	}
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}

}
