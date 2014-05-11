package cs356.bronconetwork;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewsfeedFragment extends Fragment {

	Button mPost;
	EditText mText;
	ListView mNewsfeedList;
	ArrayList<Post> postArray;
	
	Time currentTime;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("NEWSFEED");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View fragView = inflater.inflate(R.layout.newsfeed, container, false);
		
		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();

		mPost = (Button)fragView.findViewById(R.id.post_button);
		mPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mText.getText().length() >= 5) {
					post();
				}
			}
		});
		mText = (EditText)fragView.findViewById(R.id.text_bar);
		mNewsfeedList = (ListView)fragView.findViewById(R.id.newsfeed_list);
		postArray = new ArrayList<Post>();

		//test posts
		Time time = new Time();
		Post post = new Post();
		time.set(0,0,0,20,0,2013);
		post.setAuthor("Brian");
		post.setTarget("CS356");
		post.setTime(time);
		post.setMessage("Hey guys this is a test to see if the post works.");
		postArray.add(post);
		
		post = new Post();
		time.set(7,4,2014);
		post.setAuthor("Someone");
		post.setTarget("Class");
		post.setTime(time);
		post.setMessage("Hey guys this is a test to see if the post works.");
		postArray.add(post);

		mNewsfeedList.setAdapter(new CustomAdapter(postArray, getActivity()));
		System.out.println("NEWSFEED");
		return fragView;
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	public void post() {
		System.out.println("POST");
		Post post = new Post();
		post.setAuthor("Brian");
		post.setTarget("Brian");
		post.setTime(System.currentTimeMillis());
		post.setMessage(mText.getText().toString());
		postArray.add(0, post);
		mText.setText("");
		mNewsfeedList.invalidateViews();
	}
}
