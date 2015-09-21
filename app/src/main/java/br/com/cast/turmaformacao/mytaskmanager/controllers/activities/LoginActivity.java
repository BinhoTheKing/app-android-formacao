package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onResume() {
        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
        bindButtonSignUp();
        super.onResume();
    }

    private void bindButtonSignUp() {
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getString(R.string.msg_welcome,editTextLogin.getText());
                Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                startActivity(redirectToTaskList);
                finish();
            }
        });
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText)findViewById(R.id.editTextLogin);
    }
}
