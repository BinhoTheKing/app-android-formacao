package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.services.UserBusinessService;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        user = new User();
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
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toGoToUserFormActivity();
            }
        });
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyUser()) {
                    String message = getString(R.string.msg_welcome,editTextLogin.getText());
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    redirectToTaskList.putExtra(User.PARAM_USER, user);
                    startActivity(redirectToTaskList);
                    finish();
                }else{
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    dialogBuilder.setMessage(getString(R.string.msg_error_usr));
                    dialogBuilder.setNeutralButton(R.string.lbl_no, null);
                    dialogBuilder.setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toGoToUserFormActivity();
                        }
                    });
                    dialogBuilder.show();
                }
            }
        });
    }

    private void toGoToUserFormActivity() {
        Intent redirectToUserForm = new Intent(LoginActivity.this, UserFormActivity.class);
	    bindUser();
        redirectToUserForm.putExtra(User.PARAM_USER, user);
        startActivity(redirectToUserForm);
    }

    private boolean verifyUser(){
	    bindUser();
        for (User usr :
                UserBusinessService.findAll()) {
            if(usr.getName().equals(user.getName())){
                if(usr.getPassword().equals(user.getPassword())){
                    user.setId(usr.getId());
                    return true;
                }
            }
        }
        return false;
    }

	private void bindUser() {
		user.setName(editTextLogin.getText().toString());
		user.setPassword(editTextPassword.getText().toString());
	}

	private void bindEditTextPassword() {
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText)findViewById(R.id.editTextLogin);
    }
}
