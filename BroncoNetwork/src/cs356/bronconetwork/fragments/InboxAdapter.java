package cs356.bronconetwork.fragments;

import java.util.ArrayList;
import cs356.bronconetwork.Mail;
import cs356.bronconetwork.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class InboxAdapter extends FragmentStatePagerAdapter {
	
	private Fragment[] frags = {
		new InboxFragment(), new InboxFragment()
	};
	
    public InboxAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return frags[i];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Fragment: " + position;
    }
    
    /**
     * Inbox segment
     */
    @SuppressLint("ValidFragment")
	public static class InboxFragment extends Fragment {
    	
    	private ListView list;
    	private ArrayList<Mail> items = new ArrayList<Mail>();
    	
    	public InboxFragment() {
    		
    	}
    	
    	public InboxFragment(ArrayList<Mail> items) {
    		this.items = items;
    	}
    	
    	@Override
    	public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		return inflater.inflate(R.layout.inbox_sent, container, false);
    	}

    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		
    		list = (ListView) getView().findViewById(R.id.inbox_sent);
    		
    		ArrayList<Mail> items = new ArrayList<Mail>();
    		items.add(new Mail("Hi THere!", "This is a test msg. ii hope this works woopdie do yay uh huh"));
    		items.add(new Mail("Welcome to Bronco Network", "If this is your first time doing something like this don't worry...."));
    		list.setAdapter(new InboxListAdapter(items, getActivity()));
    		
    		super.onActivityCreated(savedInstanceState);
    	}	

    }
}