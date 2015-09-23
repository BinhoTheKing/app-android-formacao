package br.com.cast.turmaformacao.mytaskmanager.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Color;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.LabelRepository;


public class LabelListAdapter extends BaseAdapter {

    private Activity context;
    private List<Label> labels;

    public LabelListAdapter(Activity context) {
        super();
        this.context = context;
        labels = LabelRepository.getAll();
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View colorItemView = this.context.getLayoutInflater().inflate(R.layout.list_item_label, parent, false);
        TextView colorTextView = (TextView) colorItemView.findViewById(R.id.textViewLabel);
        colorTextView.setText(getItem(position).getName());
        colorTextView.setBackgroundColor(android.graphics.Color.parseColor(getItem(position).getColor().getHex()));

        return colorItemView;
    }
}
