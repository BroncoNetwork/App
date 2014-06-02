package cs356.bronconetwork.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.R;
@SuppressLint("ValidFragment")
public class CoursesFragment extends Fragment implements NetworkFragment {
	
	private String name = "Courses";
	private int icon = R.drawable.icon_courses;
	private Spinner major_list;
	private Spinner course_list;
	private ListView my_courses;
	private Button go_to_course_button;
	private ArrayList<String> course_array;
	private MainEntry mainEntry;
	private CourseListAdapter courseListAdapter;
	private String[] major = {
      "ABM", "ACC", "AG", "AGS", "AHS", "AMM", "ANT", "ARC", "ARO", "ART", "AVS",
      "BHS", "BIO", "BOT", "BUS", "CE", "CEU", "CHE", "CHM", "CIS", "CLS", "COM", "CPU", "CS", "CSA",
      "DAN",
      "EBZ", "EC", "ECE", "EDD", "EDS", "EDU", "EGR", "ELI", "ENG", "ENV", "ETC", "ETE", "ETM", "ETP", "ETT", "EWS",
      "FL", "FMA", "FN", "FRL", "FST",
      "GBA", "GED", "GEO", "GSC",
      "HPS", "HRT", "HST",
      "IA", "IBM", "IE", "IGE", "IME", "INA", "IPC",
      "KIN",
      "LA", "LIS", "LRC", "LS",
      "MAE", "MAT", "ME", "MFE", "MHR", "MIC", "MPA", "MSL", "MTE", "MU", 
      "NSE",
      "PHL", "PHY", "PLS", "PLT", "PSY",
      "RS",
      "SCI", "SME", "SOC", "SPN", "SSC", "STA", "STS", "SW",
      "TED", "TH", "TOM",
      "UNDC", "URP",
      "ZOO"
      
	};
	private String[] cs_courses = {
			"101", "128", "130", "140", "141", 
			"200", "210", "240", "241", "245", "256", "260", "264", "299",
			"301", "311", "331", "352", "356", "370", "375", "380", 
			"400", "408", "411", "420", "431", "435", "445", "450", "460", "461", "462", "463", "470", "480", "481", "490", "499"
	};
	private String selected_major = "";
	private String selected_course = "";
  
	public CoursesFragment(MainEntry mainEntry) {
		this.mainEntry = mainEntry;
	}
	  
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.fragment_course_list, container, false);
		final View fragView = inflater.inflate(R.layout.fragment_course_list, container, false);
		go_to_course_button = (Button)fragView.findViewById(R.id.go_course);
		go_to_course_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(selected_major.length() > 0 && selected_course.length() > 0) {
					Toast.makeText(mainEntry, "Go to "+selected_major+selected_course, Toast.LENGTH_LONG).show();
					go_to_course(selected_major + " " + selected_course);
				}
			}
		});
		
		major_list = (Spinner) fragView.findViewById(R.id.major_);
		
		ArrayAdapter<String> major_adapter = new ArrayAdapter<String>(mainEntry, R.layout.spinner_text_layout, major);
		major_list.setAdapter(major_adapter);
		major_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			boolean first_open_major = true;
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(first_open_major)
					first_open_major = false;
				else {
		        	int position = major_list.getSelectedItemPosition();
					selected_major = major[position];
		            Toast.makeText(mainEntry, "You have selected "+selected_major, Toast.LENGTH_LONG).show();
		            
		            course_list = (Spinner) fragView.findViewById(R.id.class_num);
		            
		    		switch(selected_major) {
		    		case "CS":
		    			ArrayAdapter<String> class_adapter = new ArrayAdapter<String>(mainEntry, R.layout.spinner_text_layout, cs_courses);
		    			course_list.setAdapter(class_adapter);
		    			course_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    	            boolean first_open_course = true;
		    		        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    		        	if(first_open_course)
		    		        		first_open_course = false;
		    		        	else {
			    					int position = course_list.getSelectedItemPosition();
			    		            selected_course = cs_courses[position];
			    					Toast.makeText(mainEntry,"You have selected "+selected_major+selected_course,Toast.LENGTH_LONG).show();
		    					}
		    				}
		    		        @Override
		    		        public void onNothingSelected(AdapterView<?> arg0) {
		    		               
		    		        }
		    			});
		    		}
				}
	        }
	        @Override
	        public void onNothingSelected(AdapterView<?> arg0) {
	               
	        }
		});

		my_courses = (ListView) fragView.findViewById(R.id.curr_course_list);
		my_courses.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position > 0) {
					String target = (String) courseListAdapter.getItem(position);
					go_to_course(target);
				}
			}

	    });
		
		getData();
		
		return fragView;
	}
	
	public void getData()
	{
		course_array = new ArrayList<String>();
		course_array.add(new String());
		String[] courses = mainEntry.getCourses();
		for(int i=0; i < courses.length; i++)
		{
			if(!courses[i].equals(""))
			{
				course_array.add(courses[i]);
			}
		}
		
		courseListAdapter = new CourseListAdapter(course_array, mainEntry);
		my_courses.setAdapter(courseListAdapter);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
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