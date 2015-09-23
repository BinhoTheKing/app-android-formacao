package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.services.UserBusinessService;

public class UserFormActivity extends AppCompatActivity {
    private EditText editTextNameView;
    private EditText editTextPasswordView;
    private EditText editTextRePasswordView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        user = new User();
        bindEditTextNameView();
        bindEditTextPasswordView();
        bindEditTextRePasswordView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_form, menu);
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
        String name = editTextNameView.getText().toString();
        String password = editTextPasswordView.getText().toString();
        String rePassword = editTextRePasswordView.getText().toString();
        if (password.equals(rePassword)) {
            user.setName(name);
            user.setPassword(password);
            UserBusinessService.save(user);
            finish();
        }else{
            Toast.makeText(this, R.string.msg_error_password_mismatch,Toast.LENGTH_SHORT).show();
        }
    }

    private void bindEditTextRePasswordView() {
        editTextRePasswordView = (EditText) findViewById(R.id.editTextUserRePassword);
    }

    private void bindEditTextPasswordView() {
        editTextPasswordView = (EditText) findViewById(R.id.editTextUserPassword);
    }

    private void bindEditTextNameView() {
        editTextNameView = (EditText) findViewById(R.id.editTextUserName);
    }
}
