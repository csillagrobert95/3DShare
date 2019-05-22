package com.gad.a3dshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gad.a3dshare.api.DesignAPI;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int SECOND_REQUEST_CODE = 2;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Bundle extras = getIntent().getExtras();
        String username = null;
        if (extras != null) {
            FirebaseUser user = extras.getParcelable("user_data");
            username = user.getEmail();
        }

        TextView welcome = findViewById(R.id.userTextView);
        welcome.setText("Hello " + username);

        DesignAPI designAPI = new DesignAPI();
        designAPI.getDesigns();
    }

    private void initViews() {
        fab = findViewById(R.id.floatingActionButton);
    }

    public void onFABClicked(View v) {
        Intent addDesignIntent = new Intent(MainActivity.this, AddDesignActivity.class);
        startActivityForResult(addDesignIntent, SECOND_REQUEST_CODE);
    }

}
