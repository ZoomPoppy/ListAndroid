package com.zz.CriminalIntent;

import android.support.v4.app.Fragment;
import java.util.*;
public class CrimeAcitivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    /**
     * Called when the activity is first created.
     */
}
