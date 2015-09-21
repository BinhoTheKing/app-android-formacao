package br.com.cast.turmaformacao.mytaskmanager.controllers.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Color;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;


public class ColorListAdapter extends BaseAdapter {

    private Activity context;

    public ColorListAdapter(Activity context) {
        super();
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
        Color color = (Color) getItem(position);
        View colorSpinnerItemView =  convertView;
        if(colorSpinnerItemView == null){
            colorSpinnerItemView = this.context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        }
        View colorView = (View) colorSpinnerItemView.findViewById(R.id.viewColor);
        //colorView.setBackgroundColor(android.graphics.Color.parseColor(color.getHex()));
        colorView.setBackgroundColor(Integer.parseInt(color.getHex().replaceFirst("^#", ""), 16));

        return colorSpinnerItemView;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Color color = (Color) getItem(position);
        View colorSpinnerItemView =  convertView;
        if(colorSpinnerItemView == null){
            colorSpinnerItemView = this.context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        }
        View colorView = (View) colorSpinnerItemView.findViewById(R.id.viewColor);
        //colorView.setBackgroundColor(android.graphics.Color.parseColor(color.getHex()));
        colorView.setBackgroundColor(Integer.parseInt(color.getHex().replaceFirst("^#",""), 16));

        return colorSpinnerItemView;
    }
}
