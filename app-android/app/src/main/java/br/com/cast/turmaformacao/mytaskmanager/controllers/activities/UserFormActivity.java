package br.com.cast.turmaformacao.mytaskmanager.controllers.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.mytaskmanager.R;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.Address;
import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.services.AddressHttpService;
import br.com.cast.turmaformacao.mytaskmanager.model.services.UserBusinessService;

public class UserFormActivity extends AppCompatActivity {
	private User user;
	private Address address;
	private EditText editTextNameView;
	private EditText editTextPasswordView;
	private EditText editTextRePasswordView;
	private EditText editTextZipCodeView;
	private Button buttonSearchZipCodeView;
	private LinearLayout layoutAddressView;
	private EditText editTextAddressTypeView;
	private EditText editTextAddressStreet;
	private EditText editTextAddressNeighborhoodView;
	private EditText editTextAddressCityView;
	private EditText editTextAddressStateView;

	private class GetAddressTask extends AsyncTask<String, Void, Address> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Address doInBackground(String... params) {
			return AddressHttpService.getAddressByZipCode(params[0]);
		}

		@Override
		protected void onPostExecute(Address address) {
			super.onPostExecute(address);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_form);
		initUser();
		bindLayoutComponents();
	}

	private void initUser() {
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			user = (User) extras.get(User.PARAM_USER);
		}
		if(user == null) {
			user = new User();
		}
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
		} else {
			Toast.makeText(this, R.string.msg_error_password_mismatch, Toast.LENGTH_SHORT).show();
		}
	}

	private void bindLayoutComponents() {
		bindEditTextNameView();
		bindEditTextPasswordView();
		bindEditTextRePasswordView();
		bindEditTextZipCodeView();
		bindButtonSearchZipCodeView();
		bindLayoutAddressView();
	}

	private void bindLayoutAddressView() {
		layoutAddressView = (LinearLayout) findViewById(R.id.listViewAddress);
	}

	private void bindButtonSearchZipCodeView() {
		buttonSearchZipCodeView = (Button) findViewById(R.id.buttonSearchZipCode);
		buttonSearchZipCodeView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					AsyncTask<String, Void, Address> asyncAddress = new GetAddressTask().execute(editTextZipCodeView.getText().toString());
					address = asyncAddress.get();
					user.setAddress(address);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				bindEditTextAddressTypeView();
				bindEditTextAddressStreet();
				bindEditTextAddressNeighborhoodView();
				bindEditTextAddressCityView();
				bindEditTextAddressStateView();
				layoutAddressView.setVisibility(View.VISIBLE);
			}
		});
	}

	private void bindEditTextRePasswordView() {
		editTextRePasswordView = (EditText) findViewById(R.id.editTextUserRePassword);
	}

	private void bindEditTextPasswordView() {
		editTextPasswordView = (EditText) findViewById(R.id.editTextUserPassword);
		editTextPasswordView.setText(user.getPassword());
	}

	private void bindEditTextNameView() {
		editTextNameView = (EditText) findViewById(R.id.editTextUserName);
		editTextNameView.setText(user.getName());
	}

	private void bindEditTextAddressStateView() {
		editTextAddressStateView = (EditText) findViewById(R.id.editTextAddressState);
		editTextAddressStateView.setText(user.getAddress().getState());
	}

	private void bindEditTextAddressCityView() {
		editTextAddressCityView = (EditText) findViewById(R.id.editTextAddressCity);
		editTextAddressCityView.setText(user.getAddress().getCity());
	}

	private void bindEditTextAddressNeighborhoodView() {
		editTextAddressNeighborhoodView = (EditText) findViewById(R.id.editTextAddressNeighborhood);
		editTextAddressNeighborhoodView.setText(user.getAddress().getNeighborhood());
	}

	private void bindEditTextAddressStreet() {
		editTextAddressStreet = (EditText) findViewById(R.id.editTextAddressStreet);
		editTextAddressStreet.setText(user.getAddress().getStreet());
	}

	private void bindEditTextAddressTypeView() {
		editTextAddressTypeView = (EditText) findViewById(R.id.editTextAddressType);
		editTextAddressTypeView.setText(user.getAddress().getType());
	}

	private void bindEditTextZipCodeView() {
		editTextZipCodeView = (EditText) findViewById(R.id.editTextZipCode);
	}
}
