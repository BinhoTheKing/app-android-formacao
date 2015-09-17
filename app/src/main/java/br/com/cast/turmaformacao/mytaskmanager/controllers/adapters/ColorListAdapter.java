package br.com.cast.turmaformacao.mytaskmanager.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Color;


public class ColorListAdapter extends BaseAdapter {

    private Activity context;
    private List<Color> colors;


    public ColorListAdapter(Activity context) {
        this.context = context;
        this.colors = new ArrayList<>();
        for (Color color :
                Color.values()) {
            this.colors.add(color);
        }
    }

    @Override
    public int getCount() {
        return this.colors.size();
    }

    @Override
    public Object getItem(int position) {
        return this.colors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View labelView = convertView;

        if (labelView == null) {
            labelView = this.context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        }
        labelView.setBackgroundColor(android.graphics.Color.parseColor(colors.get(position).getHex()));

        return labelView;
    }
}
