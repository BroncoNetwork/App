package cs356.bronconetwork;

import java.util.ArrayList;

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
	Time timestamp;
	int icon;
	int size;
	String content[];
	ArrayList<Comment> comments;

	public Post() {
		size = 0;
		icon = 0;
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
		return content[this.message];
	}
	
	public void setMessage(String message) {
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