package leilani.android.hawaii.edu.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import leilani.android.hawaii.edu.shoppinglist.Grocery;
import leilani.android.hawaii.edu.shoppinglist.R;

/**
 * Created by Leilani on 10/31/2016.
 */

public class GroceryView extends LinearLayout{
    private TextView m_vwGroceryText;
    private Grocery m_grocery;

    public GroceryView(Context context, Grocery grocery){
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.grocery_view, this, true);
        m_vwGroceryText = (TextView) findViewById(R.id.groceryTextView);

        setGrocery(grocery);
        requestLayout();
    }

    public void setGrocery(Grocery grocery){
        m_grocery = grocery;
        m_vwGroceryText.setText(grocery.getGrocery());
        m_vwGroceryText.setTextSize(16);
        this.requestLayout();
    }

    public String getText(){
        return m_grocery.getGrocery();
    }

    public Grocery getGrocery(){
        return m_grocery;
    }
}
