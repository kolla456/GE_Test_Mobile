package com.example.ge_test_mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // you should either define client id and secret as constants or in string resources
    private final String clientId = "92ed1b8591f1cb0420f2";
    private final String clientSecret = "c86d553d4800d61e93b1298a3ddc1ab011111d9c";
    private final String redirectUri = "gemobiletest://callback";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize"+"?client_id="+clientId+"&scope=repo&redirect_url="+redirectUri));
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                // we'll do that in a minute
                Toast.makeText(getApplicationContext(),"Pass "+code,Toast.LENGTH_LONG).show();
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
            }
        }
    }
}
