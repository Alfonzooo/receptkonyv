package com.example.pozsikmt.receptkonyv_v2;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.pozsikmt.receptkonyv_v2.adapter.KedvencAdapter;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptItem;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptKonyvDatabase;



import java.util.List;


public class KedvencActivity extends AppCompatActivity implements KedvencAdapter.KedvencClickListener{

    private RecyclerView recyclerView;
    private KedvencAdapter favadapter;
    private ReceptKonyvDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedvenc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        database = Room.databaseBuilder(
                getApplicationContext(),
                ReceptKonyvDatabase.class,
                "receptkony-list"
        ).build();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.MainRecyclerView);
        favadapter = new KedvencAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(favadapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<ReceptItem>>() {

            @Override
            protected List<ReceptItem> doInBackground(Void... voids) {
                return database.receptItemDao().getfav();
            }

            @Override
            protected void onPostExecute(List<ReceptItem> receptItems) {
                favadapter.update(receptItems);
            }
        }.execute();
    }

    @Override
    public void onItemChanged(final ReceptItem item) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.receptItemDao().update(item);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("ReceptActivity", "ShoppingItem update was successful");
            }
        }.execute();
    }


    @Override
    public void onItemRemoved(final ReceptItem item) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.receptItemDao().deleteItem(item);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("ReceptActivity", "ReceptItem removal was successful");
            }
        }.execute();
    }


}
