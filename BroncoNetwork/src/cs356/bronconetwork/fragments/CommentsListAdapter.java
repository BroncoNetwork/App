package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cs356.bronconetwork.DownloadImageTask;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;

public class CommentsListAdapter extends BaseAdapter {
	
	private ArrayList<Post> comments;
	private MainEntry mainEntry;
	
	public CommentsListAdapter(ArrayList<Post> comments, MainEntry mainEntry) {
		this.comments = comments;
		this.mainEntry = mainEntry;
	}

	@Override
	public int getCount() {
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		return comments.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		LayoutInflater vi = (LayoutInflater) mainEntry.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		v = vi.inflate(R.layout.comment_box, null);
		// if this is the original post
		TextView author = (TextView) v.findViewById(R.id.author);
		TextView target = (TextView) v.findViewById(R.id.target);
		TextView time = (TextView) v.findViewById(R.id.time);
		TextView msg = (TextView) v.findViewById(R.id.message);
		Post p = comments.get(position);
		
		author.setText(Html.fromHtml("<b>"+p.getAuthor()+"</b>"));
		time.setText(p.getTime());
		msg.setText(p.getMessage());
		
		if(!p.getAuthor().equals(p.getTarget())) target.setText(Html.fromHtml("<b>"+p.getTarget()+"</b>"));
		else target.setText("");
		
		return v;
	}

}
