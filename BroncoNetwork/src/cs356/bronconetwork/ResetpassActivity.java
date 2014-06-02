package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cs356.bronconetwork.MainActivity.PlaceholderFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class ResetpassActivity extends Activity {

	private EditText pw1,pw2;
	private String userName;
	private String email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resetpass);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		pw1 = (EditText)findViewById(R.id.npw1);
		pw2 = (EditText)findViewById(R.id.npw2);
		Bundle b = getIntent().getBundleExtra("information");
		userName = b.getString("username");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgetpass, menu); // change to resetpass
		return true;
	}
	
	public void onBackPressed() {
		onResume();
	}
	
	public void submit(View view)
	{
		if(pw1.getText().toString().equals(pw2.getText().toString()))
		{
			new ResetActivity().execute(userName,pw1.getText().toString());
		}
	}
	
	public class ResetActivity extends AsyncTask<String,Void,String> {

		private ProgressDialog dialog;


		protected void onPreExecute() {
			dialog = new ProgressDialog(ResetpassActivity.this);
			dialog.setTitle("Authenticating");
			dialog.setMessage("Please wait...");
			dialog.show();
		}

		//This function is used to make connection to online database
		@Override
		protected String doInBackground(String... arg0) {

			try{
				String username = (String)arg0[0];
				String password = (String)arg0[1];
				String link = "http://bronconetwork.comuv.com/resetPass.php";

				HttpClient client = new DefaultHttpClient();
				HttpPost send = new HttpPost(link);

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("username",username));
				nameValuePairs.add(new BasicNameValuePair("password",password));

				send.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = client.execute(send);

				BufferedReader in = new BufferedReader
						(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer sb = new StringBuffer("");
				String line="";

				UserData userInfo = (UserData)getApplicationContext();
				String[] tempData = new String[10];  
				int i = 0;
				while ((line = in.readLine()) != null) {
					sb.append(line);
					
					if(i < 10)
					{
						tempData[i++] = line.toString();
					}

				}

				in.close();

				//create data for new user
				userInfo.setUserName(tempData[1]);
				userInfo.setEmail(tempData[2]);
				for(int j = 3;j < 10;j++ )
				{
					if(!tempData[j].equals(""))
					{
						userInfo.setCourse(tempData[j], j - 3);
					}
				}

				return sb.toString();
			} catch(Exception e){
				return new String("Exception: " + e.getMessage());
			}
		}


		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			startMainEntry();
		}

	}

	public void startMainEntry()
	{
		Intent i = new Intent(this, MainEntry.class);
		startActivity(i);
		finish();
	}

	public void message(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

}
