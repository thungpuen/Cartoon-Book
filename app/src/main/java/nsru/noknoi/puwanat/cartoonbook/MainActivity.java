package nsru.noknoi.puwanat.cartoonbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    //explicit
    private MyManage myManage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);

        //test add user
        myManage.addNewUser("1", "name", "sur", "add", "phone", "user",
                "pass", "money");


    }   //main method

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

}   //main class
