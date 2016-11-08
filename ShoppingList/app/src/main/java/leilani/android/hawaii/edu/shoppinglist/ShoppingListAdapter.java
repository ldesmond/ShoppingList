package leilani.android.hawaii.edu.shoppinglist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import leilani.android.hawaii.edu.shoppinglist.Grocery;
import leilani.android.hawaii.edu.shoppinglist.GroceryView;

/**
 * Created by Leilani on 10/31/2016.
 */

public class ShoppingListAdapter extends BaseAdapter{
    private Context m_context;
    private List<Grocery> m_shoppingList;

    public ShoppingListAdapter(Context context, List<Grocery> shoppingList){
        m_context = context;
        m_shoppingList = shoppingList;
    }

    public int getCount(){
        return m_shoppingList.size();
    }

    public Grocery getItem(int position){
        return m_shoppingList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public android.view.View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            GroceryView v = new GroceryView(m_context, getItem(position));
            return v;
        }
        else{
            ((GroceryView)convertView).setGrocery(getItem(position));
            return convertView;
        }
    }
}
