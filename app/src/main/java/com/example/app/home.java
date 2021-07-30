package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new f1());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {  //It is used for handling item selection. //IIly, for ContextMenu change onContextItemSelected
        switch (item.getItemId()) {
            case R.id.a: /*Perform function*/  return true;
            default:    return super.onOptionsItemSelected(item);
        }
    }





    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nf1: fragment = new f1(); break;
            case R.id.nf2: fragment = new f2(); break;
            case R.id.nf3: fragment = new f3(); break;
            case R.id.nf4: fragment = new f4(); break;
            case R.id.nf5: fragment = new f5(); break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}