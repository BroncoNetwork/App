package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cs356.bronconetwork.Mail;
import cs356.bronconetwork.R;


public class InboxListAdapter extends BaseAdapter {
	
	private ArrayList<Mail> items;
	private Context c;
	
	public InboxListAdapter(ArrayList<Mail> items, Context c) {
		this.items = items;
		this.c = c;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null) {
			LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.inbox_list_item, null);
		}
		TextView title = (TextView) v.findViewById(R.id.title);
		TextView msg = (TextView) v.findViewById(R.id.preview);
		
		Mail mail = items.get(position);
		title.setText(Html.fromHtml("<b>"+mail.getTitle()+"</b>"));
		String preview = mail.getMsg();
		if(preview.length() > 50) preview = preview.substring(0, 25)+ "...";
		msg.setText(preview);
		
		return v;
	}
	
}
