package nsru.noknoi.puwanat.cartoonbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {

    //explicit
    private TextView nameTextView, surnameTextView,
            moneyTextView, dateTextView, grandTotalTextView;
    private ListView listView;
    private String[] loginStrings, produckIDStrings, amountStrings,
                        productNameStrings, productPriceStrings,
                        totalStrings;
    private String dateString;
    private static final String urlJSON = "http://www.swiftcodingthai.com/gun/get_product_where_id.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //bind widget
        nameTextView = (TextView) findViewById(R.id.textView20);
        surnameTextView = (TextView) findViewById(R.id.textView21);
        moneyTextView = (TextView) findViewById(R.id.textView22);
        dateTextView = (TextView) findViewById(R.id.textView23);
        grandTotalTextView = (TextView) findViewById(R.id.textView28);
        listView = (ListView) findViewById(R.id.listView2);

        //current Date
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
        Date date = new Date();
        dateString = dateFormat.format(date);

        //Show view
        loginStrings = getIntent().getStringArrayExtra("Login");
        nameTextView.setText(loginStrings[1]);
        surnameTextView.setText(loginStrings[2]);
        moneyTextView.setText(loginStrings[7]);
        dateTextView.setText(dateString);

        //create listview
        createListView();

    }   //main method

    private void createListView() {

    }


    public void clickAddMore(View view) {
        finish();
    }

    public void clickOrder(View view) {

    }   //clickOrder

}   //main class
