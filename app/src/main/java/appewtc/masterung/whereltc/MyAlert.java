package appewtc.masterung.whereltc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;

/**
 * Created by LTC on 12/14/2016.
 */

public class MyAlert {
    //Explicit
    private Context context;
    private String titleString, messString;
    private int anInt;

    public MyAlert(Context context, String titleString, String messString, int anInt) {
        this.context = context;
        this.titleString = titleString;
        this.messString = messString;
        this.anInt = anInt;
    }

    public void myDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}//Main Class Alert
