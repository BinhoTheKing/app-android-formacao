package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.mytaskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Task task;
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
        bindButtonSave();
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            this.task = extras.getParcelable(PARAM_TASK);
        }
        this.task = task == null ? new Task() : this.task;
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_error_required);
                if (!FormHelper.validateRequired(requiredMessage, editTextName, editTextDescription)) {
                    binTask();
                    Toast.makeText(TaskFormActivity.this, getString(R.string.msg_save_success), Toast.LENGTH_SHORT).show();
                    TaskBusinessService.save(task);
                    TaskFormActivity.this.finish();
                }
            }
        });
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
