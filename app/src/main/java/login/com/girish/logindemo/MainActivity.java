package login.com.girish.logindemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUN,editTextP;
    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUN = findViewById(R.id.editTextUserName);
        editTextP = findViewById(R.id.editTextPassword);


        ///create an object of MyDatabase
        myDatabase = new MyDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        String db_response =" ";
        String usr = editTextUN.getText().toString();
        String pass = editTextP.getText().toString();
        sqLiteDatabase = myDatabase.getReadableDatabase();

        //selectionArgument is array of string... where you can "where" clause
        // String[] selectionArgs = {"user = username","and","password= pass"}
        // select * from user_reg where usr = '' and pass = '';
        if (usr.isEmpty() || pass.isEmpty()){
            db_response = "Field cannot be empty";
        }else {
            String query = "select * from " + MyDatabase.TABLE_USER + " where " + MyDatabase.COL_USR + " = '" + usr + "'" +
                    " and " + MyDatabase.COL_PASS + " = '" + pass + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToNext()){

                String u = cursor.getString(1);
                db_response = u+ " , SUCCESS";
            }
        }
        Snackbar.make(view,db_response,Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void signup(View view) {
        String db_response =" ";
        String usr = editTextUN.getText().toString();
        String pass = editTextP.getText().toString();

        if (usr.isEmpty() || pass.isEmpty()){
           db_response = "Field cannot be empty";
        }else{
            sqLiteDatabase = myDatabase.getReadableDatabase();

            //selectionArgument is array of string... where you can "where" clause
            // String[] selectionArgs = {"user = username","and","password= pass"}
            // select * from user_reg where usr = '' and pass = '';
            String query = "select * from "+MyDatabase.TABLE_USER+" where "+MyDatabase.COL_USR+" = '"+usr+"'";

            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
            if (cursor.moveToNext()){
                db_response = "Username not available";
            }else {
                ///////////////////////////////////////////////////
                sqLiteDatabase = myDatabase.getWritableDatabase();
                //Create an object of contentvalue to insert some values in columns
                ContentValues contentValues = new ContentValues();
                contentValues.put(MyDatabase.COL_USR,usr);
                contentValues.put(MyDatabase.COL_PASS,pass);
                //use this method to insert into databaase.....
                sqLiteDatabase.insert(MyDatabase.TABLE_USER,null,contentValues);
                ///sqLiteDatabase.update()
                //ITS done....
                db_response = "user added";
                editTextP.setText("");
                editTextUN.setText("");;
            }

        }
        Snackbar.make(view,db_response,Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();

    }
}















