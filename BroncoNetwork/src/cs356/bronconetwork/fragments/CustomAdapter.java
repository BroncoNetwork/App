package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;
import cs356.bronconetwork.R.id;
import cs356.bronconetwork.R.layout;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {
	private ArrayList<Post> _data;
	private Context _c;
	// If _flag == 0, show course info in first item. Else, don't show.
	// Must add exactly one post in order to show course info.
	// Consequent posts will fall under course info.
	private int _flag;
	
	CustomAdapter(ArrayList<Post> data, Context c) {
		_data = data;
		_c = c;
		_flag = 1;
	}
	
	CustomAdapter(ArrayList<Post> data, Context c, int flag) {
		_data = data;
		_c = c;
		_flag = flag;
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
			v = vi.inflate(R.layout.course_info_box, null);
		}
		else {
			v = vi.inflate(R.layout.post_box, null);
		}
		if((position > 0 && _flag == 0) || _flag != 0) {
			ImageView image = (ImageView)v.findViewById(R.id.post_icon);
			TextView authorView = (TextView)v.findViewById(R.id.author);
			TextView targetView = (TextView)v.findViewById(R.id.target);
			TextView messageView = (TextView)v.findViewById(R.id.message);
			TextView timeView = (TextView)v.findViewById(R.id.time);
			Post post;
			if(_flag == 0)
				post = _data.get(position-1);
			else
				post = _data.get(position);
			image.setImageResource(post.getIcon());
			authorView.setText(Html.fromHtml("<b>"+post.getAuthor()+"</b>"));
			if(post.getAuthor() != post.getTarget())
				targetView.setText(Html.fromHtml("<b>"+post.getTarget()+"</b>"));
			else
				targetView.setText("");
			messageView.setText(post.getMessage());
			timeView.setText(post.getTime());
		}
		return v;
	}
	
}