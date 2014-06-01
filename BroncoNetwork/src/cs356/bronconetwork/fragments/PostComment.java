package cs356.bronconetwork.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import cs356.bronconetwork.Post;

public class PostComment extends AsyncTask<String, Integer, String> {
	
		private LoadComments load;
		private Post p;
		
		public PostComment(LoadComments load) {
			this.load = load;
		}
	
	   //This function is used to make connection to online database
	   @Override
	   protected String doInBackground(String... arg0) {
	      
	         try {
	            String author = (String) arg0[0];
	            String target = (String) arg0[1];
	            String msg = (String) arg0[2];
	            String timestamp = (String) arg0[3];
	            String root = arg0[4];
	            p = new Post(author, target, msg, timestamp); 
	            String link = "http://bronconetwork.comuv.com/comment.php";
	            
	            HttpClient client = new DefaultHttpClient();
	            HttpPost send = new HttpPost(link);
	            
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	            nameValuePairs.add(new BasicNameValuePair("author", author));
	            nameValuePairs.add(new BasicNameValuePair("target", target));
	            nameValuePairs.add(new BasicNameValuePair("msg", msg));
	            nameValuePairs.add(new BasicNameValuePair("timestamp", timestamp));
	            nameValuePairs.add(new BasicNameValuePair("root", root));
	            
	            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            
	            client.execute(send);
	            return "Successful";
	      } catch(Exception e) {
	         return new String("Exception: " + e.getMessage());
	      }
	   }
	   
	   public void onPostExecute(String result) {
		   // on successfully pushing the comment to the DB, hide the keyboard,
		   // and add the comment to the list in the UI
		   if(result.equals("Successful")) {
				InputMethodManager imm = (InputMethodManager) load.getMainEntry().getSystemService(
						Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(load.getPostCommentButton().getWindowToken(), 0);
			   load.addComment(p);
		   } else {
			   Toast.makeText(load.getMainEntry(), "An error occurred", Toast.LENGTH_SHORT).show();
		   }
	   }

}
