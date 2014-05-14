package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;

public class NewsfeedFragment extends Fragment implements NetworkFragment {

	private TextView mComments;
	private Button mPost;
	private EditText mText;
	private ListView mNewsfeedList;
	private ArrayList<Post> postArray;
	private String name = "Newsfeed";
	private int icon = R.drawable.icon_newsfeed;
	
	private Time currentTime;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		
		mComments = (TextView)fragView.findViewById(R.id.comments);
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
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}
}
