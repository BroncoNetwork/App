package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.R;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment implements NetworkFragment{
	
	private TextView  firstName, lastName, major, email;
	private ListView courseList;
	private ArrayList<String> courseArray;
	private ImageView profilePic;
	private String name = "Profile";
	private int icon = R.drawable.icon_profile;
	private MainEntry mainEntry;
	
	public ProfileFragment(MainEntry mainEntry) {
		  this.mainEntry = mainEntry;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.profile, container, false);
		//firstName = (TextView)fragView.findViewById(R.id.first_Name);
		//lastName = (TextView)fragView.findViewById(R.id.last_Name);
		//major = (TextView)fragView.findViewById(R.id.major_);
		//email = (TextView)fragView.findViewById(R.id.email_);
		courseList = (ListView)fragView.findViewById(R.id.course_list);
		courseArray = new ArrayList<String>();
		
		courseArray.add(new String());

		courseList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(position > 0)
					go_to_course((String) ((TextView) (view.findViewById(R.id.course_number_list))).getText());
			}

	    });
		
		/*courseList.setAdapter(new ProfileCourseAdapter(courseArray, getActivity()));
		courseList.invalidateViews();*/
		
		setData();
		return fragView;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	//get course data from databases
	public void setData() {
		
		//user info is retrieved in ProfileCourseAdapter
		courseArray.clear();
		courseArray.add(new String());
		for(int i = 0;i < mainEntry.getCourses().length;i++)
		{
			if(!mainEntry.getCourses()[i].equals(""))
			{
				courseArray.add(mainEntry.getCourses()[i]);
			}
		}
		courseList.setAdapter(new ProfileCourseAdapter(courseArray, getActivity()));
		courseList.invalidateViews();
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}

	//This function will call the function inside MainEntry activity to jump to 
	//CoursePageFragment
	public void go_to_course(String chosenCourse) {
		mainEntry.gotoCoursePage(chosenCourse);
	}
}
