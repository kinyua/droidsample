package com.ipress.droidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class DetailsForm extends Activity {

	private EditText fNameEdittext;
	private EditText lNameEdittext;
	private RadioButton femaleRBut;
	private Spinner locationSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_form);

		fNameEdittext = (EditText) findViewById(R.id.fNameEdittext);
		lNameEdittext = (EditText) findViewById(R.id.lNameEdittext);
		femaleRBut = (RadioButton) findViewById(R.id.femaleRBut);
		locationSpinner = (Spinner) findViewById(R.id.locationSpinner);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.locations_array, R.layout.spinner_location_items);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationSpinner.setAdapter(adapter);

		Button saveButton = (Button) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				Bundle bundle = new Bundle();

				bundle.putString(DBConnector.COLUMN_FNAME, fNameEdittext
						.getText().toString());
				bundle.putString(DBConnector.COLUMN_LNAME, lNameEdittext
						.getText().toString());
				bundle.putString(DBConnector.COLUMN_GENDER, femaleRBut
						.isChecked() ? "M" : "F");
				bundle.putString(DBConnector.COLUMN_LOCATION, locationSpinner
						.getSelectedItem().toString());

				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();
			}
		});
	}
}