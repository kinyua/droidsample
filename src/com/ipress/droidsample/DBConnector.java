package com.ipress.droidsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DBConnector {

	public static final String COLUMN_FNAME = "fname";
	public static final String COLUMN_LNAME = "lname";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_ID = "_id";

	private SQLiteDatabase database;
	private static final String DATABASE_TABLE = "users";

	private static class DbHelper extends SQLiteOpenHelper {

		DbHelper(Context context) {
			super(context, "droidsample_development", null, 2);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db
					.execSQL("create table "
							+ DATABASE_TABLE
							+ " (_id integer primary key autoincrement, "
							+ "fname text not null, lname text not null, gender text not null, location text);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public DBConnector(Context ctx) {
		DbHelper mDbHelper = new DbHelper(ctx);
		this.database = mDbHelper.getWritableDatabase();
	}

	public void close() {
		database.close();
    }
	
	public long save(Bundle extras) {
		ContentValues values = new ContentValues();
		values.put(DBConnector.COLUMN_FNAME, extras
				.getString(DBConnector.COLUMN_FNAME));
		values.put(DBConnector.COLUMN_LNAME, extras
				.getString(DBConnector.COLUMN_LNAME));
		values.put(DBConnector.COLUMN_GENDER, extras
				.getString(DBConnector.COLUMN_GENDER));
		values.put(DBConnector.COLUMN_LOCATION, extras
				.getString(DBConnector.COLUMN_LOCATION));

		// if id is present, the operation is an update
		Long id = extras.getLong(DBConnector.COLUMN_ID);
		if (0 == id) {
			return database.insertOrThrow(DATABASE_TABLE, null, values);
		} else {
			int affectedRows = database.update(DATABASE_TABLE, values,
					DBConnector.COLUMN_ID + "=" + id, null);
			if (1 == affectedRows) {
				return id;
			} else {
				return -1;
			}
		}
	}
	
	public Cursor retrieveAllRecords() {

        return database.query(DATABASE_TABLE, new String[] {DBConnector.COLUMN_ID, DBConnector.COLUMN_FNAME,
        		DBConnector.COLUMN_LNAME, DBConnector.COLUMN_GENDER, DBConnector.COLUMN_LOCATION}, null, null, null, null, null);
    }
}