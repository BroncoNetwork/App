package cs356.bronconetwork.fragments;

import cs356.bronconetwork.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("INFO", "In TestFrag1");
		return inflater.inflate(R.layout.fragment_test1, container, false);
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}

}
