package cs356.bronconetwork.fragments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import cs356.bronconetwork.Mail;
import cs356.bronconetwork.R;
import cs356.bronconetwork.UserData;


public class InboxAdapter extends FragmentStatePagerAdapter {
	
	public static final int INBOX = 0;
	public static final int SENT = 1;
	public static final int COMPOSE = 2;
	
	private InboxFragment inboxFrag;
	private Fragment[] frags = {
		new InboxList(INBOX), new InboxList(SENT), new ComposeFragment(this)
	};
	
    public InboxAdapter(InboxFragment inboxFrag) {
        super(inboxFrag.getMainEntry().getSupportFragmentManager());
        this.inboxFrag = inboxFrag;
    }
    
    public void refresh() {
    	((InboxList) frags[INBOX]).refresh();
    	((InboxList) frags[SENT]).refresh();
    }
    
    public InboxFragment getInboxFrag() {
    	return inboxFrag;
    }

    @Override
    public Fragment getItem(int i) {
        return frags[i];
    }

    @Override
    public int getCount() {
        return frags.length;
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
    	private boolean sent;
    	
    	public InboxList(int which) {
    		if(which == INBOX) sent = false;
    		else sent = true;
    	}
    	
    	public InboxList() {

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
    		
    		// get the mail msgs
    		String user = ((UserData) inboxFrag.getMainEntry().getApplicationContext()).getUserName();
    		new GetMessages().execute(user);
    		
    		Log.i("INFO", "INSIDE, sent: " + sent);
    	}
    	
    	// refresh list
    	public void refresh() {
    		String user = ((UserData) inboxFrag.getMainEntry().getApplicationContext()).getUserName();
    		new GetMessages().execute(user);
    	}
    	
        class GetMessages extends AsyncTask<String, Integer, String> {
     
        	private String link = "";
        	public GetMessages() {
        		if(sent) link = "http://bronconetwork.comuv.com/getSent.php";
        		else link = "http://bronconetwork.comuv.com/getInbox.php";
        	}
        	
        	public String doInBackground(String... args) {
    			try {		        
    		        HttpClient client = new DefaultHttpClient();
    		        HttpPost send = new HttpPost(link);
    		        
    		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    		        nameValuePairs.add(new BasicNameValuePair("username", args[0]));
    		        
    		        send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    		            
    		        HttpResponse response = client.execute(send);
    		            
    		        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    	            StringBuffer sb = new StringBuffer("");
    		        String line="";

    	            while ((line = in.readLine()) != null) {
    	            	sb.append(line);
    		        }
    	            
    	            // got the response
    	           String ans = sb.toString().trim().substring(0, sb.toString().trim().indexOf("<!--"));
    	            return ans;
    			} catch(Exception e) {
    				Log.e("ERROR", e.getMessage());
    				return "";
    		    }
        	}
        	
        	public void onPostExecute(String result) {
        		// parse each message
        		final ArrayList<Mail> msgs = new ArrayList<Mail>();
        		StringTokenizer eachMsg = new StringTokenizer(result, "`");
        		while(eachMsg.hasMoreTokens()) {
        			StringTokenizer eachEle = new StringTokenizer(eachMsg.nextToken(), "|");
        			while(eachEle.hasMoreTokens()) {
        				String from = eachEle.nextToken();
        				String to = eachEle.nextToken();
        				String timestamp = eachEle.nextToken();
        				String msg = eachEle.nextToken();
        				if(sent) msgs.add(new Mail("To: " + to, from, timestamp, msg));
        				else msgs.add(new Mail("From: " + from, to, timestamp, msg));
        			}
        		}
        		
        		// setup the listview
        		list.setAdapter(new InboxListAdapter(msgs, getActivity()));
        		list.setOnItemClickListener(new OnItemClickListener() {
    				@Override
    				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    					// open up a new window to display the full message
    					AlertDialog.Builder builder = new AlertDialog.Builder(inboxFrag.getMainEntry());
    					final Mail mail = msgs.get(position);
    					final AlertDialog dialog = builder.setTitle(mail.getFrom())
    						   .setMessage(mail.getMsg())
    						   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    								@Override
    								public void onClick(DialogInterface dialog, int which) {
    									dialog.dismiss();
    								}
    						   })
    						   .setNegativeButton("Reply", new DialogInterface.OnClickListener() {
    								@Override
    								public void onClick(DialogInterface dialog, int which) {
    									dialog.dismiss();
    									TabHost tabHost = inboxFrag.getTabHost();
    									tabHost.setCurrentTab(COMPOSE);
    									inboxFrag.getInboxPager().setCurrentItem(tabHost.getCurrentTab());
    									((ComposeFragment) frags[COMPOSE]).getToField().setText(mail.getFrom());
    									((ComposeFragment) frags[COMPOSE]).getMsgField().requestFocus();
    								}			
    						   })
    						   .create();
    					dialog.show();
    				}		
        		});
        	}
        }

    }

}