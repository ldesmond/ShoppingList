package leilani.android.hawaii.edu.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditGroceryActivity extends AppCompatActivity {

    Grocery m_grocery;
    EditText t_name;
    EditText t_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        m_grocery = intent.getExtras().getParcelable("grocery");
        initLayout();
    }
    private void initLayout(){
    t_name = (EditText)findViewById(R.id.grocery_name);
    t_location = (EditText)findViewById(R.id.grocery_location);
        t_name.setText(m_grocery.getGrocery());
        if(m_grocery.getLocation()==""){
            t_location.setText("None");
        }
        else t_location.setText(m_grocery.getLocation());

    }

    public void onSave(View view){
        this.m_grocery.setGrocery(t_name.getText().toString());

        //Navigating back to list screen
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }
    public void onChangeLocation(View view){
        //pass the grocery item through searchActivity and mapsActivity

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("grocery", (Parcelable) m_grocery);
        startActivity(intent);
    }
}
