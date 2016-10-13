package leilani.android.hawaii.edu.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Menu m_vwMenu;
    Button m_vwSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.m_vwSettingsButton = (Button)this.findViewById(R.id.exit_button);
        setContentView(R.layout.activity_settings);
        initAddSettingsListeners();
    }

    protected void initAddSettingsListeners(){
        this.m_vwSettingsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        } );
    }
}
