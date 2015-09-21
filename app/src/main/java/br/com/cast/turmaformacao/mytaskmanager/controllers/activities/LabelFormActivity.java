package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Color;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.LabelRepository;

public class LabelFormActivity extends AppCompatActivity {
    private View viewColors;
    private EditText editTextName;
    private EditText editTextDescription;
    private Label label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);
        label = new Label();
        bindEditTextName();
        bindViewColors();
        bindEditTextDescription();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onMenuSaveClick() {
        label.setName(editTextName.getText().toString());
        LabelRepository.save(label);
        Toast.makeText(LabelFormActivity.this, LabelRepository.getAll().toString(), Toast.LENGTH_SHORT).show();
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

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }

    private void bindViewColors() {
        viewColors = findViewById(R.id.viewColors);
        viewColors.setBackgroundColor(android.graphics.Color.parseColor("red"));
        viewColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorListAdapter adapter = new ColorListAdapter(LabelFormActivity.this);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LabelFormActivity.this);
                dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateColor(adapter.getItem(which));
                    }
                });
                dialogBuilder.setTitle(R.string.dialog_color_label_title);
                dialogBuilder.setNeutralButton(R.string.lbl_cancel,null);
                dialogBuilder.create();
                dialogBuilder.show();
            }
        });
    }

    private void updateColor(Color item) {
        viewColors.setBackgroundColor(android.graphics.Color.parseColor(item.getHex()));
        label.setColor(item);
    }
}

