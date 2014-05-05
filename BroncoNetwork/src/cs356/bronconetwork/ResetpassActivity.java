package cs356.bronconetwork;

import cs356.bronconetwork.MainActivity.PlaceholderFragment;
import android.os.Bundle;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;

public class ResetpassActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resetpass);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgetpass, menu); // change to resetpass
		return true;
	}
}
