package com.ipress.droidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DroidSample extends Activity implements OnClickListener {
	private static final int ACTIVITY_CREATE = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button addButton = (Button) this.findViewById(R.id.addButton);
		addButton.setOnClickListener(this);	    
	}

	public void onClick(View v) {
		Intent i = new Intent(this, DetailsForm.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}
}