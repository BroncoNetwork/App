package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;
import cs356.bronconetwork.UserData;
import cs356.bronconetwork.R.id;
import cs356.bronconetwork.R.layout;
import cs356.bronconetwork.Register.registerActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
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
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("COURSEPAGE");
		super.onCreate(savedInstanceState);
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.fragment_course_page, container, false);
		View fragView = inflater.inflate(R.layout.fragment_course_page, container, false);
		
		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();
		
		mCoursePageList = (ListView)fragView.findViewById(R.id.course_page_list);
		postArray = new ArrayList<Post>();
		
		mPost = (Button)fragView.findViewById(R.id.post_button);
		mPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mText.getText().length() >= 5) {
					post();
				}
			}
		});
		
		mComments = (TextView)fragView.findViewById(R.id.comments);
		mText = (EditText)fragView.findViewById(R.id.text_bar);

		mCoursePageList.setAdapter(new CustomAdapter(postArray, getActivity(), 0));
		// Creates the first item (course info)
		postArray.add(0,new Post());
		
		getData();
		return fragView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		//load online database
		//use functions of MainEntry to get data of current user
		
		
		/*currentTime = new Time(Time.getCurrentTimezone());
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
		postArray.add(0,new Post());*/
		
		//message("stupid");
		/*String target = ((MainEntry)getActivity()).currentCourse;
		new retrieveDataActivity().execute(target);*/
		//getData();
		//test posts
		/*Time time = new Time();
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
		postArray.add(0, post);*/
	}
	
	public void getData()
	{
		//String target = ((MainEntry)getActivity()).getCurrentCourse();
		String target = "CS356";
		if(target.equals(""))
			message("no current course");
		else
			new retrieveDataActivity().execute(target);
	}
	
	public class retrieveDataActivity  extends AsyncTask<String,Void,String>{


		   protected void onPreExecute(){
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try{
		            String course = (String)arg0[0];
		            String link = "http://bronconetwork.comuv.com/getPost.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		            nameValuePairs.add(new BasicNameValuePair("target",course));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            in.close();
		            return sb.toString();
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			      message("NOTICE " + result );
		   }
		   
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
		//post.setTime(System.currentTimeMillis());
		//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		post.setTime();
		post.setMessage(mText.getText().toString());
		postArray.add(0, post);
		mCoursePageList.invalidateViews();
		
		//insert into database
		String user = ((MainEntry)getActivity()).getUser();
		String msg = mText.getText().toString();
		String timeStamp = post.getTime();
		String target = "CS356";
		new postActivity().execute(target,user,msg,timeStamp);
		
		mText.setText("");
	}
	
	public class postActivity  extends AsyncTask<String,Void,String>{


		   protected void onPreExecute(){
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try{
		            String course = (String)arg0[0];
		            String user = (String)arg0[1];
		            String msg = (String)arg0[2];
		            String time = (String)arg0[3];
		            String link = "http://bronconetwork.comuv.com/post.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		            nameValuePairs.add(new BasicNameValuePair("target",course));
		            nameValuePairs.add(new BasicNameValuePair("author",user));
		            nameValuePairs.add(new BasicNameValuePair("msg",msg));
		            nameValuePairs.add(new BasicNameValuePair("timestamp",time));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            in.close();
		            return sb.toString();
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			   
		   }
		   
		}
	
	
	
	
	
	public void message(String message)
	{
		Toast.makeText(c, message, Toast.LENGTH_LONG).show();
	}
}