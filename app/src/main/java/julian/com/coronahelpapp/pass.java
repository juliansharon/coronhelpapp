package julian.com.coronahelpapp;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.WriterException;

public class pass extends AppCompatActivity {
    EditText outname,outpurpose,outdate;
    Button requestpass,generateqr;
    ImageView qrcode;
    String TAG="QR CODE GENRATOR";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        outname=findViewById(R.id.passname);
        outdate=findViewById(R.id.passdate);
        outpurpose=findViewById(R.id.passpurspose);
        requestpass=findViewById(R.id.requestpass);
        generateqr=findViewById(R.id.qrcode);
        qrcode=findViewById(R.id.qrimage);


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setSelectedItemId(R.id.vehicle);
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
                        startActivity(new Intent(getApplicationContext(),help.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.vehicle:
                        return true;
                }
                return false;
            }
        });
        generateqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValue = outname.getText().toString().trim()+"\n"+outpurpose.getText().toString().trim()+"\n"+outdate.getText().toString().trim();
                if (inputValue.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(
                            inputValue, null,
                            QRGContents.Type.TEXT,
                            smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrcode.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                } else {
                    outdate.setError("Required");
                    outname.setError("Required");
                    outpurpose.setError("Required");
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        return;
    }
}
