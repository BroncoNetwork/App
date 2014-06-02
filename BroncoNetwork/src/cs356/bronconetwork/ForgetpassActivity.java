package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetpassActivity extends Activity {

	private EditText emailField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpass);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		emailField = (EditText) findViewById(R.id.email);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgetpass, menu);
		return true;
	}
	
	public void validate_email(View v) {
		// check if email is valid & in database
		// send code to email
		String email = emailField.getText().toString();
		String newCode = generatePin();
		if(isValidEmail(email))
		{
			new SendValidationCode().execute(email,newCode);
		}
		else
		{
			message("The email is not a valid one.");
		}
		
	}
	
	public void startValidateEmail(String code,String username)
	{
		Intent i = new Intent(this, ValidateEmail.class);
		Bundle data = new Bundle();
		data.putString("email",emailField.getText().toString());
		data.putString("username", username);
		data.putString("code",code);
		data.putString("state","reset");
		i.putExtra("information", data);
		startActivity(i);
		finish();
	}
	
	public class SendValidationCode  extends AsyncTask<String,Void,String>{

		private String code;
		private String username;
		private String data; //this string content the information of result from server

		   protected void onPreExecute(){
			   
		   }
		   
		   //This function is used to make connection to online database
		   @Override
		   protected String doInBackground(String... arg0) {
		      
		         try {
		            String email = (String)arg0[0];
		            code = (String)arg0[1];
		            String link = "http://bronconetwork.comuv.com/sendResetCode.php";
		            
		            HttpClient client = new DefaultHttpClient();
		            HttpPost send = new HttpPost(link);
		            
		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		            nameValuePairs.add(new BasicNameValuePair("email",email));
		            nameValuePairs.add(new BasicNameValuePair("code",code));
		            
		            send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           String line="";

		            line = in.readLine();
		            in.close();		            
		            return line;
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
		    
		   
		   @Override
		   protected void onPostExecute(String result){
			   
			   if(result.substring(0,5).equals("Email"))
			   {
				   message("This email is not in the system.");
			   }
			   else
			   {
				   username = result;
				   startValidateEmail(code,username);
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
	
	public void message(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
