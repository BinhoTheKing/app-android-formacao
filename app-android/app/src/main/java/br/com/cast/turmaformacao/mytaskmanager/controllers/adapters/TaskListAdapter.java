package br.com.cast.turmaformacao.mytaskmanager.controllers.adapters;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;

public class TaskListAdapter extends BaseAdapter {

	private List<Task> taskList;
	private Activity context;

	public TaskListAdapter(Activity context, List<Task> taskList) {
		this.context = context;
		this.taskList = taskList;
	}

	public void setDataValues(List<Task> values) {
		this.taskList.clear();
		this.taskList.addAll(values);
	}

	@Override
	public int getCount() {
		return taskList.size();
	}

	@Override
	public Task getItem(int position) {
		return taskList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Task task = getItem(position);
		View taskListItemView = context.getLayoutInflater().inflate(R.layout.list_item_task, parent, false);


		ImageView viewColor = (ImageView) taskListItemView.findViewById(R.id.viewColor);
		GradientDrawable shape = (GradientDrawable) context.getResources().getDrawable(R.drawable.shape_circle);
		if (shape != null) {
			if (task.getLabel() != null) {
				shape.setColor(android.graphics.Color.parseColor(task.getLabel().getColor().getHex()));
			}
			viewColor.setImageDrawable(shape);
		}

		TextView textViewName = (TextView) taskListItemView.findViewById(R.id.textViewName);
		textViewName.setText(task.getName());

		return taskListItemView;
	}
}
