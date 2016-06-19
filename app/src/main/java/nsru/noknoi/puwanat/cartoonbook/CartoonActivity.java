package nsru.noknoi.puwanat.cartoonbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class CartoonActivity extends AppCompatActivity {

    //Explicit
    private TextView nameTextView, moneyTextView;
    private ListView listView;
    private String[] loginStrings;
    private boolean bolGuest;
    private String urlJSON = "http://www.swiftcodingthai.com/gun/get_cartoon.php";
    private String[] nameStrings, descripStrings, stockStrings,
            priceStrings, iconStrings;
    private String amountString, productIDString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);

        //bind widget
        nameTextView = (TextView) findViewById(R.id.textView9);
        moneyTextView = (TextView) findViewById(R.id.textView10);
        listView = (ListView) findViewById(R.id.listView);

        bolGuest = getIntent().getBooleanExtra("Guest", false);//false ==> guest, true ==> user
        if (bolGuest) {
            loginStrings = getIntent().getStringArrayExtra("Login");
            nameTextView.setText("Welcome : " + loginStrings[1] + "" + loginStrings[2]);
            checkMoney();
        }

        createListCartoon();


    }   //main method

    public void clickConfirmOrder(View view) {

        if (bolGuest) {
            //user
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);


            if (cursor.getCount() > 0) {

            } else {
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "ยังไม่มีสินค้า",
                "กรุณาเลือก สินค้าก่อนคะ");
            }

        } else {
            //guest
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่ได้Login",
                    "กรุณาLoginก่อน");
        }

        //click confirm
    }

    private void createListCartoon() {
        ConnectedCartoon connectedCartoon = new ConnectedCartoon(bolGuest, urlJSON, this);
        connectedCartoon.execute();

    }

    private void checkMoney() {
        moneyTextView.setText("Money : " + loginStrings[7] + "แต้ม");
    }

    private class ConnectedCartoon extends AsyncTask<Void, Void, String> {

        //explicit
        private Context context;
        private String urlJSONString;
        private boolean guestABoolean;


        public ConnectedCartoon(boolean guestABoolean,
                                String urlJSONString,
                                Context context) {
            this.guestABoolean = guestABoolean;
            this.urlJSONString = urlJSONString;
            this.context = context;
        }   //constructor

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSONString).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }

        }   //doInback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("18TestV2", "JASON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];
                descripStrings = new String[jsonArray.length()];
                stockStrings = new String[jsonArray.length()];
                priceStrings = new String[jsonArray.length()];
                iconStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    nameStrings[i] = jsonObject.getString("Name");
                    descripStrings[i] = jsonObject.getString("Description");
                    stockStrings[i] = jsonObject.getString("Stock");
                    priceStrings[i] = jsonObject.getString("Price");
                    iconStrings[i] = jsonObject.getString("Cover");     //ตัวหนังสือสีเขียวคือช่อคอลั่มในฐานข้อมูล

                }   //for

                CartoonAdapter cartoonAdapter = new CartoonAdapter(context,
                        iconStrings, nameStrings, descripStrings, priceStrings, stockStrings);
                listView.setAdapter(cartoonAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (guestABoolean) {
                            //from user
                            confirmdialog((position + 1), nameStrings[position]);

                        } else {
                            //from guest
                            MyAlert myAlert = new MyAlert();
                            myAlert.myDialog(context, "ยังไม่ได้Login!!",
                                    "กรุณา login หรือ สมัคร");
                        }

                    }   //onItemclick
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   //onPost
    }   //Async class

    private void confirmdialog(final int ProductID, String nameProduct) {

        CharSequence[] charSequences = new CharSequence[]{"1 เล่ม", "2 เล่ม", "3 เล่ม", "4 เล่ม",
                "5 เล่ม", "6 เล่ม", "7 เล่ม", "8 เล่ม", "9 เล่ม", "10 เล่ม"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(nameProduct);
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    amountString = Integer.toString(i + 1);
                Log.d("18TestV3", "ProductID ==> " + ProductID);
                Log.d("18TestV3", "amountString ==> " + amountString);

                MyManage myManage = new MyManage(CartoonActivity.this);
                myManage.addOrder(Integer.toString(ProductID), amountString);


                dialogInterface.dismiss();

            }   //onClick
        });
        builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.show();


    }   //confirm dialog

}   //main class
