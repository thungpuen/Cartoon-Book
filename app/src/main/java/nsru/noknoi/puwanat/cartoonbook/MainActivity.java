package nsru.noknoi.puwanat.cartoonbook;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    //explicit
    private MyManage myManage;
    private static final String urlJSON = "http://swiftcodingthai.com/gun/get_user_gun.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);

        //test add user
//        myManage.addNewUser("1", "name", "sur", "add", "phone", "user",
//                "pass", "money");

        //delete all SQLite
        deleteAllSQLite();

        mySynchronizeJSON();


    }   //main method

    private void mySynchronizeJSON() {
        ConnectedUSER connectedUSER = new ConnectedUSER(MainActivity.this, urlJSON);
        connectedUSER.execute();
    }


    private class ConnectedUSER extends AsyncTask<Void, Void, String> {

        private Context context;
        private String myJSON;

        public ConnectedUSER(Context context, String myJSON) {
            this.context = context;
            this.myJSON = myJSON;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }
           }   //dooInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("17JuneV1", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strId = jsonObject.getString("id");
                    String strName = jsonObject.getString("Name");
                    String strSurname = jsonObject.getString("Surname");
                    String strAddress = jsonObject.getString("Address");
                    String strPhone = jsonObject.getString("Phone");
                    String strUsername = jsonObject.getString("Username");
                    String strPassword = jsonObject.getString("Password");
                    String strMoney = jsonObject.getString("Money");
                    myManage.addNewUser(strId, strName, strSurname, strAddress, strPhone,
                            strUsername, strPassword, strMoney);

                }      //for

                Toast.makeText(context, "โหลดข้อมูลจาก Server เรียบร้อยแล้ว",
                        Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                e.printStackTrace();
            }



        }   //onPost
    }   //AsyncTask Class


    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

}   //main class
