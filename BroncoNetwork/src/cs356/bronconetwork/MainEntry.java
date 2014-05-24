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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cs356.bronconetwork.fragments.CoursePageFragment;
import cs356.bronconetwork.fragments.CoursesFragment;
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
	public static final int COURSESPAGE = 5;
	public static final int LOGOUT 		= 6;
	
	private String user = "";
	private String email = "";
	private String[] courses;
	private String currentC = "";
	
	public MainEntryLayout slideHolder;
	private ListView sideBar;
	FragmentManager fMger = getSupportFragmentManager();
	
	public NetworkFragment[] frags = {
		new NewsfeedFragment(), 
		new ProfileFragment(this), // profile placeholder
		new TestFragment(), // groups placeholder
		new CoursesFragment(this),
		new InboxFragment(this),
		new CoursePageFragment(this)
	};

	
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
		//FragmentManager fMger = getSupportFragmentManager();
		//call instance of UserData to use global data of user
		UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
		user = userInfo.getUserName();
		email = userInfo.getEmail();
		courses = userInfo.getCourses();
		//currentC = userInfo.getCurrentCourse();
		
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
		inflater.inflate(R.menu.inbox_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	//This function will switch current fragment to CoursePageFragment
	public void gotoCoursePage(String currentCourse)
	{
		//currentC = "CS356";
		setCurrentCourse(currentCourse);
		ColorDrawable actionBarColor = new ColorDrawable(new Color().parseColor("#005c27"));
		FragmentTransaction fTrans = fMger.beginTransaction();
		for(int i=frags.length-1 ; i >= 0; i--) {
			fTrans.hide((Fragment) frags[i]);
		}
		getActionBar().show();
		getActionBar().setTitle(frags[COURSESPAGE].getName());
		getActionBar().setIcon(frags[COURSESPAGE].getDrawableId());
		getActionBar().setBackgroundDrawable(actionBarColor);
		
		//NetworkFragment testing = (NetworkFragment) getFragmentManager().findFragmentBytag(CoursePageFragment);
		//testing.
		
		fTrans.show((Fragment) frags[COURSESPAGE]);
		fTrans.commit();
	}
	
	
	
	public void logout() {
		getActionBar().hide();
		//clear courses of current user when logging out
		UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
		for(int i = 0;i < 7;i++)
		{
			userInfo.setCourse("", i);
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
		return user;
	}
	
	//this function will be used in fragment
	public String getEmail()
	{
		return email;
	}
	
	//this function will be used in fragment
	public String[] getCourses()
	{
		return courses;
	}
	
	public String getCurrentCourse()
	{
		UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
		return userInfo.getCurrentCourse();
	}
	
	public void setCurrentCourse(String course)
	{
		UserData userInfo = (UserData)getApplicationContext();//userInfo will contain all user's info
		userInfo.setCurrent(course);
	}
}
