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
import android.widget.Toast;
import cs356.bronconetwork.R;
import cs356.bronconetwork.UserData;

public class ComposeFragment extends Fragment {
	
	private boolean userExists = false;
	
	public ComposeFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.compose, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button send = (Button) getView().findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String to = ((EditText) getView().findViewById(R.id.to)).getText().toString();
				String msg = ((EditText) getView().findViewById(R.id.msg)).getText().toString();
				// see if use exists
				new UserExists().execute(to);
				if(userExists) {
					String from = ((UserData) getActivity().getApplicationContext()).getUserName();
					String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
					new SendMsg().execute(from, to, time, msg);
				}
			}
		});
	}
	
	class UserExists extends AsyncTask<String, Void, Void> {

		@Override
		public Void doInBackground(String... params) {
			try {
				String to = params[0];
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
	            if(ans.contains("Exists")) userExists = true;
	            else Log.e("ERROR", ans);
	            return null;
			} catch(Exception e) {
				Log.e("ERROR", e.getMessage());
				return null;
		    }
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
		}
		
	}

}
