package com.example.currency;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CurrencyAdapter extends BaseAdapter {

    List<Currency> currencyList;
    Activity activity;

    public CurrencyAdapter(List<Currency> currencyList, Activity activity) {
        this.currencyList = currencyList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        convertView = inflater.inflate(R.layout.items_layout, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.itemsName);
        tvName.setText(currencyList.get(position).getSign());
        return convertView;
    }
}
