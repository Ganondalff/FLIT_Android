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
public class Submenu2Adapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] location;
    private final float[] amount;
    private final int layoutId;

    public Submenu2Adapter(Context context, int layoutId, String[] location, float[] amount)
    {
        super(context, layoutId, location);
        this.context = context;
        this.location = location;
        this.amount = amount;
        this.layoutId = layoutId;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
            row = LayoutInflater.from(context).inflate(layoutId, parent, false);

        TextView locText = (TextView) row.findViewById(R.id.rowLocation);
        TextView amText = (TextView) row.findViewById(R.id.rowAmount);

        locText.setText(location[position]);
        amText.setText('$' + String.format("%.2f", amount[position]));

        return row;
    }
}
