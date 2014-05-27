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

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;

@SuppressLint("ValidFragment")
public class NewsfeedFragment extends Fragment implements NetworkFragment {

	private ListView mNewsfeedList;
	private ArrayList<Post> postArray;
	private String name = "Newsfeed";
	private int icon = R.drawable.icon_newsfeed;
	private String[] courses;
	
	private Time currentTime;
	
	public NewsfeedFragment(String[] newCourses)
	{
		courses = newCourses;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View fragView = inflater.inflate(R.layout.newsfeed, container, false);
		
		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();

		/*mPost = (Button)fragView.findViewById(R.id.post_button);
		mPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mText.getText().length() >= 5) {
					post();
				}
			}
		});*/
		
		mNewsfeedList = (ListView)fragView.findViewById(R.id.newsfeed_list);
		postArray = new ArrayList<Post>();

		getData();
		return fragView;
	}

	public void getData()
	{
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
		            String link = "http://bronconetwork.comuv.com/getNewsFeed.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
		            nameValuePairs.add(new BasicNameValuePair("target1", courses[0]));
		            nameValuePairs.add(new BasicNameValuePair("target2", courses[1]));
		            nameValuePairs.add(new BasicNameValuePair("target3", courses[2]));
		            nameValuePairs.add(new BasicNameValuePair("target4", courses[3]));
		            nameValuePairs.add(new BasicNameValuePair("target5", courses[4]));
		            nameValuePairs.add(new BasicNameValuePair("target6", courses[5]));
		            nameValuePairs.add(new BasicNameValuePair("target7", courses[6]));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line + "__");
		            }
		            return sb.toString();
		      } catch(Exception e) {
		         return new String("Exception: " + e.getMessage());
		      }
		   }		    
		   
		   @Override
		   public void onPostExecute(String result) {
			   if(result.length() > 2)
			   {
				   String[] temp = result.split("__");
				   for(int i = 0; i < temp.length; i++)
				   {
					   String[] temp2 = temp[i].split("<a>");
					   postArray.add(postArray.size(), new Post(temp2[0], temp2[1], temp2[2], temp2[3]));
				   }
			   }
			   mNewsfeedList.setAdapter(new CustomAdapter(postArray, getActivity(), 1,""));
		   }
		   
		}

	
	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	public void refresh() {
		
	}
	
	public void message(String message)
	{
		Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
	}
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}
}
