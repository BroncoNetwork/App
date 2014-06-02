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
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
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
		private int mNumberOfSelectedItems = 0;
		private Mail mailObject;
		private String author;
		private String target;
		private String timestamp;
    	
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
    		
    		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    		list.setMultiChoiceModeListener(new MultiChoiceModeListener() {

				@Override
				public boolean onActionItemClicked(ActionMode mode,
						MenuItem item) {
					switch(item.getItemId()) {
					case R.id.item_delete:
						deletePost();
						mode.finish();
						return true;
					default:
						return false;
					}
					
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					MenuInflater inflater = mode.getMenuInflater();
					inflater.inflate(R.menu.contextual_menu, menu);
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode arg0) {
					mNumberOfSelectedItems = 0;
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode,
						Menu menu) {
					return false;
				}

				@Override
				public void onItemCheckedStateChanged(ActionMode mode,
						int position, long id, boolean checked) {
					if(checked) {
						++mNumberOfSelectedItems;
					} else {
						--mNumberOfSelectedItems;
					}
					mode.invalidate();
					mode.setTitle(String.valueOf(mNumberOfSelectedItems) + " selected");
				} 
    			
    		});
    		
    		Log.i("INFO", "INSIDE, sent: " + sent);
    	}
    	
		private void deletePost() {
			SparseBooleanArray checked = list.getCheckedItemPositions();
			//gets all of the checked list objects
			//adds data to a mail object
			//call async task to delete
			for(int i = 0; i < checked.size(); i++) {
				Mail theSelectedMsg = (Mail) list.getItemAtPosition(checked.keyAt(i));
				mailObject = theSelectedMsg;
				//sets fields, testing to make sure they worked, getting author gives From: so had to replace
				author = mailObject.getFrom().replace("From: ", "");
				target = mailObject.getTo();
				timestamp = mailObject.getTimeStamp();
				new deleteMsgs().execute();
				Toast.makeText(inboxFrag.getMainEntry(), author + " " + target + " " + timestamp, Toast.LENGTH_SHORT).show();

			}
			
		}
		
    	public class deleteMsgs extends AsyncTask<String,Void,String>{
    		
 		   protected void onPreExecute(){
 			   
 		   }
 		   
 		   //This function is used to make connection to online database
 		   @Override
 		   protected String doInBackground(String... args0) {
 		         try{
 		            String link = "http://bronconetwork.comuv.com/deleteMsgs.php";
 		            
 		            HttpClient client = new DefaultHttpClient();
 		            HttpPost send = new HttpPost(link);
 		            
 		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
 		            nameValuePairs.add(new BasicNameValuePair("target",target));
 		            nameValuePairs.add(new BasicNameValuePair("author",author));
 		            nameValuePairs.add(new BasicNameValuePair("timestamp",timestamp));

 		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            in.close();
		            return sb.toString() + "Executed";
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
 		    
 		   
 		   @Override
 		   protected void onPostExecute(String result){
 			   //post function
 		   }
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
        				//should add the to/from separately 
        				if(sent) msgs.add(new Mail("To: " + to, from, timestamp, msg));
        				else msgs.add(new Mail("From: " + from, to, timestamp, msg));
        			}
        		}
        		
        		// setup the listview
        		list.setAdapter(new InboxListAdapter(msgs, getActivity()));
        		/*list.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(inboxFrag.getMainEntry(), "hello", Toast.LENGTH_SHORT).show();
						return true;
					}
        			
        		});
        		*/

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