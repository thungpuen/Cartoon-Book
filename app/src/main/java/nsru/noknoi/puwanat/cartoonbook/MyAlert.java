package nsru.noknoi.puwanat.cartoonbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by PC on 17/6/2559.
 */
public class MyAlert {

    public void myDialog(Context context,
                         String strTitle,
                         String strMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.doremon48);
        builder.setCancelable(false);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

}       //main class
