package nsru.noknoi.puwanat.cartoonbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 17/6/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //explicit
    public static final String database_name = "Cartoon.db";
    private static final int database_version = 1;
    private static final String create_user_table = "create table userTable (" +
            "_id integer primary key," +
            "Name text," +
            "Surname text," +
            "Address text," +
            "Phone text," +
            "User text," +
            "Password text," +
            "Money text);";

    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   //constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   //main class
