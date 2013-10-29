package it.imy.openponte.fragments;

import it.imy.openponte.OPFPagerAdpater;
import it.imy.openponte.OPHomeActivity;
import it.imy.openponte.R;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

public class OPHomeFragment extends SherlockFragment {
	// View Pager e PagerAdpater
	ViewPager mViewPager;
	OPFPagerAdpater mPagerAdapter;
	LinearLayout mContentLayout;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mContentLayout = (LinearLayout)inflater.inflate(R.layout.fragment_home_layout, null);
		
		// **********************************************************
		// VIEW PAGER
		// **********************************************************
		
		//mViewPager = (ViewPager) mContentLayout.findViewById(R.id.home_view_pager);
		
		mViewPager = new ViewPager(getActivity());

		// Inizializzo il FragmentPagerAdapter
		mPagerAdapter = new OPFPagerAdpater(getFragmentManager());

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
		Tab listTab = ((OPHomeActivity)getActivity()).getSupportActionBar().newTab().setText("Elenco").setTabListener(tabListener);
				//.setIcon(R.drawable.android).setTabListener(tabListener);

		((OPHomeActivity)getActivity()).getSupportActionBar().addTab(listTab);
		
		Tab mapTab = ((OPHomeActivity)getActivity()).getSupportActionBar().newTab().setText("Mappa").setTabListener(tabListener);
		((OPHomeActivity)getActivity()).getSupportActionBar().addTab(mapTab);
		
		/** Set tab navigation mode */
		((OPHomeActivity)getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
//		// Bind the widget to the adapter
//		 PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) mContentLayout.findViewById(R.id.tabs);
//		 
//		 final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
//                 .getDisplayMetrics());
//		 		
//		 mViewPager.setPageMargin(pageMargin);
//
// 		tabs.setViewPager(mViewPager);

		// **********************************************************
 
 		return inflater.inflate(R.layout.fragment_home_layout, container, false);
    }
	
}
