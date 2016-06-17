package nsru.noknoi.puwanat.cartoonbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PC on 17/6/2559.
 */
public class MyManage {

    //explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String user_table = "userTable";
    public static final String colum_id = "_id";
    public static final String colum_Name = "Name";
    public static final String colum_Surname = "Surname";
    public static final String colum_Address = "Address";
    public static final String colum_Phone = "Phone";
    public static final String colum_User = "User";
    public static final String colum_Password = "Password";
    public static final String colum_Money = "Money";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();


    }   //constructor

    public long addNewUser(String strId,
                           String strName,
                           String strSurname,
                           String strAddress,
                           String strPhone,
                           String strUser,
                           String strPassword,
                           String strMoney) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(colum_id, strId);
        contentValues.put(colum_Name, strName);
        contentValues.put(colum_Surname, strSurname);
        contentValues.put(colum_Address, strAddress);
        contentValues.put(colum_Phone, strPhone);
        contentValues.put(colum_User, strUser);
        contentValues.put(colum_Password, strPassword);
        contentValues.put(colum_Money, strMoney);

        return writeSqLiteDatabase.insert(user_table, null, contentValues);
    }

}   //main class
