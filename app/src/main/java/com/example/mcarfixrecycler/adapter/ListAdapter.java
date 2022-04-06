package com.example.mcarfixrecycler.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcarfixrecycler.MainActivity;
import com.example.mcarfixrecycler.R;
import com.example.mcarfixrecycler.WebViewActivity;
import com.example.mcarfixrecycler.model.ListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {

    private Context context;
    private WebView webView;
    private List<ListData> dataList;
    private TextView textView;

    public ListAdapter(List<ListData> dataList, Context context) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view, context, dataList);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

//        Picasso.get().load(dataList.get(position).getFlag());
        holder.mCountryName.setText(dataList.get(position).getCountryname());
        holder.mRegulator.setText(dataList.get(position).getRegulatorname());
        holder.mLink.setText(dataList.get(position).getRegulatorweb());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        ImageView mFlag;
        TextView mCountryName;
        TextView mRegulator;
        TextView mLink;
        Context context;
        List<ListData> dataList;

        public ViewHolder(@NonNull View itemView, Context context, List<ListData> dataList) {
            super(itemView);

//            mFlag = itemView.findViewById(R.id.flagImage);
            mCountryName = itemView.findViewById(R.id.countryName);
            mRegulator = itemView.findViewById(R.id.regulatorName);
            mLink = itemView.findViewById(R.id.webLink);
            itemView.setOnClickListener(this);
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public void onClick(View view) {
            if (view != null) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("URL_LINK", dataList.get(getAdapterPosition()).getRegulatorweb());
                context.startActivity(intent);
            } else {

                Toast.makeText(context.getApplicationContext(), "Not working", Toast.LENGTH_LONG).show();
            }

        }
    }

    //    Search Implementation Adapter


    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ListData> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataList);
            } else {

                for (ListData data: dataList) {
                    if (data.getCountryname().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataList.clear();
            dataList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
