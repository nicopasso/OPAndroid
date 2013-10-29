package it.imy.openponte;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

public class OPHomeActivity extends SherlockFragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	// private CharSequence mTitle;
	private String[] mDrawerLogoutTitles;

	// View Pager e PagerAdpater
	ViewPager mViewPager;
	OPFPagerAdpater mPagerAdapter;
	
	Tab listTab, mapTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Recupero le risorse per il NavigationDrawer
		mDrawerLogoutTitles = getResources().getStringArray(
				R.array.drawer_list_logout); // array di stringhe del drawer
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); // Navigation
																			// Drawer
		mDrawerList = (ListView) findViewById(R.id.left_drawer_lv); // ListView
																	// nel
																	// Navigation
																	// Drawer
		

		// Imposto un'ombra custom per l'overlay del main content quando il
		// drawer è aperto
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// Imposto l'adapter della lista del Drawer e il click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mDrawerLogoutTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Abilito l'icona dell'app della ActionBar ad agire come toggle per il
		// nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// L'ActionBarDrawerToggle tiene insieme l einterazioni tra il drawer e
		// l'icona dell'app nell'ActionBar
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				// creates call to onPrepareOptionsMenu()
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// **********************************************************
		// VIEW PAGER
		// **********************************************************
		
		mViewPager = (ViewPager) findViewById(R.id.home_view_pager);
		//mViewPager = new ViewPager(this);
		// Inizializzo il FragmentPagerAdapter
		mPagerAdapter = new OPFPagerAdpater(getSupportFragmentManager());

		// Collego il Pager con il PagerAdapter
		mViewPager.setAdapter(mPagerAdapter);

		//Tab Listener
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(tab.getPosition());
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		};
		
		// Aggiungo i TAB
		listTab = getSupportActionBar().newTab().setText("Elenco").setTabListener(tabListener);
				//.setIcon(R.drawable.android).setTabListener(tabListener);

		getSupportActionBar().addTab(listTab);
		
		mapTab = getSupportActionBar().newTab().setText("Mappa").setTabListener(tabListener);
		getSupportActionBar().addTab(mapTab);
		
		/** Set tab navigation mode */
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

                
		// Bind the widget to the adapter
		// PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		 
		 //final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		 		
		 //mViewPager.setPageMargin(pageMargin);

 		//tabs.setViewPager(mViewPager);

		// **********************************************************
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.ophome, menu);
		return true;
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// selectItem(position);
		}
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				
				mDrawerLayout.openDrawer(mDrawerList);
				
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
