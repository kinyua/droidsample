package com.ipress.droidsample;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class DroidSample extends ListActivity implements OnClickListener {
	private static final int CREATE_OPERATION = 0;
	private static final String TAG = "DroidSampleActivity";

	private DBConnector dbCon;
	private Cursor recordsCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button addButton = (Button) this.findViewById(R.id.addButton);
		addButton.setOnClickListener(this);

		dbCon = new DBConnector(this);

		refreshGrid();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		onClick(null);

		return super.onMenuItemSelected(featureId, item);
	}

	public void onClick(View v) {
		Intent i = new Intent(this, DetailsForm.class);
		startActivityForResult(i, CREATE_OPERATION);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();
		switch (requestCode) {
		case CREATE_OPERATION: {
			Long recordId = dbCon.save(extras);
			if (recordId > 0) {
				refreshGrid();
			} else {
				// to-do display alert informing user that something went wrong
				Log.v(TAG,
						"An error occured while saving a record...........................  "
								+ recordId);
			}
			break;
		}
		}
	}

	private void refreshGrid() {

		// Get all of the rows from the database and create the item list
		recordsCursor = dbCon.retrieveAllRecords();
		startManagingCursor(recordsCursor);

		// Map Cursor columns to views defined in records_row.xml
		ListAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.records_row, recordsCursor, new String[] {
						DBConnector.COLUMN_FNAME, DBConnector.COLUMN_LNAME },
				new int[] { R.id.fname_text, R.id.lname_text });

		setListAdapter(adapter);
	}
}