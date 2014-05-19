package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;
import cs356.bronconetwork.R.id;
import cs356.bronconetwork.R.layout;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("ValidFragment")
public class CoursePageFragment extends Fragment implements NetworkFragment {
	
	private String name = "CoursePage";
	private int icon = R.drawable.icon_courses;
	private Context c;
	
	private TextView mComments;
	private Button mPost;
	private EditText mText;
	private ListView mCoursePageList;
	private ArrayList<Post> postArray;
	private Time currentTime;
	private CustomAdapter mAdapter = new CustomAdapter(postArray,getActivity(),0);
  
	public CoursePageFragment(Context c) {
		//getActivity().getActionBar().setTitle(course);
		this.c = c;
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_course_page, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();
		
		mCoursePageList = (ListView)getView().findViewById(R.id.course_page_list);
		postArray = new ArrayList<Post>();
		
		mPost = (Button)getView().findViewById(R.id.post_button);
		mPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mText.getText().length() >= 5) {
					post();
				}
			}
		});
		
		mComments = (TextView)getView().findViewById(R.id.comments);
		mText = (EditText)getView().findViewById(R.id.text_bar);

		mCoursePageList.setAdapter(new CustomAdapter(postArray, getActivity(), 0));
		// Creates the first item (course info)
		postArray.add(0,new Post());

		//test posts
		Time time = new Time();
		Post post = new Post();
		time.set(0,0,0,20,0,2013);
		post.setAuthor("Brian");
		post.setTarget("CS356");
		post.setTime(time);
		post.setMessage("Hey guys this is a test to see if the post works.");
		postArray.add(0, post);
		
		post = new Post();
		time.set(7,4,2014);
		post.setAuthor("Someone");
		post.setTarget("Class");
		post.setTime(time);
		post.setMessage("Hey guys this is a test to see if the post works.");
		postArray.add(0, post);
	}
	
	public String getName() {
		return name;
	}
  
	public int getDrawableId() {
		return icon;
	}
	
	public void post() {
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
					Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mText.getWindowToken(), 0);
		System.out.println("POST");
		Post post = new Post();
		post.setAuthor("Brian");
		post.setTarget("Brian");
		post.setTime(System.currentTimeMillis());
		post.setMessage(mText.getText().toString());
		postArray.add(0, post);
		mText.setText("");
		mCoursePageList.invalidateViews();
	}
}