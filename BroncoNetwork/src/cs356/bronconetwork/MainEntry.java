package cs356.bronconetwork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cs356.bronconetwork.fragments.InboxMain;
import cs356.bronconetwork.fragments.NewsfeedFragment;
import cs356.bronconetwork.fragments.TestFragment2;



public class MainEntry extends FragmentActivity {
	
	private MainEntryLayout slideHolder;
	private ListView sideBar;
	private FragmentManager fMger = getSupportFragmentManager();
	private FragmentTransaction fTrans = fMger.beginTransaction();
	
	private NewsfeedFragment newsfeed_fragment;
	private TestFragment2 test2;
	private InboxMain inbox;

	
	public void onBackPressed() {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
	}
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainentry);	
		
		slideHolder = (MainEntryLayout) findViewById(R.id.slideHolder);
		sideBar = (ListView) findViewById(R.id.sideBar);
		sideBar.setAdapter(new SideBarAdapter(this));
		sideBar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				fTrans = getSupportFragmentManager().beginTransaction();
				switch(position) {
					case 0: // newsfeed
						getActionBar().show();
						getActionBar().setTitle("Newsfeed");
						getActionBar().setIcon(R.drawable.icon_newsfeed);
						fTrans.hide(test2);
						fTrans.hide(inbox);
						if(newsfeed_fragment.isHidden()) {
							fTrans.show(newsfeed_fragment);
						}
						break;
					case 1: // profile
						getActionBar().show();
						getActionBar().setTitle("Profile");
						getActionBar().setIcon(R.drawable.icon_profile);
						fTrans.hide(inbox);
						fTrans.hide(newsfeed_fragment);
						if(test2.isHidden()) {
							fTrans.show(test2);
						}
						break;
					case 2: // groups
						break;
					case 3: // courses
						break;
					case 4: // inbox
						getActionBar().show();
						getActionBar().setTitle("Inbox");
						getActionBar().setIcon(R.drawable.icon_inbox);
						fTrans.hide(test2);
						fTrans.hide(newsfeed_fragment);
						if(inbox.isHidden()) {
							fTrans.show(inbox);
						}
						break;
					case 5: // logout
						logout();
						break;
				}
				fTrans.commit();
				slideHolder.toggle();
			}
			
		});
		
		ColorDrawable actionBarColor = new ColorDrawable(new Color().parseColor("#005c27"));
		
		getActionBar().show();
		getActionBar().setTitle("Newsfeed");
		getActionBar().setIcon(R.drawable.icon_newsfeed);
		getActionBar().setBackgroundDrawable(actionBarColor);
		newsfeed_fragment = new NewsfeedFragment();
		test2 = new TestFragment2();
		inbox = new InboxMain(fMger);
		fTrans.add(R.id.mainEntryContent, test2);
		fTrans.add(R.id.mainEntryContent, inbox);
		fTrans.add(R.id.mainEntryContent, newsfeed_fragment).commit();
		
	}
	
	public void logout() {
		getActionBar().hide();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
}
