package appewtc.masterung.whereltc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by LTC on 12/14/2016.
 */

public class SynUser extends AsyncTask<Void, Void, String>{
    //Explicit
    private Context context;
    private static final String urlJSON="http://swiftcodingthai.com/ltc/get_user_phiaxay.php";

    public SynUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).build();
            Response respon = okHttpClient.newCall(request).execute();
            return respon.body().string();



        } catch (Exception e) {
            Log.d("14decV2", "e doin" + e.toString());
        }
        return null;
    }
}//Class main SynUser
