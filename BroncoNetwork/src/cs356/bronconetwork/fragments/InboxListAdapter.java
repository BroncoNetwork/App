package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cs356.bronconetwork.Mail;
import cs356.bronconetwork.R;
import cs356.bronconetwork.fragments.InboxAdapter.InboxList;
import cs356.bronconetwork.fragments.InboxAdapter.InboxList.deleteMsgs;


public class InboxListAdapter extends BaseAdapter {
	
	private ArrayList<View> listItems = new ArrayList<View>();
	private ArrayList<Mail> mailItems = new ArrayList<Mail>();
	private ActionMode actionMode;
	private int mNumberOfSelectedItems = 0;
	private InboxList inboxList;
	
	public InboxListAdapter(InboxList inboxList, ArrayList<Mail> mailItems) {
		this.inboxList = inboxList;
		this.mailItems = mailItems;
	}

	@Override
	public int getCount() {
		return mailItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null) {
			LayoutInflater vi = (LayoutInflater) inboxList.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.inbox_list_item, null);
		}
		TextView title = (TextView) v.findViewById(R.id.title);
		TextView msg = (TextView) v.findViewById(R.id.preview);
//		CheckBox checkbox = (CheckBox) v.findViewById(R.id.checkbox);
//		checkbox.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(actionMode == null) actionMode = inboxList.getActivity().startActionMode(mActionModeCallback);
//				v.setSelected(!v.isSelected());
//				if(v.isSelected()) {
//					++mNumberOfSelectedItems;
//					inboxList.addMsgToDelete(position);
//				} else {
//					--mNumberOfSelectedItems;
//					for(int i=0; i < inboxList.getMsgsToDeleteSize(); i++) {
//						int msg = inboxList.getMsgToDelete(i);
//						if(msg == position) {
//							inboxList.removeMsgToDelete(i);
//							break;
//						}
//					}
//				}
//				actionMode.setTitle(String.valueOf(mNumberOfSelectedItems) + " selected");
//				actionMode.invalidate();
//				// destroy action mode
//				if(mNumberOfSelectedItems == 0) {
//					actionMode.finish();
//				}
//			}		
//		});
		
		Mail mail = mailItems.get(position);
		title.setText(Html.fromHtml("<b>"+mail.getFrom()+"</b>"));
		String preview = mail.getMsg();
		if(preview.length() > 50) preview = preview.substring(0, 40)+ "...";
		msg.setText(preview);
		listItems.add(v);
		
		return v;
	}
	
//	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
//
//	    // Called when the action mode is created; startActionMode() was called
//	    @Override
//	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//	        // Inflate a menu resource providing context menu items
//	        MenuInflater inflater = mode.getMenuInflater();
//	        inflater.inflate(R.menu.contextual_menu, menu);
//	        return true;
//	    }
//
//	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
//	    // may be called multiple times if the mode is invalidated.
//	    @Override
//	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//	        return false; // Return false if nothing is done
//	    }
//
//	    // Called when the user selects a contextual menu item
//	    @Override
//	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//	    	switch(item.getItemId()) {
//			case R.id.item_delete:
//				deletePost();
//				mode.finish();
//				return true;
//			default:
//				return false;
//			}
//	    }
//
//		@Override
//		public void onDestroyActionMode(ActionMode arg0) {
//			// remove any checkmarks and clear the msgsToDelete list
//			for(int i=0; i < inboxList.getMsgsToDeleteSize(); i++) {
//				int item = inboxList.getMsgToDelete(i);
//				View v = (View) getItem(item);
//				CheckBox checkbox = (CheckBox) v.findViewById(R.id.checkbox);
//				checkbox.setChecked(false);
//			}
//			inboxList.clearMsgsToDelete();
//		}
//	};
//	
//	private void deletePost() {
//		SparseBooleanArray checked = inboxList.getList().getCheckedItemPositions();
//		//gets all of the checked list objects
//		//adds data to a mail object
//		//call async task to delete
//		for(int i = 0; i < checked.size(); i++) {
//			//Mail theSelectedMsg = (Mail) list.getItemAtPosition(checked.keyAt(i));
//			Mail theSelectedMsg = mailItems.get(checked.keyAt(i));
//			Toast.makeText(inboxList.getActivity(), "Deleting: " + theSelectedMsg.getId(), Toast.LENGTH_SHORT).show();
//			// collect the id's of the msgs and delete them from the db
//			inboxList.deleteMsgs(theSelectedMsg.getId());
//		}
//		
//	}
	
	public void setActionModeNull() {
		actionMode = null;
	}
	
}
