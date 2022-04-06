package com.example.mcarfixrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.example.mcarfixrecycler.adapter.ListAdapter;
import com.example.mcarfixrecycler.api.RetrofitClient;
import com.example.mcarfixrecycler.model.ListData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ListAdapter adapter;
    List<ListData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerRegulatorList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        Initializing adpter class

        adapter = new ListAdapter(dataList, MainActivity.this);
        recyclerView.setAdapter(adapter);
        fetchData();

    }

    private void fetchData() {

        RetrofitClient.getRetrofitClient().getRegulators().enqueue(new Callback<List<ListData>>() {
            @Override
            public void onResponse(Call<List<ListData>> call, Response<List<ListData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.v("onResponse", "Data is fetched");
                    Toast.makeText(MainActivity.this, "Data is being Fetched", Toast.LENGTH_LONG).show();
                    dataList.addAll(response.body());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<ListData>> call, Throwable t) {
                Log.v("TAG", "Not working");
                Toast.makeText(MainActivity.this, "Failed to get Regulators", Toast.LENGTH_LONG).show();
            }
        });

    }

//    Search widget/Implementation


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}