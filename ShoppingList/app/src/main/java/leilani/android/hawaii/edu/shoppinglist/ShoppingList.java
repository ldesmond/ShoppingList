package leilani.android.hawaii.edu.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;


import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    protected ArrayList<Grocery> m_arrayGroceryList;
    protected ShoppingListAdapter m_shoppingAdapter;
    protected ListView m_vwShoppingLayout;
    protected EditText m_vwShoppingEditText;
    protected Button m_vwShoppingButton;
    protected Menu m_vwMenu;
    protected ActionMode actionMode;
    protected int m_currentId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Resources res = this.getResources();
        m_arrayGroceryList = new ArrayList<>();

        this.initLayout();
        this.m_shoppingAdapter = new ShoppingListAdapter(this, m_arrayGroceryList);
        this.m_vwShoppingLayout.setAdapter(m_shoppingAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shopping_list, menu);
        m_vwMenu = menu;
        return true;
    }

    protected void initLayout(){
        setContentView(R.layout.activity_shopping_list);
        this.m_vwShoppingLayout = (ListView) findViewById(R.id.shoppingListViewGroup);
        this.m_vwShoppingEditText = (EditText) findViewById(R.id.newShoppingEditText);
        this.m_vwShoppingButton = (Button) findViewById(R.id.addShoppingButton);
        this.initAddShoppingListeners();
        this.initLongClickListener();
    }

    protected void initAddShoppingListeners(){
        m_vwShoppingEditText.setOnKeyListener(new OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))||((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER))
                        ){
                    String shoppingText = m_vwShoppingEditText.getText().toString();
                    Grocery grocery = new Grocery(shoppingText);
                    addGrocery(grocery);

                    m_vwShoppingEditText.setText("");
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(m_vwShoppingEditText.getWindowToken(), 0);
                }
                return false;
            }
        });
        m_vwShoppingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String groceryText = m_vwShoppingEditText.getText().toString();
                Grocery grocery = new Grocery(groceryText);
                addGrocery(grocery);

                m_vwShoppingEditText.setText("");
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(m_vwShoppingEditText.getWindowToken(), 0);
            }
        });
    }

    protected void initLongClickListener(){
        m_vwShoppingLayout.requestFocus();
        m_vwShoppingLayout.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               if(actionMode !=null)
                   return false;
                m_currentId = position;

                actionMode = startActionMode(callback);
                return true;
            }
        });
    }

    protected Callback callback = new Callback(){
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_shopping_list,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.menu_remove:
                    m_arrayGroceryList.remove(m_currentId);
                    m_shoppingAdapter.notifyDataSetChanged();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {
            actionMode = null;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void addGrocery(Grocery grocery){
        m_arrayGroceryList.add(grocery);
        m_shoppingAdapter.notifyDataSetChanged();
    }
}