package com.macor.practicaretrofit.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macor.practicaretrofit.api.model.Commands;

import java.util.List;

import io.futurestud.retrofit1.R;

public class CommandsAdapter extends RecyclerView.Adapter<CommandsAdapter.ViewHolder> {


    private Context context;
    private List<Commands> values;

    public CommandsAdapter(Context context, List<Commands> response) {

        this.context = context;
        this.values = response;
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
        Commands call = values.get(position);
        holder.showDetails(call);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nameTxtView;
        public TextView numberTxtView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.name);
            numberTxtView = itemView.findViewById(R.id.number);

        }

        public void showDetails(Commands order) {
            String name = order.getName();
            String number = String.valueOf(order.getNumber());
            nameTxtView.setText(name);
            numberTxtView.setText(number);

        }
    }
}
