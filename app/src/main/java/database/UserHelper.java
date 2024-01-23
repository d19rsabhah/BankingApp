package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import UI.AmountInput;
import UI.SelectUserActivity;
import UI.TransactionsFragment;
import UI.UserInfoActivity;
import UI.UsersFragment;
import database.UserContract.UserEntry;

public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "customer.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public UserHelper(AmountInput context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(UsersFragment context) {
        super(context.getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(TransactionsFragment context) {
        super(context.getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(SelectUserActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(UserInfoActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        + UserEntry.USER_ID + " INTEGER , "
        // Create a String that contains the SQL statement to create the customer table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("

                + UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " VARCHAR PRIMARY KEY, "
                + UserEntry.COLUMN_USER_NAME + " VARCHAR NOT NULL, "
                + UserEntry.COLUMN_GENDER + " INTEGER, "                // 0 - female and 1 - male
                + UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR NOT NULL, "
                + UserEntry.COLUMN_USER_CONTACT + " VARCHAR, "
                + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        String inputColumns = "(" + UserEntry.COLUMN_USER_ACCOUNT_NUMBER
                                + ", " + UserEntry.COLUMN_USER_NAME
                                + ", " + UserEntry.COLUMN_GENDER
                                + ", " + UserEntry.COLUMN_USER_EMAIL
                                + ", " + UserEntry.COLUMN_USER_IFSC_CODE
                                + ", " + UserEntry.COLUMN_USER_CONTACT
                                + ", " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + ")";

        // Insert Into Table
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('11111111111','Suvarnalaxmi Lambture', 0,  'suvarna@gmail.com','AXIS0987','9359678698', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('22222222222','Deeplaxmi Lambture', 0,  'deep@gmail.com','AXIS6543','9999955555', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('33333333333','Prakash Lambture', 1,  'prakash@gmail.com','BOI2109','7659611212', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('44444444444','Urmila Lambture', 0,  'urmila@gmail.com','SBI8765','8021754321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('55555555555','Steve Jobs', 1,'steve@gmail.com','AXIS4321','9098764398', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('66666666666','Bill Gates', 1, 'bill@gmail.com','BOI0987','8598765321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('77777777777','Jai Shetty', 1, 'jai@gmail.com','AXIS6543','7099876421', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('88888888888','Sandeep Maheshwari', 1, 'sandeep@gmail.com','HDFC2109','7031098765', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('99999999999','Shantanu Maheshwari', 1, 'shantanu@gmail.com','SBI8765','9987654432', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('32165498702','Indra Nooyi', 0, 'indra@gmail.com','BOI4321','8213141516', 10000)");

        Log.e("User Helper", "Inserted Dummy Data");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readAllDataExcept(String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " != " + accountNo, null);
        return cursor;
    }

    public Cursor readParticularData (String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(String accountNo, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + UserEntry.TABLE_NAME + " SET " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }

}
