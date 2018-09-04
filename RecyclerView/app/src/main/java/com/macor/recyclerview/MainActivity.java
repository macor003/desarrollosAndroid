package com.macor.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Call> callsList;
    private CallsAdapter callsAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progress = ProgressDialog.show(this,"Cargando","Espere por favor", true);

        listView = (ListView) findViewById(R.id.pagination_list);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.1.10.45:1092")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ServiceClient client = retrofit.create(ServiceClient.class);
        Call<List<ServicesResponse>> call = client.searchCommands();

        call.enqueue(new Callback<List<ServicesResponse>>() {
            @Override
            public void onResponse(Call<List<ServicesResponse>> call, Response<List<ServicesResponse>> response) {
                List<ServicesResponse> repos = response.body();
                progress.dismiss();

                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<ServicesResponse>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });






        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Set Layout Manager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Limiting the size
        recyclerView.setHasFixedSize(true);

        // Initialize list items
        init();

    }

    private void init() {
        callsList = new ArrayList<Call>();

        // Initiating Adapter
        callsAdapter = new CallsAdapter(MainActivity.this);
        recyclerView.setAdapter(callsAdapter);

        // Adding some demo data(Call Objects).
        // You can get them from your data server
        callsList.add(new Call("John", "9:30 AM"));
        callsList.add(new Call("Rob", "9:40 AM"));
        callsList.add(new Call("Peter", "9:45 AM"));
        callsList.add(new Call("Jack", "9:50 AM"));
        callsList.add(new Call("Bob", "9:55 AM"));
        callsList.add(new Call("Sandy", "10:00 AM"));
        callsList.add(new Call("Kate", "10:05 AM"));
        callsList.add(new Call("Daniel", "10:10 AM"));
        callsList.add(new Call("Roger", "10:15 AM"));
        callsList.add(new Call("Sid", "10:20 AM"));
        callsList.add(new Call("Kora", "10:25 AM"));
        callsList.add(new Call("Nick", "10:30 AM"));
        callsList.add(new Call("Rose", "10:35 AM"));
        callsList.add(new Call("Mia", "10:40 AM"));
        callsList.add(new Call("Scott", "10:45 AM"));

        // Set items to adapter
        callsAdapter.setCallsFeed(callsList);
        callsAdapter.notifyDataSetChanged();
    }
}
