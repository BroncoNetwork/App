package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.R;


public class ProfileCourseAdapter extends BaseAdapter{
	
	private ArrayList<String> _data;
 	Context _c;
	
	ProfileCourseAdapter(ArrayList<String> data, Context c) {
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
		if(v == null && position == 0) {
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.profile_basic_info_box, null);
			String course = _data.get(position);

			TextView firstName = (TextView)v.findViewById(R.id.first_Name);
			//lastName = (TextView)v.findViewById(R.id.last_Name);
			TextView major = (TextView)v.findViewById(R.id.major_);
			TextView email = (TextView)v.findViewById(R.id.email_);
			
			firstName.setText(((MainEntry)_c).getUser());
			//lastName.setText("Nham");
			major.setText("Computer Science");
			email.setText(((MainEntry)_c).getEmail());
		}
		else if(v == null && position > 0) {
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.profile_course_box, null);
			
			TextView courseNumber = (TextView)v.findViewById(R.id.profile_course_number);
			courseNumber.setText(_data.get(position));
		}
		return v;
	}

	
}
