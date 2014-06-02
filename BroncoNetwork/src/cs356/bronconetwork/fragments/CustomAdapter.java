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
	TextView courseTitle;
	TextView courseDescription;
	
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
			courseTitle = (TextView)v.findViewById(R.id.course_title);
			TextView courseNumber = (TextView)v.findViewById(R.id.course_number);
			courseDescription = (TextView)v.findViewById(R.id.course_description);
			courseNumber.setText(course);
			switch(course) {
			case "CS 101":
				courseTitle.setText("Introduction to Computers for Non-CS Majors");
				courseDescription.setText("Basic concepts of computer hardware and software.");
				break;
			case "CS 128":
				courseTitle.setText("Introduction to C++");
				courseDescription.setText("Basic concepts of computer software and programming.");
				break;
			case "CS 130":
				courseTitle.setText("Discrete Structures");
				courseDescription.setText("Fundamental topics for Computer Science, such as logic, proof techniques, sets, basic counting rules, relations, functions and recursion, graphs and trees.");
				break;
			case "CS 140":
				courseTitle.setText("Introduction to Computer Science");
				courseDescription.setText("Basic concepts of Computer Science, including hardware and software. Problem-solving methods. Programming in an object-oriented language.");
				break;
			case "CS 141":
				courseTitle.setText("Introduction to Programming and Problem-Solving");
				courseDescription.setText("Design, implementation, documentation and testing of programs in an object-oriented language.");
				break;
			case "CS 200":
				break;
			case "CS 210":
				courseTitle.setText("Computer Logic");
				courseDescription.setText("Boolean algebra with applications to computers and logic design.");
				break;
			case "CS 240":
				courseTitle.setText("Data Structures and Algorithms I");
				courseDescription.setText("Abstract data types and their implementations.");
				break;
			case "CS 241":
				courseTitle.setText("Data Structures and Algorithms II");
				courseDescription.setText("Trees, priority queues, graphs, sets, and maps.");
				break;
			case "CS 245":
				courseTitle.setText("Programming Graphical User Interfaces");
				courseDescription.setText("Computer interfaces. Usability of interactive systems. GUI development processes.");
				break;
			case "CS 256":
				courseTitle.setText("C++ Programming");
				courseDescription.setText("Class encapsulation, inheritance, polymorphism, object storage management, and exception handling.");
				break;
			case "CS 301":
				courseTitle.setText("Numerical Methods");
				courseDescription.setText("Error analysis, zeros of a function, systems of linear equations, interpolation.");
				break;
			case "CS 356":
				courseTitle.setText("Object-Oriented Design and Programming");
				courseDescription.setText("Elements of the object model. Abstraction, encapsulation, modularity and hierarchy.");
				break;
			case "CS 408":
				courseTitle.setText("Programming Languages");
				courseDescription.setText("Concepts in programming languages. Virtual machines and abstraction.");
				break;
			case "CS 450":
				courseTitle.setText("Computability");
				courseDescription.setText("Abstract models of computation, including Turing machines. Church- Turing thesis.");
				break;
			}
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