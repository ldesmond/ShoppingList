package leilani.android.hawaii.edu.shoppinglist;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.widget.Button;

/**
 * Created by Leilani on 10/17/2016.
 */

public class settingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
