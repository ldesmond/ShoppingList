package leilani.android.hawaii.edu.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity{
    protected Button m_Save;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.m_Save = (Button) findViewById(R.id.save_button);
        setContentView(R.layout.activity_settings);
//        initAddSettingsListeners();

    }

    protected void initAddSettingsListeners(){
        m_Save.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), ShoppingList.class);
                startActivity(intent);
            }
        });
    }

}

