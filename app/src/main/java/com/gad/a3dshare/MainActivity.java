package com.gad.a3dshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gad.a3dshare.adapter.RecyclerViewAdapter;
import com.gad.a3dshare.api.DesignAPI;
import com.gad.a3dshare.api.DesignsApi;
import com.gad.a3dshare.api.model.Design;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final int SECOND_REQUEST_CODE = 2;
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static Retrofit retrofit;

    public FloatingActionButton fab;

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

        //TextView welcome = findViewById(R.id.userTextView);
        //welcome.setText("Hello " + username);

        //DesignAPI designAPI = new DesignAPI();
        //designAPI.getDesigns();
        fetchData();
    }

    private void initViews() {
        fab = findViewById(R.id.floatingActionButton);
    }

    public void onFABClicked(View v) {
        Intent addDesignIntent = new Intent(MainActivity.this, AddDesignActivity.class);
        startActivityForResult(addDesignIntent, SECOND_REQUEST_CODE);
    }

    public void fetchData(){
        DesignsApi api = getRetrofit().create(DesignsApi.class);
        Call<List<Design>> designs = api.getDesigns("designs.json");
        designs.enqueue(new Callback<List<Design>>() {
            @Override
            public void onResponse(Call<List<Design>> call, Response<List<Design>> response) {
                if (response.isSuccessful()) {
                    Log.d("Response", response.body().toString());
                    displayData(response.body());
                } else {
                    Log.d("Response", "Response code " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Design>> call, Throwable t) {
                Log.w("Response", "Error in call", t);
            }
        });
    }

    public void displayData(List<Design> designList){
        RecyclerView recyclerView = findViewById(R.id.designListView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(designList);

        recyclerView.setAdapter(recyclerViewAdapter);
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
