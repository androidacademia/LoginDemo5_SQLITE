package login.com.girish.logindemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    //Declare database name and version
    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION  = 2;



    // create table user_reg (_id primary key autoincrement int,usr text,pass text);
    public static final String TABLE_USER = "user_reg";
    public static final String COL_USR = "usr";
    public static final String COL_PASS = "pass";
    public static final String CREATE_TABLE = "" +
            "create table "+TABLE_USER+" (_id  integer primary key autoincrement,"+COL_USR+" text,"+COL_PASS+" text)";



    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            //alter....
        }
    }
}
