package cs356.bronconetwork;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class Profile extends Fragment{
	
	private TextView  firstName, lastName, major, email;
	
	private ImageView profilePic;
	
	/*
	public void getData(){
		
	}
	
	*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("INFO", "In profile");
		return inflater.inflate(R.layout.profile, container, false);
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}

}
