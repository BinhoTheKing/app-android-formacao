package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.services.TaskBusinessService;


public class TaskListActivity extends AppCompatActivity {

    private ListView listViewTaskList;
    private Task selectedTask;
    private User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Bundle extras = getIntent().getExtras();
        assert (extras.get(User.PARAM_USER)) != null;
        activeUser = (User) extras.get(User.PARAM_USER);
        bindListViewTaskList();
    }

    @Override
    protected void onResume() {
        updateTaskList();
        super.onResume();
    }

    private void updateTaskList() {
        TaskListAdapter adapter = (TaskListAdapter) listViewTaskList.getAdapter();
        List<Task> values = TaskBusinessService.findAll();/*UsersAll(activeUser.getId());*/
        adapter.setDataValues(values);
        adapter.notifyDataSetChanged();
    }

    private void bindListViewTaskList() {
        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        List<Task> values = TaskBusinessService.findAll();
        registerForContextMenu(listViewTaskList);
        listViewTaskList.setAdapter(new TaskListAdapter(this, values));
        listViewTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskListAdapter selectedAdapter = (TaskListAdapter) listViewTaskList.getAdapter();
                selectedTask = selectedAdapter.getItem(position);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(TaskListActivity.this, TaskFormActivity.class);
        goToTaskFormActivity.putExtra(User.PARAM_USER,activeUser);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_task_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                onMenuEditClick();
                break;
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(TaskListActivity.this, TaskFormActivity.class);
        goToTaskForm.putExtra(Task.PARAM_TASK, selectedTask);
        startActivity(goToTaskForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(TaskListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_delete_confirm)
                .setPositiveButton(getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskBusinessService.delete(selectedTask);
                        selectedTask = null;
                        CharSequence message = getString(R.string.msg_delete_task);
                        updateTaskList();
                        Toast.makeText(TaskListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton(getString(R.string.lbl_no), null)
                .create()
                .show();
    }
}
