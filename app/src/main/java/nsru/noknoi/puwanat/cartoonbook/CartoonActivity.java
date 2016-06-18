package nsru.noknoi.puwanat.cartoonbook;

import android.content.Context;
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

                final String[] nameStrings = new String[jsonArray.length()];
                String[] descripStrings = new String[jsonArray.length()];
                String[] stockStrings = new String[jsonArray.length()];
                String[] priceStrings = new String[jsonArray.length()];
                String[] iconStrings = new String[jsonArray.length()];

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

}   //main class
