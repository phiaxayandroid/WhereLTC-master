package appewtc.masterung.whereltc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Pxplicit
    private EditText userEditText, passEditText;
    private String userString, passString;
    private String[] loginStrings;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText4);
        passEditText = (EditText) findViewById(R.id.editText5);
    }   // Main Method

    public void clickSignin(View view) {

        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        if (userString.equals("") || passString.equals("")) {

            //Have space
            MyAlert myAlert = new MyAlert(MainActivity.this,
                    getResources().getString(R.string.title_have_space),
                    getResources().getString(R.string.message_have_space),
                    R.drawable.alertimage);
            myAlert.myDialog();

        } else {

            //No space
            try {

                SynUser synUser = new SynUser(MainActivity.this);
                synUser.execute();
                String s = synUser.get();
                Log.d("14decV2", "JSON ==>" + s);

                JSONArray jsonArray = new JSONArray(s);

                loginStrings = new String[4];

                for (int i = 0; i < jsonArray.length(); i += 1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    if (userString.equals(jsonObject.get("user"))) {
                        loginStrings[0] = jsonObject.getString("id");
                        loginStrings[1] = jsonObject.getString("name");
                        loginStrings[2] = jsonObject.getString("user");
                        loginStrings[3] = jsonObject.getString("pass");

                        aBoolean = false;

                    }


                }// for


                if (aBoolean) {
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            getResources().getString(R.string.title_user_false),
                            getResources().getString(R.string.message_have_false),
                            R.drawable.alertimage);
                    myAlert.myDialog();
                } else if (passString.equals(loginStrings[3])) {
                    // pass true

                } else {
                    // pass false
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            getResources().getString(R.string.title_pass_false),
                            getResources().getString(R.string.message_pass_false),
                            R.drawable.alertimage);
                    myAlert.myDialog();
                }


            } catch (Exception e) {
                Log.d("14decV2", "e Main==>" + e.toString());
            }

        }


    }// Click Signin

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
