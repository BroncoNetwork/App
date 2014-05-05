package cs356.bronconetwork;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ForgetpassActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpass);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
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
		Intent i = new Intent(this, ResetpassActivity.class);
		startActivity(i);
	}
	
	public void enter_code(View v) {
		Intent i = new Intent(this, ResetpassActivity.class);
		startActivity(i);
	}

}
