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
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
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
		private ArrayList<Mail> msgs = new ArrayList<Mail>();
		private ArrayList<Integer> msgsToDelete = new ArrayList<Integer>(); // this list is used to delete the msgs in the inbox/sent UI
																			// the actual mail item to be deleted in the DB is taken from the ListView list
		private InboxListAdapter listAdapter;
    	
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
    	
    	// refresh list
    	public void refresh() {
    		String user = ((UserData) inboxFrag.getMainEntry().getApplicationContext()).getUserName();
    		new GetMessages().execute(user);
    	}

    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		
    		list = (ListView) getView().findViewById(R.id.inboxList);
    		
    		listAdapter = new InboxListAdapter(this, msgs);
    		list.setAdapter(listAdapter);
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
									if(sent) ((ComposeFragment) frags[COMPOSE]).getToField().setText(mail.getFrom().substring(4, mail.getFrom().length()));
									else ((ComposeFragment) frags[COMPOSE]).getToField().setText(mail.getFrom().substring(6, mail.getFrom().length()));
									((ComposeFragment) frags[COMPOSE]).getMsgField().requestFocus();
									
								}
						   })
						   .create();
					dialog.show();
				}		
    		});
    		
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
					
					// remove any checkmarks and clear the msgsToDelete list
					for(int i=0; i < msgsToDelete.size(); i++) {
						int item = msgsToDelete.get(i);
						View v = (View) listAdapter.getItem(item);
						CheckBox checkbox = (CheckBox) v.findViewById(R.id.checkbox);
						checkbox.setChecked(false);
					}
					msgsToDelete.clear();
					listAdapter.setActionModeNull();
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
					if(checked) {
						++mNumberOfSelectedItems;
					} else {
						--mNumberOfSelectedItems;
					}
					View v = (View) listAdapter.getItem(position);
					CheckBox checkbox = (CheckBox) v.findViewById(R.id.checkbox);
					checkbox.setChecked(checked);
					// add position of the item to delete
					if(checked) {
						msgsToDelete.add(position);
					// otherwise look for the index of the item to remove and remove it
					} else {
						for(int i=0; i < msgsToDelete.size(); i++) {
							int msg = msgsToDelete.get(i);
							if(msg == position) {
								msgsToDelete.remove(i);
								break;
							}
						}
					}
					mode.setTitle(String.valueOf(mNumberOfSelectedItems) + " selected");
					mode.invalidate();
				} 
    			
    		});
    		
    		// get the mail msgs
    		refresh();
    		
    		Log.i("INFO", "INSIDE, sent: " + sent);
    	}
    	
		private void deletePost() {
			SparseBooleanArray checked = list.getCheckedItemPositions();
			//gets all of the checked list objects
			//adds data to a mail object
			//call async task to delete
			for(int i = 0; i < checked.size(); i++) {
				//Mail theSelectedMsg = (Mail) list.getItemAtPosition(checked.keyAt(i));
				Mail theSelectedMsg = msgs.get(checked.keyAt(i));
				// collect the id's of the msgs and delete them from the db
				deleteMsgs(theSelectedMsg.getId());
			}
			
		}
		
		/*
		 * So it can be called from the inbox list adapter, when the checkbox is checked
		 */
		public void deleteMsgs(String id) {
			new deleteMsgs().execute(id);
		}

		/**
		 * Deletes the Message(s) from the DB
		 *
		 */
    	public class deleteMsgs extends AsyncTask<String,Void,String> {
    		
 		   protected void onPreExecute() {
 			   
 		   }
 		   
 		   //This function is used to make connection to online database
 		   @Override
 		   protected String doInBackground(String... args0) {
 		         try{
 		        	 String id = args0[0];
 		            String link = "http://bronconetwork.comuv.com/deleteMsgs.php";
 		            
 		            HttpClient client = new DefaultHttpClient();
 		            HttpPost send = new HttpPost(link);
 		            
 		            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
 		            nameValuePairs.add(new BasicNameValuePair("id", id));
 		            
 		           send.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 		            
		            HttpResponse response = client.execute(send);
		            
		            BufferedReader in = new BufferedReader
		           (new InputStreamReader(response.getEntity().getContent()));

		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		              sb.append(line);
		            }
		            in.close();
		            return sb.toString() + " Executed";
		      }catch(Exception e){
		         return new String("Exception: " + e.getMessage());
		      }
		      }
 		    
 		   
 		   @Override
 		   protected void onPostExecute(String result) {
 			   String executed = result.substring(result.lastIndexOf(" ")+1, result.length());
 			   if(executed.equals("Executed")) {
	 			   //post function
	 			   // delete msgs from UI
	 			   for(int i=0; i < msgsToDelete.size(); i++) {
	 				   msgs.remove(msgsToDelete.get(i));
	 			   }
	 			   msgsToDelete.clear();
	 			  refresh();
 			   } else {
 				   Toast.makeText(getActivity(), executed, Toast.LENGTH_SHORT).show();
 			   }
 		   }
    	}
    	
    	/**
    	 * Get messages and load up the inbox/sent
    	 *
    	 */
        class GetMessages extends AsyncTask<String, Integer, String> {
     
        	private String link = "";
        	public GetMessages() {
        		if(sent) link = "http://bronconetwork.comuv.com/getSent.php";
        		else link = "http://bronconetwork.comuv.com/getInbox.php";
        	}
        	
        	public void onPreExecute() {
        		msgs.clear();
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
        		StringTokenizer eachMsg = new StringTokenizer(result, "`");
        		while(eachMsg.hasMoreTokens()) {
        			StringTokenizer eachEle = new StringTokenizer(eachMsg.nextToken(), "|");
        			while(eachEle.hasMoreTokens()) {
        				String id = eachEle.nextToken();
        				String from = eachEle.nextToken();
        				String to = eachEle.nextToken();
        				String timestamp = eachEle.nextToken();
        				String msg = eachEle.nextToken();
        				//should add the to/from separately 
        				if(sent) msgs.add(new Mail(id, "To: " + to, from, timestamp, msg));
        				else msgs.add(new Mail(id, "From: " + from, to, timestamp, msg));
        			}
        		}        		
        		list.invalidateViews();
        	}
        }
        
        public ListView getList() {
        	return list;
        }
        
        public ArrayList<Mail> getMsgs() {
        	return msgs;
        }
        
        public void addMsgToDelete(int i) {
        	msgsToDelete.add(i);
        }
        
        public void removeMsgToDelete(int i) {
        	msgsToDelete.remove(i);
        }
        
        public void clearMsgsToDelete() {
        	msgsToDelete.clear();
        }
        
        public int getMsgToDelete(int i) {
        	return msgsToDelete.get(i);
        }
        
        public int getMsgsToDeleteSize() {
        	return msgsToDelete.size();
        }
    }		
}