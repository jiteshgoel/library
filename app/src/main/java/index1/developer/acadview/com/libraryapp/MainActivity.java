package index1.developer.acadview.com.libraryapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in= new Intent(MainActivity.this, homeActivity.class);
                startActivity(in);
                finish();
            }
        },SPLASH_TIMEOUT);
    }
}
