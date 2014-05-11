package cs356.bronconetwork;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cs356.bronconetwork.MainActivity.PlaceholderFragment;
import cs356.bronconetwork.fragments.Inbox;
import cs356.bronconetwork.fragments.TestFragment;
import cs356.bronconetwork.fragments.TestFragment2;

public class MainEntry extends Activity {
	
	private MainEntryLayout slideHolder;
	private ListView sideBar;
	private String[] sideBarItems = {
		"Newsfeed", "Profile", "Groups", "Courses", "Inbox", "Logout"	
	};
	private FragmentManager fMger = getFragmentManager();
	private FragmentTransaction fTrans = fMger.beginTransaction();
	
	private NewsfeedFragment newsfeed_fragment;
	private TestFragment2 test2;
	private Inbox inbox;

	
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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sidebarlistitem, sideBarItems);
		sideBar.setAdapter(adapter);
		sideBar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				fTrans = getFragmentManager().beginTransaction();
				switch(position) {
					case 0: 
						getActionBar().show();
						getActionBar().setTitle("Newsfeed");
						getActionBar().setIcon(R.drawable.icon_newsfeed);
						fTrans.replace(R.id.mainEntryContent, newsfeed_fragment);
						break;
					case 1:
						getActionBar().show();
						getActionBar().setTitle("Profile");
						getActionBar().setIcon(R.drawable.icon_profile);
						if(test2 == null) test2 = new TestFragment2();
						fTrans.replace(R.id.mainEntryContent, test2);
						break;
					case 2: break;
					case 3: break;
					case 4: 
						if(inbox == null) inbox = new Inbox();
						fTrans.replace(R.id.mainEntryContent, inbox);
						break;
					case 5:
						logout();
						break;
				}
				fTrans.commit();
				slideHolder.toggle();
			}
			
		});
		
		getActionBar().show();
		getActionBar().setTitle("Newsfeed");
		getActionBar().setIcon(R.drawable.icon_newsfeed);
		newsfeed_fragment = new NewsfeedFragment();
		fTrans.add(R.id.mainEntryContent, newsfeed_fragment).commit();
		
	}
	
	public void logout() {
		getActionBar().hide();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
}
