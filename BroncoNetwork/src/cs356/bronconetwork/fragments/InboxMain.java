package cs356.bronconetwork.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cs356.bronconetwork.R;

@SuppressLint("ValidFragment")
public class InboxMain extends Fragment {
	
	private ViewPager inboxPager;
	private InboxAdapter adapter;
	private FragmentManager mgr;
	
	public InboxMain(FragmentManager mgr) {
		this.mgr = mgr;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		InboxFragment inboxFrag = new InboxFragment();
//		android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//		transaction.add(R.id.inbox, inboxFrag).commit();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.fragment_inbox, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		inboxPager = (ViewPager) getView().findViewById(R.id.inboxViewPager);
		adapter = new InboxAdapter(mgr);
		inboxPager.setAdapter(adapter);
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}

}
