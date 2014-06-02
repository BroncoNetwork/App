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

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ValidateEmail extends Activity{
	private String username;
	private String pass;
	private String email;
	private String code;
	private String state;
	private EditText validationCode;
	
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.validate_email);	
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		validationCode = (EditText) findViewById(R.id.validationCode);
		
		
		Bundle bundle = getIntent().getBundleExtra("information");
		state = bundle.getString("state");
		username = bundle.getString("username");
		email = bundle.getString("email");
		code = bundle.getString("code");
		if(state.equals("register")){
			pass = bundle.getString("password");
		}
	}
	
	public void submit(View v) {
		if(code.equals(validationCode.getText().toString()))
		{
			if(state.equals("register"))
			{
				new registerActivity().execute(username,pass,email);
			}
			else
			{
				startResetPass();
			}
		}
		else
		{
			message("Wrong validation code. Please try again!");
		}
	}
	
	public void startMainEntry()
	{
		Intent i = new Intent(this, MainEntry.class);
		startActivity(i);
		finish();
	}
	
	public void startResetPass()
	{
		Intent i = new Intent(this, ResetpassActivity.class);
		Bundle data = new Bundle();
		data.putString("username",username);
		data.putString("email",email);
		i.putExtra("information", data);
		message(username);
		startActivity(i);
		finish();
	}
	
	public void message(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	public class registerActivity  extends AsyncTask<String,Void,String>{


		   protected void onPreExecute(){
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try {
		            String username = (String)arg0[0];
		            String password = (String)arg0[1];
		            String email = (String)arg0[2];
		            String link = "http://bronconetwork.comuv.com/register.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		            nameValuePairs.add(new BasicNameValuePair("username",username));
		            nameValuePairs.add(new BasicNameValuePair("password",password));
		            nameValuePairs.add(new BasicNameValuePair("email",email));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            in.close();
		            return sb.toString();
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			   
				   UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
				   userInfo.setUserName(username);
				   userInfo.setEmail(email);
				   message("Register Successfully");
				   startMainEntry(); //jump to MainEntry activity
		   }
		   
		}
}
