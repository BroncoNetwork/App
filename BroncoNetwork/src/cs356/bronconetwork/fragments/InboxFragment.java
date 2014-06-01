package cs356.bronconetwork.fragments;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.R;
import cs356.bronconetwork.ViewPagerParallax;

@SuppressLint("ValidFragment")
public class InboxFragment extends Fragment implements NetworkFragment {
	
	private MainEntry mainEntry;
	private TabHost tabHost;
	private ViewPagerParallax inboxPager;
	private InboxAdapter adapter;
	private String name = "Inbox";
	private int icon = R.drawable.icon_inbox;
	
	public InboxFragment(MainEntry mainEntry) {
		this.mainEntry = mainEntry;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.fragment_inbox, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tabHost = (TabHost) getView().findViewById(R.id.inboxTabHost);
		inboxPager = (ViewPagerParallax) getView().findViewById(R.id.inboxViewPager);
		adapter = new InboxAdapter(this);
		inboxPager.setBackgroundAsset(R.drawable.inbox_bg);
		inboxPager.set_max_pages(adapter.getCount());
		inboxPager.setAdapter(adapter);
		tabHost.setup();
		
		TabSpec inbox = tabHost.newTabSpec("Inbox");
		TabSpec sent = tabHost.newTabSpec("Sent");
		TabSpec compose = tabHost.newTabSpec("Compose");
		inbox.setContent(new TabContent());
		inbox.setIndicator("Inbox");
		sent.setContent(new TabContent());
		sent.setIndicator("Sent");
		compose.setContent(new TabContent());
		compose.setIndicator("Compose");
		
		tabHost.addTab(inbox);
		tabHost.addTab(sent);
		tabHost.addTab(compose);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				inboxPager.setCurrentItem(tabHost.getCurrentTab());
			}
		});
		inboxPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int pos, float percent, int offset) {
				
			}

			@Override
			public void onPageSelected(int pos) {
				tabHost.setCurrentTab(pos);
			}
			
		});
	}
	
	public void refresh() {
		adapter.refresh();
	}

	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getDrawableId() {
		return icon;
	}
	
	public MainEntry getMainEntry() {
		return mainEntry;
	}
	
	public TabHost getTabHost() {
		return tabHost;
	}
	
	public ViewPagerParallax getInboxPager() {
		return inboxPager;
	}
	
	class TabContent implements TabContentFactory {
		
		public TabContent() {}
		
		@Override
		public View createTabContent(String tag) {
			View v = new View(mainEntry);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
		
	}

}
