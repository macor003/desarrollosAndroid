package io.futurestud.retrofit1.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.futurestud.retrofit1.R;
import io.futurestud.retrofit1.api.model.GitHubRepo;

/**
 * Created by norman on 12/26/16.
 */

public class GitHubRepoAdapter extends BaseAdapter {

    private Context context;
    private List<GitHubRepo> values;
    LayoutInflater infilter;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        //super(context, R.layout.list_item_pagination);

        this.context = context;
        this.values = values;
        infilter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);
        TextView text2 = (TextView) row.findViewById(R.id.list_item_pagination_text2);

        GitHubRepo item = values.get(position);
        String message = item.getName();
        String description = item.getDescription();
        text2.setText(description);
        textView.setText(message);

        return row;
    }
}
