package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.ColorListAdapter;

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

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }

    private void bindSpinnerColors() {
        spinnerColors = (Spinner) findViewById(R.id.spinnerColors);
        spinnerColors.setAdapter(new ColorListAdapter(LabelFormActivity.this));
    }
}
