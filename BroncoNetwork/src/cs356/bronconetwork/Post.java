package cs356.bronconetwork;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Post {
	private final int id = 0,
					  author = 1,
					  target = 2,
					  message = 3,
					  time = 4;
	private Time timestamp;
	private int icon;
	private int size;
	private int type = 0;
	private String content[];
	private ArrayList<Comment> comments;
	
	/*
	 * Post from the DB
	 */
	public Post(String id, String auth, String tar, String msg, String ti) {
		size = 0;
		icon = 0;
		type = 0;
		if((msg.contains(".jpg") ||
			msg.contains(".gif") ||
			msg.contains(".png") ||
			msg.contains(".bmp") ||
			msg.contains(".webp")))
					type = 1;
		content = new String[5];
		content[this.id] = id;
		content[author] = auth;
		content[target] = tar;
		content[message] = msg;
		content[time] = ti;
		comments = new ArrayList<Comment>();
	}
	
	/*
	 * New Post, hopefully will get pushed to DB
	 */
	public Post(String auth, String tar, String msg, String ti) {
		size = 0;
		icon = 0;
		type = 0;
		if((msg.contains(".jpg") ||
			msg.contains(".gif") ||
			msg.contains(".png") ||
			msg.contains(".bmp") ||
			msg.contains(".webp")))
					type = 1;
		content = new String[5];
		content[this.id] = "";
		content[author] = auth;
		content[target] = tar;
		content[message] = msg;
		content[time] = ti;
		comments = new ArrayList<Comment>();
	}
	
	public Post() {
		size = 0;
		icon = 0;
		type = 0;
		content = new String[5];
		content[id] = "";
		content[author] = "";
		content[target] = "";
		content[message] = "";
		content[time] = "";
		timestamp = new Time();
		comments = new ArrayList<Comment>();
	}
	
	public String getId() {
		return content[this.id];
	}
	
	public void setId(String id) {
		content[this.id] = id;
	}
	
	public String getAuthor() {
		return content[this.author];
	}
	
	public void setAuthor(String author) {
		content[this.author] = author;
	}
	
	public String getTarget() {
		return content[this.target];
	}
	
	public void setTarget(String target) {
		content[this.target] = target;
	}
	
	public String getMessage() {
		if((content[this.message].contains(".jpg") ||
			content[this.message].contains(".gif") ||
			content[this.message].contains(".png") ||
			content[this.message].contains(".bmp") ||
			content[this.message].contains(".webp")))
				type = 1;
		return content[this.message];
	}
	
	public void setMessage(String message) {
		if((message.contains(".jpg") ||
			message.contains(".gif") ||
			message.contains(".png") ||
			message.contains(".bmp") ||
			message.contains(".webp")))
				type = 1;
		content[this.message] = message;
	}
	
	public String getTime() {
		return content[this.time];
	}
	
	public Time getTimestamp() {
		return timestamp;
	}

	public void setTime(Time time) {
		timestamp = time;
		content[this.time] = (String) DateUtils.getRelativeTimeSpanString(time.toMillis(true),System.currentTimeMillis(),0);
	}
	
	public void setTime(long time) {
		timestamp.set(time);
		content[this.time] = (String) DateUtils.getRelativeTimeSpanString(time,System.currentTimeMillis(),0);
	}
	
	//add time
	public void setTime()
	{
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		content[this.time] = timeStamp;
	}

	public int getIcon() {
		return icon;
	}
	
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public int size() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		return "Author: " + content[author] +
			   " Target: " + content[target] +
			   " Message: " + content[message] +
			   " TimeStamp: " + content[time];
	}
	
	public int getType() {
		return type;
	}
	
	public void addComment(String id, String author, String target, String message, Time time) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setAuthor(author);
		comment.setTarget(target);
		comment.setMessage(message);
		comment.setTime(time);
		++size;
		comments.add(comment);
	}
}