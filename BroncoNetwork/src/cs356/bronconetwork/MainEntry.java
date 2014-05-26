package cs356.bronconetwork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cs356.bronconetwork.fragments.CoursePageFragment;
import cs356.bronconetwork.fragments.CoursesFragment;
import cs356.bronconetwork.fragments.GroupsFragment;
import cs356.bronconetwork.fragments.InboxFragment;
import cs356.bronconetwork.fragments.NetworkFragment;
import cs356.bronconetwork.fragments.NewsfeedFragment;
import cs356.bronconetwork.fragments.ProfileFragment;
import cs356.bronconetwork.fragments.TestFragment;



public class MainEntry extends FragmentActivity {
	
	public static final int NEWSFEED 	= 0;
	public static final int PROFILE 	= 1;
	public static final int GROUPS 		= 2;
	public static final int COURSES 	= 3;
	public static final int INBOX 		= 4;
	public static final int COURSEPAGE 	= 5;
	public static final int LOGOUT 		= 6;
	
	public static final int SIZE 		= 6; // logout doesn't count
	
	private UserData user;
	private MainEntryLayout slideHolder;
	private ListView sideBar;
	private FragmentManager fMger = getSupportFragmentManager();
	
	private NetworkFragment[] frags = new NetworkFragment[SIZE];

	
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
		
		user = (UserData) getApplicationContext();

		frags[NEWSFEED] = new NewsfeedFragment();
		frags[PROFILE] = new ProfileFragment(this);
		frags[GROUPS] = new TestFragment();
		frags[COURSES] = new CoursesFragment(this);
		frags[INBOX] = new InboxFragment(this);
		frags[COURSEPAGE] = new CoursePageFragment(user.getCourse(0));
		
		slideHolder = (MainEntryLayout) findViewById(R.id.slideHolder);
		sideBar = (ListView) findViewById(R.id.sideBar);
		sideBar.setAdapter(new SideBarAdapter(this));
		
		sideBar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FragmentTransaction fTrans = fMger.beginTransaction();
				//fTrans = getSupportFragmentManager().beginTransaction();
				
				// if logout then logout
				if(position == 5) logout();
				else {
					getActionBar().show();
					getActionBar().setTitle(frags[position].getName());
					getActionBar().setIcon(frags[position].getDrawableId());
					
					for(NetworkFragment frag: frags) 
						fTrans.hide((Fragment) frag);
					fTrans.show((Fragment) frags[position]);
					
					fTrans.commit();
					slideHolder.toggle();
				}
			}
			
		});
		
		ColorDrawable actionBarColor = new ColorDrawable(new Color().parseColor("#005c27"));
		FragmentTransaction fTrans = fMger.beginTransaction();
		// add the bronconetwork fragments and initially hide them
		for(int i=frags.length-1 ; i >= 0; i--) {
			fTrans.add(R.id.mainEntryContent, (Fragment) frags[i]);
			fTrans.hide((Fragment) frags[i]);
		}
		getActionBar().show();
		getActionBar().setTitle(frags[NEWSFEED].getName());
		getActionBar().setIcon(frags[NEWSFEED].getDrawableId());
		getActionBar().setBackgroundDrawable(actionBarColor);
		
		// show the newsfeed
		fTrans.show((Fragment) frags[NEWSFEED]);
		fTrans.commit();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.refresh: 
				// right now only works for refreshing the inbox
				if(((Fragment) frags[NEWSFEED]).isVisible()) {
					((NewsfeedFragment) frags[NEWSFEED]).refresh();
					Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
				} else if(((Fragment) frags[INBOX]).isVisible()) {
					((InboxFragment) frags[INBOX]).refresh();
					Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
				} else if (((Fragment) frags[COURSEPAGE]).isVisible()) {
					((CoursePageFragment) frags[COURSEPAGE]).getData();
					Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
				}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//This function will switch current fragment to CoursePageFragment
	public void gotoCoursePage(String currentCourse)
	{
		setCurrentCourse(currentCourse);
		ColorDrawable actionBarColor = new ColorDrawable(new Color().parseColor("#005c27"));
		FragmentTransaction fTrans = fMger.beginTransaction();
		/*for(int i=frags.length-1 ; i >= 0; i--) {
			fTrans.hide((Fragment) frags[i]);
		}*/
		fTrans.hide((Fragment) frags[COURSES]);
		((CoursePageFragment) frags[COURSEPAGE]).setName(currentCourse);
		((CoursePageFragment) frags[COURSEPAGE]).getData();
		
		getActionBar().show();
		getActionBar().setTitle(frags[COURSEPAGE].getName());
		getActionBar().setIcon(frags[COURSEPAGE].getDrawableId());
		getActionBar().setBackgroundDrawable(actionBarColor);
		
		fTrans.show((Fragment) frags[COURSEPAGE]);
		fTrans.commit();
	}
	
	
	
	public void logout() {
		getActionBar().hide();
		//clear courses of current user when logging out
		for(int i = 0;i < 7;i++)
		{
			user.setCourse("", i);
		}
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
	
	public NetworkFragment getFrag(int i) {
		return frags[i];
	}
	
	//this function will be used in fragment 
	public String getUser()
	{
		return user.getUserName();
	}
	
	//this function will be used in fragment
	public String getEmail()
	{
		return user.getEmail();
	}
	
	//this function will be used in fragment
	public String[] getCourses()
	{
		return user.getCourses();
	}
	
	public String getCurrentCourse()
	{
		return user.getCurrentCourse();
	}
	
	public void setCurrentCourse(String course)
	{
		user.setCurrent(course);
	}
}
