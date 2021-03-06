package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.mytaskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Task task;
    private User activeUser;
    private Spinner spinnerLabels;
    private Button buttonSaveLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTask();
        bindEditTextName();
        bindEditTextDescription();
        bindSnipperLabels();
        bindButtonAddLabel();
    }

    private void bindButtonAddLabel() {
        buttonSaveLabel = (Button) findViewById(R.id.buttonAddLabel);
        buttonSaveLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskFormActivity = new Intent(TaskFormActivity.this, LabelFormActivity.class);
                goToTaskFormActivity.putExtra(getString(R.string.lbl_label), TaskFormActivity.this.task.getLabel());
                startActivity(goToTaskFormActivity);
            }
        });
    }

    private void bindSnipperLabels() {
        spinnerLabels = (Spinner) findViewById(R.id.snipperLabels);
        spinnerLabels.setAdapter(new LabelListAdapter(this));
        if (task!=null) {
            for (int i = 0; i<spinnerLabels.getAdapter().getCount(); i++){
                Label label = (Label) spinnerLabels.getItemAtPosition(i);
                if (task.getLabel()!=null) {
                    if(label.getId().equals(task.getLabel().getId())){
                        spinnerLabels.setSelection(i);
                    }
                }
            }
        }
        spinnerLabels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View spinnerLayout = TaskFormActivity.this.findViewById(R.id.spinnerLayout);
                spinnerLayout.setBackgroundColor(android.graphics.Color.parseColor(((Label)spinnerLabels.getSelectedItem()).getColor().getHex().replace("#", "#55")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = extras.getParcelable(Task.PARAM_TASK);
            this.activeUser = extras.getParcelable(User.PARAM_USER);
        }
        this.task = this.task == null ? new Task() : this.task;
        this.activeUser = this.activeUser == null ? new User() : this.activeUser;
        this.task.setUser(activeUser);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveClick() {
        String requiredMessage = TaskFormActivity.this.getString(R.string.msg_error_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextName, editTextDescription)) {
            binTask();
            Toast.makeText(TaskFormActivity.this, getString(R.string.msg_save_success), Toast.LENGTH_SHORT).show();
            TaskBusinessService.save(task);
            TaskFormActivity.this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void binTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
        task.setLabel((Label) spinnerLabels.getSelectedItem());
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(task.getName() == null ? "" : task.getName());
    }
}
