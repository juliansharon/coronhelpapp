package julian.com.coronahelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setSelectedItemId(R.id.help);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.food:
                        startActivity(new Intent(getApplicationContext(),food.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.fund:
                        startActivity(new Intent(getApplicationContext(),fund.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.help:
                        return true;
                    case R.id.vehicle:
                        startActivity(new Intent(getApplicationContext(),pass.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        return;
    }
}
