package com.mad_devs.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mad_devs.internet.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_launcher );

        Handler handler = new Handler ();
        handler.postDelayed ( new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent ( LauncherActivity.this , MainActivity.class );
                LauncherActivity.this.startActivity ( intent );
                LauncherActivity.this.finish ();

            }
        } , 2000 );

    }
}
