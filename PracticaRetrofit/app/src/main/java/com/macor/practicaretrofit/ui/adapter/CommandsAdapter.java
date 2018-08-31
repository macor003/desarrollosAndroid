package com.macor.practicaretrofit.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;

import java.util.ArrayList;
import java.util.List;

import io.futurestud.retrofit1.R;

public class CommandsAdapter extends RecyclerView.Adapter<CommandsAdapter.ViewHolder> {


    private Context context;
    private RMIServerResponse values;

    public CommandsAdapter(Context context, RMIServerResponse responseAnulDev) {

        this.context = context;
        this.values = responseAnulDev;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pagination, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.i("onBindViewHolder", "Entro al metodo onBindViewHolder");

        ServicesResponse response = new ServicesResponse();
        response.setData(values.getData());
        List<Order> orders;
        orders = (List<Order>) response.getData();
        Order order = orders.get(position);

        holder.showDetails(order);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nameTxtView;
        public TextView numberTxtView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.name);
            numberTxtView = itemView.findViewById(R.id.number);

        }

        public void showDetails(Order order) {
            String name = order.getClient().getName();
            String number = order.getClient().getIdNumber();
            nameTxtView.setText(name);
            numberTxtView.setText(number);

        }
    }
}
