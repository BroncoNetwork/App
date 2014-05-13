package cs356.bronconetwork;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cs356.bronconetwork.SideBarAdapter.SideBarItem;

public class SideBarAdapter extends ArrayAdapter<SideBarItem> {
	
	private Context c;
	private static ArrayList<SideBarItem> items = new ArrayList<SideBarItem>();
	
	static {
		items.add(new SideBarItem("Newsfeed", R.drawable.icon_newsfeed));
		items.add(new SideBarItem("Profile", R.drawable.icon_profile));
		items.add(new SideBarItem("Groups", R.drawable.icon_groups));
		items.add(new SideBarItem("Courses", R.drawable.icon_courses));
		items.add(new SideBarItem("Inbox", R.drawable.icon_inbox));
		items.add(new SideBarItem("Logout", R.drawable.icon_logout));
	}
	
	public SideBarAdapter(Context c) {
		super(c, 0, items);
		this.c = c;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public SideBarItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null) {
			LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.sidebar_item, null);
		}
		ImageView image = (ImageView) v.findViewById(R.id.pageImg);
		TextView page = (TextView) v.findViewById(R.id.page);
		
		image.setImageDrawable(c.getResources().getDrawable(items.get(position).getId()));
		page.setText(items.get(position).getName());
		
		return v;
	}
	
	public static class SideBarItem {
		
		private String name;
		private int id;
		
		public SideBarItem(String name, int id) {
			this.name = name;
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public int getId() {
			return id;
		}
	}

}
