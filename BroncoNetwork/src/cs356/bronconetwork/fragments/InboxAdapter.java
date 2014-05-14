package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import cs356.bronconetwork.Mail;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class InboxAdapter extends FragmentStatePagerAdapter {
	
	public static final int INBOX = 0;
	public static final int SENT = 1;
	
	private MainEntry mainEntry;
	private Fragment[] frags = {
		new InboxList(INBOX), new InboxList(SENT)
	};
	
    public InboxAdapter(MainEntry mainEntry) {
        super(mainEntry.getSupportFragmentManager());
        this.mainEntry = mainEntry;
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
	public class InboxList extends Fragment {
    	
    	private ListView list;
    	private ArrayList<Mail> items = new ArrayList<Mail>();
    	private boolean sent;
    	
    	public InboxList(int which) {
    		if(which == INBOX) sent = false;
    		else sent = true;
    	}
    	
    	public InboxList(ArrayList<Mail> items) {
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
    		super.onActivityCreated(savedInstanceState);
    		
    		list = (ListView) getView().findViewById(R.id.inboxList);
    		
    		// in the future will fill this with user specific mail items retrieved from the database
    		final ArrayList<Mail> items = new ArrayList<Mail>();
    		if(sent) {
    			items.add(new Mail("From Thuan", "Hey guys I added mroe stuff to github so get on it!"));
    			items.add(new Mail("From Joe", "Hey guys I can't make it to the meeting tomorrow some things came up"));
    		} else {
    			items.add(new Mail("Hi THere!", "This is a test msg. ii hope this works woopdie do yay uh huh"));
    			items.add(new Mail("Welcome to Bronco Network", "If this is your first time doing something like this don't worry...."));
    		}
    		
    		list.setAdapter(new InboxListAdapter(items, getActivity()));
    		list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// open up a new window to display the full message
					AlertDialog.Builder builder = new AlertDialog.Builder(mainEntry);
					Mail mail = items.get(position);
					final AlertDialog dialog = builder.setTitle(mail.getTitle())
						   .setMessage(mail.getMsg())
						   .setNegativeButton("Cancel", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}					
						   })
						   .setPositiveButton("Ok", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}					
						   })
						   .create();
					dialog.show();
				}    			
    		});
    	}	

    }
}