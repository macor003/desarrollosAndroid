package com.macor.practicaretrofit.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.macor.practicaretrofit.api.model.Commands;
import com.macor.practicaretrofit.api.service.ServiceClient;
import com.macor.practicaretrofit.ui.adapter.CommandsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.futurestud.retrofit1.R;
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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progress = ProgressDialog.show(this, "Cargando", "Espere por favor", true);

        rView = findViewById(R.id.pagination_list);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(this));

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.1.10.45:1092")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ServiceClient client = retrofit.create(ServiceClient.class);
        Call<RMIServerResponse> call = client.searchCommands();

        call.enqueue(new Callback<RMIServerResponse>() {
            public static final String TAG = "Error --->>>> ";

            @Override
            public void onResponse(Call<RMIServerResponse> call, Response<RMIServerResponse> response) {
                List<Order> responseList = (List<Order>) response.body().getData();
                Log.i("Información", "Llego la petición *******************");

                List<Commands> commandsList = new ArrayList<>();

                commandsList.add(new Commands("CAROLINA SOLARES", 326377));
                commandsList.add(new Commands("FERNANDO ORELLANA", 326379));
                commandsList.add(new Commands("EDGAR MORA", 326384));
                commandsList.add(new Commands("ANTONIO PEREZ", 326385));
                commandsList.add(new Commands("WILLIAM PAIZ", 326390));

                progress.dismiss();
                rView.setAdapter(new CommandsAdapter(MainActivity.this, commandsList));
            }

            @Override
            public void onFailure(Call<RMIServerResponse> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                progress.dismiss();
                //Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builderAlert = new AlertDialog.Builder(MainActivity.this);
                builderAlert.setTitle("Error").setMessage("Error en la conexión con el servidor");
                builderAlert.setIcon(R.mipmap.ic_error);
                builderAlert.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        restartActivity(MainActivity.this);
                    }
                });
                builderAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog dialog = builderAlert.create();
                dialog.show();


            }
        });
    }

    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }

}


