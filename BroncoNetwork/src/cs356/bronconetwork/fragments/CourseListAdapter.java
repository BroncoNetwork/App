package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cs356.bronconetwork.R;


public class CourseListAdapter extends BaseAdapter{
	
	private ArrayList<String> _data;
 	Context _c;
	
	CourseListAdapter(ArrayList<String> data, Context c) {
		_data = data;
		_c = c;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if(v == null) {
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.course_list_box, null);
			
			TextView courseNumber = (TextView)v.findViewById(R.id.course_number_list);
			
			courseNumber.setText(_data.get(position));
		}
		return v;
	}
}
