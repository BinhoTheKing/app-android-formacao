package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.LabelRepository;

public class LabelFormActivity extends AppCompatActivity {
    private Spinner spinnerColors;
    private EditText editTextName;
    private EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);

        bindEditTextName();
        bindSpinnerColors();
        bindEditTextDescription();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onMenuSaveClick(){
        Label label = new Label();
        label.setName(editTextName.getText().toString());
        LabelRepository.save(label);
        Toast.makeText(LabelFormActivity.this, LabelRepository.getAll().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }

    private void bindSpinnerColors() {
        spinnerColors = (Spinner) findViewById(R.id.spinnerColors);
//        spinnerColors.setAdapter(new ColorListAdapter(LabelFormActivity.this));
    }
}
