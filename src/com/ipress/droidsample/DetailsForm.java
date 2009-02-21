package com.ipress.droidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DetailsForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_form);
		
		Spinner localSpinner = (Spinner)findViewById(R.id.locationSpinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	            R.array.locations_array, R.layout.spinner_location_items);
	      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      localSpinner.setAdapter(adapter);
	      
	      Button saveButton = (Button)findViewById(R.id.saveButton);
	      saveButton.setOnClickListener(new View.OnClickListener() {

	          public void onClick(View view) {
	              setResult(RESULT_OK);
	              finish();
	          }
	      });

	}

}
