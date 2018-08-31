package com.macor.practicaretrofit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import io.futurestud.retrofit1.R;

import com.becoblohm.cr.net.response.RMIServerResponse;

import com.macor.practicaretrofit.api.service.ServiceClient;
import com.macor.practicaretrofit.ui.adapter.CommandsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private RecyclerView rView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progress = ProgressDialog.show(this, "Cargando", "Espere por favor", true);

        rView = findViewById(R.id.pagination_list);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.1.10.45:1092")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ServiceClient client = retrofit.create(ServiceClient.class);
        Call<RMIServerResponse> call = client.searchCommands();

        call.enqueue(new Callback<RMIServerResponse>() {
            public static final String TAG = "Error --->>>> ";

            @Override
            public void onResponse(Call<RMIServerResponse> call, Response<RMIServerResponse> response) {
                RMIServerResponse responseAnulDev = response.body();
                Log.i("Informacion", "Llego la petición *******************");

                progress.dismiss();
                rView.setAdapter(new CommandsAdapter(MainActivity.this, responseAnulDev));
            }

            @Override
            public void onFailure(Call<RMIServerResponse> call, Throwable t) {

                Log.i(TAG, t.getMessage());
                progress.dismiss();
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
