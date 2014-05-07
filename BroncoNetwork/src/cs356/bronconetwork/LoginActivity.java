package cs356.bronconetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginActivity  extends AsyncTask<String,Void,String>{

	   
	   private Context context;
	   public LoginActivity(Context context) {
	      this.context = context;
	      
	   }

	   protected void onPreExecute(){

	   }
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
		   if(result.length() > 1)
			   Toast.makeText(context, "Cannot Login", Toast.LENGTH_LONG).show();
		   else
			   Toast.makeText(context, "Login Successfully ", Toast.LENGTH_LONG).show();
	   }
	   
	}

