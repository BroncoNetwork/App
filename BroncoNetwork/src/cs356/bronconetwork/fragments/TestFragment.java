package cs356.bronconetwork.fragments;

import cs356.bronconetwork.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment implements NetworkFragment {
	
	public TestFragment() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test1, container, false);
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	public String getName() {
		return "Test";
	}
	
	public int getDrawableId() {
		return R.drawable.ic_launcher;
	}

}
