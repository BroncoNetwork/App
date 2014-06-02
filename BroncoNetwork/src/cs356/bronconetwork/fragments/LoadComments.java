package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.Post;
import cs356.bronconetwork.R;

class LoadComments extends AsyncTask<String, Integer, String> {
	
	private MainEntry mainEntry;
	private Post p;
	private ProgressDialog dialog;
	private AlertDialog.Builder builder;
	private LayoutInflater inflater;
	private View commentsLayout;
	private ListView commentsList;
	private ArrayList<Post> comments = new ArrayList<Post>();
	private Button postCommentButton;
	
	public LoadComments(final Context c, final Post p) {
		this.mainEntry = (MainEntry) c;
		this.p = p;
		dialog = new ProgressDialog(c);
		builder = new AlertDialog.Builder(c);
		builder.setTitle("Comments");
		inflater = ((Activity) c).getLayoutInflater();
		commentsLayout = inflater.inflate(R.layout.comments, null);
		commentsList = (ListView) commentsLayout.findViewById(R.id.commentsList);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setTitle("Comments");
		dialog.setMessage("Loading comments...");
		
		// initialize the original post
		TextView author = (TextView) commentsLayout.findViewById(R.id.author);
		TextView target = (TextView) commentsLayout.findViewById(R.id.target);
		TextView time = (TextView) commentsLayout.findViewById(R.id.time);
		TextView msg = (TextView) commentsLayout.findViewById(R.id.message);
		
		author.setText(Html.fromHtml("<b>"+p.getAuthor()+"</b>"));
		time.setText(p.getTime());
		msg.setText(p.getMessage());
		
		if(!p.getAuthor().equals(p.getTarget())) target.setText(Html.fromHtml("<b>"+p.getTarget()+"</b>"));
		else target.setText("");
		
		// post functionality
		postCommentButton = (Button) commentsLayout.findViewById(R.id.comment_button);
		final EditText eT = (EditText) commentsLayout.findViewById(R.id.text_bar);
		postCommentButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String comment = eT.getText().toString();
				if(comment.length() >= 5) {
					String username = mainEntry.getUsername();
					String target = p.getAuthor();
					String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
					String root = p.getId();
					new PostComment(LoadComments.this).execute(username, target, comment, timestamp, root);
					eT.setText("");
				}
				else
					Toast.makeText(c, "Failed to post comment (too short).", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void onPreExecute() {
		dialog.show();
	}
	
	public String doInBackground(String... args) {
		// call server to get the list of comments for this post
	      
        try {
           String link = "http://bronconetwork.comuv.com/getComments.php";
           
           HttpClient client = new DefaultHttpClient();
           HttpPost send = new HttpPost(link);
           
           List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
           nameValuePairs.add(new BasicNameValuePair("id", p.getId()));
           
           send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
           
           HttpResponse response = client.execute(send);
           
           BufferedReader in = new BufferedReader
          (new InputStreamReader(response.getEntity().getContent()));

          StringBuffer sb = new StringBuffer("");
          String line="";
          
          while ((line = in.readLine()) != null) {
             sb.append(line);
           }
           String ans = sb.toString().trim().substring(0, sb.toString().trim().indexOf("<!--"));
           return ans;
     } catch(Exception e) {
        return new String("Exception: " + e.getMessage());
     }
	}
	
	public void onPostExecute(String result) {
		// add the original post
		// parse result and add to list of comments
		if(result.length() > 0) {
			result = result.substring(0, result.length()-1);
       		StringTokenizer eachPost = new StringTokenizer(result, "`");
       		while(eachPost.hasMoreTokens()) {
       			String next = eachPost.nextToken();
	       		StringTokenizer eachEle = new StringTokenizer(next, "|");
	       		while(eachEle.hasMoreTokens()) {
	       			String author = eachEle.nextToken();
	       			String target = eachEle.nextToken();
	       			String msg = eachEle.nextToken();
	       			String time = eachEle.nextToken();
	       			// add comments after the original post
	       			comments.add(new Post(author, target, msg, time));
	       		}
       		}
		}
		// dismess dialog, set the adapter for the comments list and show the popup
		dialog.dismiss();
		commentsList.setAdapter(new CommentsListAdapter(comments, mainEntry));
		builder.setView(commentsLayout).create().show();
	}
	
	public ListView getCommentsListView() {
		return commentsList;
	}
	
	public ArrayList<Post> getComments() {
		return comments;
	}
	
	public void addComment(Post p) {
		comments.add(0, p);
		commentsList.invalidateViews();
	}
	
	public MainEntry getMainEntry() {
		return mainEntry;
	}
	
	public Button getPostCommentButton() {
		return postCommentButton;
	}
}
