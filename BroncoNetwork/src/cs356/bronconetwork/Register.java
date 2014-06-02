package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		String code = generatePin();
		if(pw1.equals(pw2))	
		{
			// create user
			if(isValidEmail(email))
			{
				//send validation code to email of user
				new SendValidationCode().execute(username,email,code);
			}
			else
			{
				message("The email is not a valid one.");
			}
		}
		else
		{
			message("Password does not match.");
		}
		
	}
	
	public void startValidateEmail(String code)
	{
		Intent i = new Intent(this, ValidateEmail.class);
		Bundle data = new Bundle();
		data.putString("username",usernameField.getText().toString());
		data.putString("password",pw1Field.getText().toString());
		data.putString("email",emailField.getText().toString());
		data.putString("code",code);
		i.putExtra("information", data);
		startActivity(i);
		finish();
	}
	
	
	
	
	public void message(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	public class SendValidationCode  extends AsyncTask<String,Void,String>{

		private String code;

		   protected void onPreExecute(){
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try {
		            String username = (String)arg0[0];
		            String email = (String)arg0[1];
		            code = (String)arg0[2];
		            String link = "http://bronconetwork.comuv.com/sendCode.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		            nameValuePairs.add(new BasicNameValuePair("username",username));
		            nameValuePairs.add(new BasicNameValuePair("email",email));
		            nameValuePairs.add(new BasicNameValuePair("code",code));
		            
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
			   
			   message(result);
			   if(result.substring(0,5).equals(" User"))
			   {
				   message("This username has been taken. Please choose another one.");
			   }
			   else if(result.substring(0,5).equals("Email"))
			   {
				   message("This email has been taken. Please choose another one.");
			   }
			   else
			   {
				   startValidateEmail(code);
			   }
		   }
		   
		}
			
	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}

	public String generatePin()
	{
		Random random = new Random();
		int randomNum1 = random.nextInt(10);
		int randomNum2 = random.nextInt(10);
		int randomNum3 = random.nextInt(10);
		int randomNum4 = random.nextInt(10);
		return "" + randomNum1 + randomNum2 + randomNum3 + randomNum4;
	}
}


