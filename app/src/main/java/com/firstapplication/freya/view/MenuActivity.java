package com.firstapplication.freya.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firstapplication.freya.R;
import com.firstapplication.freya.view.signin.activity.SingInActivity;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_go_out) {
            Intent intent = new Intent(this, SingInActivity.class);
            intent.putExtra("key", -1);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}