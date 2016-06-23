package edu.fontbonne.mobileapplab.flit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by rick on 4/27/16.
 */
public class SubmenuBudgetAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] subcategory;
    private final float[] amount;
    private final int layoutId;

    public SubmenuBudgetAdapter(Context context, int layoutId, String[] subcategory, float[] amount)
    {
        super(context, layoutId, subcategory);
        this.context = context;
        this.subcategory = subcategory;
        this.amount = amount;
        this.layoutId = layoutId;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
            row = LayoutInflater.from(context).inflate(layoutId, parent, false);

        TextView titleText = (TextView) row.findViewById(R.id.rowTitle);
        TextView amText = (TextView) row.findViewById(R.id.rowAmount);

        titleText.setText(subcategory[position]);
        amText.setText(String.format("%.2f", amount[position]));

        return row;
    }
}
