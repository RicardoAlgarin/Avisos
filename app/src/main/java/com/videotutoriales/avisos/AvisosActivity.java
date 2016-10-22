package com.videotutoriales.avisos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AvisosActivity extends AppCompatActivity {

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos); // Actividad principal, que contiene content_avisos que a su vez tiene avisos_row
        mListView = (ListView) findViewById(R.id.avisos_list_view);//avisos_list_view esta en content_avisos

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //context
                this,
                // layout (view)
                R.layout.avisos_row,
                // row(view) elementos del layout a utilizar, o llamado tambien campo
                R.id.row_test,
                new String[]{"Primera sesion","Segunda Sesion","Tercera Sesion","Cuarta Sesion"});

        mListView.setAdapter(arrayAdapter);


    }

}
