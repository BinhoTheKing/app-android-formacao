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
    private Color[] colors;

    public ColorListAdapter(Activity context) {
        super();
        this.context = context;
        colors = Color.values();
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Color getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View colorItemView = this.context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        colorItemView.findViewById(R.id.viewColor)
                .setBackgroundColor(android.graphics.Color.parseColor(getItem(position).getHex()));

        return colorItemView;
    }
}
