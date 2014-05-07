package cs356.bronconetwork;


import cs356.bronconetwork.fragments.TestFragment;
import cs356.bronconetwork.fragments.TestFragment2;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainEntry extends Activity {
	
	private MainEntryLayout slideHolder;
	private ListView sideBar;
	private String[] sideBarItems = {
		"Profile", "Newsfeed", "Groups", "Courses", "Settings"	
	};
	private FragmentManager fMger = getFragmentManager();
	private FragmentTransaction fTrans = fMger.beginTransaction();
	
	private TestFragment test1;
	private TestFragment2 test2;

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
						fTrans.replace(R.id.mainEntryContent, test1);
						break;
					case 1:
						if(test2 == null) test2 = new TestFragment2();
						fTrans.replace(R.id.mainEntryContent, test2);
						break;
					case 2: break;
					case 3: break;
					case 4: break;
				}
				fTrans.commit();
				slideHolder.toggle();
			}
			
		});
		
		getActionBar().hide();
		test1 = new TestFragment();
		fTrans.add(R.id.mainEntryContent, test1).commit();
		
	}
	
	
}
