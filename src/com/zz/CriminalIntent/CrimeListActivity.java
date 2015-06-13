package com.zz.CriminalIntent;

import android.support.v4.app.Fragment;

/**
 * Created by zz on 2015/6/12.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
