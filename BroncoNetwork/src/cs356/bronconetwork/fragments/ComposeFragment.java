package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import cs356.bronconetwork.R;
import cs356.bronconetwork.UserData;
import cs356.bronconetwork.fragments.InboxAdapter.InboxList;

@SuppressLint("ValidFragment")
public class ComposeFragment extends Fragment {
	
	private boolean userExists = false;
	private EditText toField, msgField;
	private InboxAdapter inboxAdapter;
	
	public ComposeFragment(InboxAdapter inboxAdapter) {
		this.inboxAdapter = inboxAdapter;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.compose, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		toField = (EditText) getView().findViewById(R.id.toField);
		msgField = (EditText) getView().findViewById(R.id.msgField);
		
		Button send = (Button) getView().findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String from = ((UserData) getActivity().getApplicationContext()).getUserName();
				String to = toField.getText().toString();
				String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
				String msg = msgField.getText().toString();
				// see if use exists
				new UserExists().execute(from, to, time, msg);
			}
		});
	}
	
	class UserExists extends AsyncTask<String, Void, Boolean> {
		
		private String from, to, time, msg;

		@Override
		public Boolean doInBackground(String... params) {
			try {
				from = params[0];
				to = params[1];
				time = params[2];
				msg = params[3];
		        String link = "http://bronconetwork.comuv.com/userExists.php";
		        
		        HttpClient client = new DefaultHttpClient();
		        HttpPost send = new HttpPost(link);
		        
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		        nameValuePairs.add(new BasicNameValuePair("to", to));
		        
		        send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		        HttpResponse response = client.execute(send);
		            
		        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
		        String line="";

	            while ((line = in.readLine()) != null) {
	            	sb.append(line);
		        }
	            	
	            String ans = sb.toString().trim();
	            return ans.contains("Exists");
			} catch(Exception e) {
				Log.e("ERROR", e.getMessage());
				return false;
		    }
		}
		
		@Override
		public void onPostExecute(Boolean b) {
			if(b) new SendMsg().execute(from, to, time, msg);
			else Toast.makeText(getActivity(), "An error has occurred...", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	class SendMsg extends AsyncTask<String, Void, String> {
		
		private ProgressDialog dialog;
		
		@Override
		public void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Compose");
			dialog.setMessage("Sending message...");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.show();
		}

		@Override
		public String doInBackground(String... params) {
			try {
				String from = params[0];
				String to = params[1];
				String timestamp = params[2];
				String msg = params[3];
		        String link = "http://bronconetwork.comuv.com/sendMsg.php";
		        
		        HttpClient client = new DefaultHttpClient();
		        HttpPost send = new HttpPost(link);
		        
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
		        nameValuePairs.add(new BasicNameValuePair("author", from));
		        nameValuePairs.add(new BasicNameValuePair("target", to));
		        nameValuePairs.add(new BasicNameValuePair("timestamp", timestamp));
		        nameValuePairs.add(new BasicNameValuePair("message", msg));
		        
		        send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		        HttpResponse response = client.execute(send);
		            
		        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
		        String line="";

	            while ((line = in.readLine()) != null) {
	            	sb.append(line);
		        }
	            	
	            return sb.toString().trim();
			} catch(Exception e){
				Log.e("ERROR", e.getMessage());
				return "?";
		    }
		}
		
		@Override
		public void onPostExecute(String result) {
			dialog.dismiss();
			Toast.makeText(getActivity(), "Message Sent!", Toast.LENGTH_SHORT).show();
			((InboxList) inboxAdapter.getItem(InboxAdapter.SENT)).refresh();
			TabHost tabHost = inboxAdapter.getInboxFrag().getTabHost();
			tabHost.setCurrentTab(InboxAdapter.SENT);
			inboxAdapter.getInboxFrag().getInboxPager().setCurrentItem(tabHost.getCurrentTab());
		}
		
	}
	
	public EditText getToField() {
		return toField;
	}
	
	public EditText getMsgField() {
		return msgField;
	}

}
