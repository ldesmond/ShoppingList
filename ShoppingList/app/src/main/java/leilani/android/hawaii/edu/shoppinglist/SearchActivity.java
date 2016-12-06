package leilani.android.hawaii.edu.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private LatLng currentLatLng;
    private String searchField;
    protected ListView addressListView;
    private ArrayList<Address> addressList;
    // Search bar EditText view
    protected EditText searchFieldEditText;
    protected ListAdapter addressAdapter;
    private Grocery selectedGrocery;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField="";
        currentLatLng = new LatLng(21.300191, -157.819018);
//        Intent intent = getIntent();
//        selectedGrocery = intent.getExtras().getParcelable("grocery");
        initLayout();

    }
    public void initLayout(){
        searchFieldEditText = (EditText) findViewById(R.id.search_bar);
        searchFieldEditText.setText(searchField);
        /* hiding the keyboard */
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        /* end hiding the keyboard*/
        try {
            onNewSearch();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(searchField!=""){
            addressAdapter = new AddressListAdapter(this, addressList);
            addressListView = (ListView)findViewById(R.id.addressSearchListView);
            addressListView.setAdapter(addressAdapter);
            addressListView.setOnItemClickListener(new
                                                           AdapterView.OnItemClickListener() {
                                                               @Override
                                                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                                                   String picked = "You selected" + String.valueOf(parent.getItemAtPosition(position));
//                                                                   Toast.makeText(SearchActivity.this, picked, Toast.LENGTH_SHORT).show();
                                                                   Address viewAddress = (Address)parent.getItemAtPosition(position);
                                                                   viewOnMap(viewAddress);

                                                               }
                                                           });

        }

    }



    public void viewOnMap(Address address){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("address",address);
//        intent.putExtra("grocery", (Parcelable) this.selectedGrocery);
        startActivity(intent);
    }

    public void onSearch(View view) throws IOException {
        searchField = searchFieldEditText.getText().toString();
        initLayout();
    }
    public void onNewSearch() throws IOException {

        LatLngBounds currentBounds = toBounds(currentLatLng, 2);
        searchField = searchField.trim();
        addressList = new ArrayList<>();

        Geocoder geocoder = new Geocoder(this);
        try {
            // add location to addressList
            List<Address> list1 = geocoder.getFromLocationName(searchField,
                    6 /* number of search results*/,
                    currentBounds.southwest.latitude,
                    currentBounds.southwest.longitude,
                    currentBounds.northeast.latitude,
                    currentBounds.northeast.longitude);
            addressList.addAll(list1);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public LatLngBounds toBounds(LatLng center, double radius) {
        //radius in miles
        //THERE IS A PROBLEM IN THIS BLOCK WHERE? WHO KNOWS?
//        double earthRadius = 3959;
        double centerLat = center.latitude;
        double centerLng = center.longitude;
        double latChange = radius * 180 / (Math.PI * 3959);
        double longChange = latChange / Math.sin(centerLat);
        double sw_lat = centerLat - latChange;
        double ne_lat = centerLat + latChange;
        double sw_lng = centerLng - longChange;
        double ne_lng = centerLng + longChange;
        LatLng southwest = new LatLng(sw_lat, sw_lng);
        LatLng northeast = new LatLng(ne_lat, ne_lng);
        return new LatLngBounds(southwest, northeast);
    }


}