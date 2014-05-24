package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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

public class Register extends Activity {
	
	private EditText usernameField, pw1Field, pw2Field, emailField;
	
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.register);	
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		usernameField = (EditText) findViewById(R.id.username);
		pw1Field = (EditText) findViewById(R.id.pw1);
		pw2Field = (EditText) findViewById(R.id.pw2);
		emailField = (EditText) findViewById(R.id.email);
	}
	
	public void submit(View v) {
		String username = usernameField.getText().toString();
		String pw1 = pw1Field.getText().toString();
		String pw2 = pw2Field.getText().toString();
		String email = emailField.getText().toString();
		if(pw1.equals(pw2))	
		{
			// create user
			new registerActivity().execute(username,pw1,email);
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
			   if(result.charAt(0) == 'D')
			   {
				   message("The account with same username or email already exists on the system!");
			   }
			   else
			   {
				   UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
				   userInfo.setUserName(usernameField.getText().toString());
				   userInfo.setEmail(emailField.getText().toString());
				   message("Register Successfully");
				   startMainEntry(); //jump to MainEntry activity
			   }
			   
		   }
		   
		}

}


