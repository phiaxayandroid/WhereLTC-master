package appewtc.masterung.whereltc;

import android.content.Context;
import android.os.AsyncTask;
import android.system.ErrnoException;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by LTC on 12/14/2016.
 */



//Need to loade libraly from server got to project structure(ctrl+alt+shit+s) choose app -->dependency --> +


public class InsertUser extends AsyncTask<Void,Void,String>{
    //Explicit
    private static final String urlPHP="http://swiftcodingthai.com/ltc/add_user_phiaxay.php";
    private Context context;
    private String nameString, usString,pasString;

    public InsertUser(Context context, String nameString, String usString, String pasString) {
        this.context = context;
        this.nameString = nameString;
        this.usString = usString;
        this.pasString = pasString;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();


            // step create package
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", nameString)
                    .add("User", usString)
                    .add("Password", pasString)
                    .build();
            Request.Builder builder = new Request.Builder();//created package
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            Log.d("14decV1", "e doin ==>" + e.toString());
            return null;

        }


    }
}//main Class InsertUser
