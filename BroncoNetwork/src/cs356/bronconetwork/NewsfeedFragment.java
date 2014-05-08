package cs356.bronconetwork;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsfeedFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("INFO", "In newsfeed");
		return inflater.inflate(R.layout.newsfeed, container, false);
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}
}
