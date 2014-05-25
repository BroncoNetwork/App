package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cs356.bronconetwork.Mail;
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
import android.util.Log;
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
	
	private String name = ""; // CoursePage initialization and setup are all based on the name
							  // to load a new CoursePage, just setName(newCourse) and getData();
	private int icon = R.drawable.icon_courses;
	
	private TextView mComments;
	private Button mPost;
	private EditText mText;
	private ListView mCoursePageList;
	private ArrayList<Post> postArray = new ArrayList<Post>();
	private Time currentTime;
	private CustomAdapter mAdapter;
  
	public CoursePageFragment(String course) {
		name = course;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.fragment_course_page, container, false);
		View fragView = inflater.inflate(R.layout.fragment_course_page, container, false);
		
		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();
		
		mCoursePageList = (ListView) fragView.findViewById(R.id.course_page_list);
		
		mPost = (Button) fragView.findViewById(R.id.post_button);
		mPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mText.getText().length() >= 5) {
					post();
				}
			}
		});
		
		mComments = (TextView) fragView.findViewById(R.id.comments);
		mText = (EditText) fragView.findViewById(R.id.text_bar);
		
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
		if(name.equals(""))
			message("no current course");
		else
			new retrieveDataActivity().execute();
	}
	
	public class retrieveDataActivity extends AsyncTask<String, Void, String>{


		   protected void onPreExecute() {
			   postArray.clear();
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try {
		            String link = "http://bronconetwork.comuv.com/getPost.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		            nameValuePairs.add(new BasicNameValuePair("target", name));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            String ans = sb.toString().trim().substring(0, sb.toString().trim().indexOf("<!--"));
		            return ans;
		      } catch(Exception e) {
		         return new String("Exception: " + e.getMessage());
		      }
		   }		    
		   
		   @Override
		   public void onPostExecute(String result) {
			   result = result.trim();
			   // Creates the first item (course info)
			   postArray.add(0,new Post());
			   // add the rest of the posts if there are any
			   if(result.length() > 0) {
				   result = result.substring(0, result.length()-1);
	       			StringTokenizer eachPost = new StringTokenizer(result, "`");
	       			while(eachPost.hasMoreTokens()) {
	       				String next = eachPost.nextToken();
	       				Log.i("next", next);
		       			StringTokenizer eachEle = new StringTokenizer(next, "|");
		       			while(eachEle.hasMoreTokens()) {
		       				String author = eachEle.nextToken();
		       				String target = eachEle.nextToken();
		       				String msg = eachEle.nextToken();
		       				String time = eachEle.nextToken();
		       				Log.i("each", author + "." + target + "." + msg + "." + time);
		       				postArray.add(0, new Post(author, target, msg, time));
		       			}
	       			}
			   }
			   mAdapter = new CustomAdapter(postArray, getActivity(), 0);
			   mCoursePageList.setAdapter(new CustomAdapter(postArray, getActivity(), 0));
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
		new postActivity().execute(name, user, msg, timeStamp);
		
		mText.setText("");
	}
	
	public class postActivity  extends AsyncTask<String,Void,String> {


		   protected void onPreExecute() {
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try{
		            String course = (String) arg0[0];
		            String user = (String) arg0[1];
		            String msg = (String) arg0[2];
		            String time = (String) arg0[3];
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
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void message(String message)
	{
		Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
	}
}