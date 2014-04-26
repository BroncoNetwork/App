package cs356.bronconetwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Register extends Activity {
	
	private String username, pw1, pw2, email;
	
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.register);	
	}
	
	public void submit(View v) {
		username = findViewById(R.id.username).toString();
		pw1 = findViewById(R.id.pw1).toString();
		pw2 = findViewById(R.id.pw2).toString();
		email = findViewById(R.id.email).toString();
		if(pw1.equals(pw2))	{
			// create user
		}
	}

}
