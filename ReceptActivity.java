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

import com.example.pozsikmt.receptkonyv_v2.adapter.ReceptAdapter;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptItem;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptKonyvDatabase;
import com.example.pozsikmt.receptkonyv_v2.fragment.NewReceptItemDialogFragment;


import java.util.List;


public class ReceptActivity extends AppCompatActivity implements NewReceptItemDialogFragment.NewReceptItemDialogListener,
        ReceptAdapter.ReceptItemClickListener{

    private RecyclerView recyclerView;
    private ReceptAdapter adapter;
    private ReceptKonyvDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewReceptItemDialogFragment().show(getSupportFragmentManager(), NewReceptItemDialogFragment.TAG);
            }
        });

        database = Room.databaseBuilder(
                getApplicationContext(),
                ReceptKonyvDatabase.class,
                "receptkony-list"
        ).build();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.MainRecyclerView);
        adapter = new ReceptAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<ReceptItem>>() {

            @Override
            protected List<ReceptItem> doInBackground(Void... voids) {
                return database.receptItemDao().getAll();
            }

            @Override
            protected void onPostExecute(List<ReceptItem> receptItems) {
                adapter.update(receptItems);
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

    @Override
    public void onReceptItemCreated(final ReceptItem newItem) {
        new AsyncTask<Void, Void, ReceptItem>() {

            @Override
            protected ReceptItem doInBackground(Void... voids) {
                newItem.rec_id = database.receptItemDao().insert(newItem);
                return newItem;
            }

            @Override
            protected void onPostExecute(ReceptItem receptItem) {
                adapter.addItem(receptItem);
            }
        }.execute();
    }



}
