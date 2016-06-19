package nsru.noknoi.puwanat.cartoonbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }   //method

    public void clickAddMore(View view) {
        finish();
    }

    public void clickOrder(View view) {

    }   //clickOrder

}   //main class
