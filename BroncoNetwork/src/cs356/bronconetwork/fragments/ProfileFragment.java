package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cs356.bronconetwork.Course;
import cs356.bronconetwork.R;

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
		//getData();
		//new GetUserData
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
	/*public void getData() {
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
	}*/
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}
	
	
	public class GetUserData  extends AsyncTask<String,Void,String>{
		
		   protected void onPreExecute(){

		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try{
		            String username = (String)arg0[0];
		            //String password = (String)arg0[1];
		            String link = "http://bronconetwork.comuv.com/profile.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		            nameValuePairs.add(new BasicNameValuePair("username",username));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
//		           FileOutputStream fOut = openFileOutput("outputtesting.txt",
//		        		   MODE_WORLD_READABLE);
//		           OutputStreamWriter osw = new OutputStreamWriter(fOut); 	
	           
	           while ((line = in.readLine()) != null) {
	              sb.append(line);
	              Log.i("DATABASE INFO", line);
	              //osw.write(line);
	              break;
	            }
	           //osw.close();
	            in.close();
		            return sb.toString();
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			   //result = result.trim();
			   	/*firstName.setText("Steve");
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
				courseList.invalidateViews();*/
		   }
		   
		}

}
