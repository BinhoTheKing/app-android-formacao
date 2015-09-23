package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;

public class ImcActivity extends AppCompatActivity {
    EditText editTextPeso;
    EditText editTextAltura;
    Button buttonIMC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        bindEditTextAltura();
        bindEditTextPeso();
        bindButtonIMC();

    }

    private void bindButtonIMC() {
        buttonIMC = (Button) findViewById(R.id.buttonIMC);
        buttonIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = Double.parseDouble(editTextPeso.getText().toString());
                resultado /= Math.pow(Double.parseDouble(editTextAltura.getText().toString()),2);
                String message = getString(R.string.msg_imc,resultado);
                Toast.makeText(ImcActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindEditTextPeso() {
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
    }

    private void bindEditTextAltura() {
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
    }
}
