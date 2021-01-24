package com.joanneum.motivation.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joanneum.motivation.R;

public class CategoryAdapter extends BaseAdapter {
    final Context context;

    private final String[] categorys = {
            "Household",
            "Education",
            "Work",
            "Social",
            "Fitness",
            "Health",
            "Hobbys",
            "Special"
    };

    CategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return categorys.length;
    }

    @Override
    public Object getItem(int position) {
        return categorys[position];
    }

    @Override
    public long getItemId(int position) {
        return ((long) position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String category = categorys[position];
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View catView = inflator.inflate(R.layout.category, null);
        TextView description = catView.findViewById(R.id.txtCategoryName);
        description.setText(category);
        return catView;
    }
}
