package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cs356.bronconetwork.DownloadImageTask;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;


public class CustomAdapter extends BaseAdapter {
	private ArrayList<Post> _data;
	private static Context _c;
	private String course;
	// If _flag == 0, show course info in first item. Else, don't show.
	// Must add exactly one post in order to show course info.
	// Consequent posts will fall under course info.
	private int _flag;
	
	CustomAdapter(ArrayList<Post> data, Context c) {
		_data = data;
		_c = c;
		_flag = 1;
	}
	
	CustomAdapter(ArrayList<Post> data, Context c, int flag, String course) {
		_data = data;
		_c = c;
		_flag = flag;
		this.course = course;
	}
	
	@Override
	public int getCount() {
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if(position == 0 && _flag == 0) {
			v = vi.inflate(R.layout.course_info_box, null);//post the course info
			//TextView courseTitle = (TextView)v.findViewById(R.id.course_title);
			TextView courseNumber = (TextView)v.findViewById(R.id.course_number);
			//TextView courseDescription = (TextView)v.findViewById(R.id.course_description);
			courseNumber.setText(course);
			for(int i = 0; i < ((MainEntry)_c).getCourses().length; ++i) {
				if(((MainEntry)_c).getCourses()[i] == course) {
					v.setBackgroundColor(Color.parseColor("#ECB326"));
					v.invalidate();
					break;
				}
			}
		}
		else {
			switch(_data.get(position).getType()) {
			case 0:
				v = vi.inflate(R.layout.post_box, null);
				break;
			case 1:
				v = vi.inflate(R.layout.post_box_image, null);
				break;
			}
			// this connects each comment button with the load comments async task
			// the load comments async task in turn connects each comment (Post) button
			// with the PostComment async task
			TextView comment = (TextView) v.findViewById(R.id.comment);
			final Post p = _data.get(position);
			comment.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new LoadComments(_c, p).execute();
				}
			});
		}
		if((position > 0 && _flag == 0) || _flag != 0) {
			Post post;
			post = _data.get(position);
			ImageView image = (ImageView)v.findViewById(R.id.post_icon);
			TextView authorView = (TextView)v.findViewById(R.id.author);
			TextView targetView = (TextView)v.findViewById(R.id.target);
			View messageView;
			switch(_data.get(position).getType()) {
			case 0:
				messageView = (TextView)v.findViewById(R.id.message);
				((TextView)messageView).setText(post.getMessage());
				break;
			case 1:
				messageView = (ImageView)v.findViewById(R.id.message);
				String url = _data.get(position).getMessage();
				if(!url.contains("http://") && !url.contains("https://"))
					url = "http://" + url;
				new DownloadImageTask((ImageView) messageView)
                	.execute(url);
				break;
			}
			TextView timeView = (TextView)v.findViewById(R.id.time);
			image.setImageResource(post.getIcon());
			authorView.setText(Html.fromHtml("<b>"+post.getAuthor()+"</b>"));
			if(!post.getAuthor().equals(post.getTarget()))
				targetView.setText(Html.fromHtml("<b>"+post.getTarget()+"</b>"));
			else
				targetView.setText("");
			timeView.setText(post.getTime());
		}
		return v;
	}

}