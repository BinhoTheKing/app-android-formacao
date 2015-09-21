package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.mytaskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Task task;
    private Spinner spinnerLabels;
    private Button buttonSaveLabel;
    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
        initTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = extras.getParcelable(PARAM_TASK);
        }
        this.task = task == null ? new Task() : this.task;
        this.task.setLabel(this.task.getLabel());
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
