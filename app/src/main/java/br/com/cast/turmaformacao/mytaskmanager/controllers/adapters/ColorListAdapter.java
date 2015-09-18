package br.com.cast.turmaformacao.mytaskmanager.controllers.adapters;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Color;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;


public class ColorListAdapter extends BaseAdapter {

    private Activity context;

//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public void registerDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public int getCount() {
//        return labels.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return labels.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return labels.get(position).getId();
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return 0;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }





    public ColorListAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Color.values().length;
    }

    @Override
    public Object getItem(int position) {
        return Color.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;


        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = this.context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_color, null);

            rowView.setBackgroundColor(android.graphics.Color.parseColor(Color.values()[position].getHex()));

        } else {
            rowView.setBackgroundColor(android.graphics.Color.parseColor(Color.values()[position].getHex()));
        }


        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View labelView = convertView;

        if (labelView == null) {
            labelView = this.context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        }
        int parsedColor = android.graphics.Color.parseColor(Color.values()[position].getHex());

        labelView.setBackgroundColor(parsedColor);

        return labelView;
    }
}
