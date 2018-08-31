package com.macor.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.ViewHolder> {

    private List<Call> callsFeed = new ArrayList();
    // Context is not used here but may be required to
    // perform complex operations or call methods from outside
    private Context context;

    // Constructor
    public CallsAdapter(Context context) {
        this.context = context;
    }

    public void setCallsFeed(List<Call> callsFeed) {
        this.callsFeed = callsFeed;
    }

    // Invoked by layout manager to create new views
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Attach layout for single cell
        int layout = R.layout.activity_calls_feed_layout;
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    // Invoked by layout manager to replace the contents of the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Call call = callsFeed.get(position);
        holder.showCallDetails(call);
    }

    @Override
    public int getItemCount() {
        return callsFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView callerNameTextView, callTimeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initiate view
            callerNameTextView = (TextView) itemView.findViewById(R.id.callerName);
            callTimeTextView = (TextView) itemView.findViewById(R.id.callTime);
        }

        public void showCallDetails(Call call) {
            // Attach values for each item
            String callerName = call.getCallerName();
            String callTime = call.getCallTime();
            callerNameTextView.setText(callerName);
            callTimeTextView.setText(callTime);
        }
    }
}
