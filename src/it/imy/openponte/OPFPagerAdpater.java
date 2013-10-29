package it.imy.openponte;

import it.imy.openponte.fragments.OPMapFragment;
import it.imy.openponte.fragments.OPPlacesListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OPFPagerAdpater extends FragmentPagerAdapter {
	
	 final int PAGE_COUNT = 2;

	public OPFPagerAdpater(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
        switch(arg0){
            /** List places tab is selected */
            case 0:
                OPPlacesListFragment listFragment = new OPPlacesListFragment();
                data.putInt("current_page", arg0+1);
                listFragment.setArguments(data);
                return listFragment;
 
            /** Map tab is selected */
            case 1:
                OPMapFragment mapFragment = new OPMapFragment();
                data.putInt("current_page", arg0+1);
                mapFragment.setArguments(data);
                return mapFragment;
        }
        
        return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}
	
	@Override
    public CharSequence getPageTitle(int position) {
            return position == 0 ? "Elenco" : "Mappa";
    }

}
