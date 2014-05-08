package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private static EditText usernameField, pwField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
	
	
	//This function will call the loginActivity when user types in username and password
	public void login(View v) {
		String username = usernameField.getText().toString();
		String password = pwField.getText().toString();
		
		username = "user";
		password = "pass";
		
		if(username.equals("") || password.equals(""))
		{
			message("Please enter your username and password");
		}
		else
		{
			new loginActivity().execute(username,password);
		}
		
	}
	
	
	//This function will call the MainEntry activity
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
	
	public void register(View v) {
		Intent i = new Intent(this, Register.class);
		startActivity(i);
	}
	
	public void forgetPass(View view) {
    	Intent a = new Intent(this, ForgetpassActivity.class);
		startActivity(a);    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			usernameField = (EditText) getView().findViewById(R.id.username);
			pwField = (EditText) getView().findViewById(R.id.pw);

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			return rootView;
		}
	}
	
	public class loginActivity  extends AsyncTask<String,Void,String>{


		   protected void onPreExecute(){

		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try{
		            String username = (String)arg0[0];
		            String password = (String)arg0[1];
		            String link = "http://bronconetwork.comuv.com/test.php?username="
				            +username+"&password="+password;
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpGet request = new HttpGet();
		            request.setURI(new URI(link));
		            HttpResponse response = client.execute(request);
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		              break;
		            }
		            in.close();
		            
		            return sb.toString();
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			   result = result.trim();
			   if(result.length() < 1)
			   {
				   message("Cannot Login");
			   }
			   else 
			   {
				   message("Login Successfully");
				   startMainEntry(); //jump to MainEntry activity if username and password are correct.
			   }
		   }
		   
		}

}
