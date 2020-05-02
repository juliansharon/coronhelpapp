package julian.com.coronahelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText name,mobile,address,people;
    Button submit;
    String num,per_name,add,locals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobileno);
        address=findViewById(R.id.address);
        people=findViewById(R.id.email);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=mobile.getText().toString();
                per_name=name.getText().toString();
                add=address.getText().toString();
                locals=people.getText().toString();

                if(TextUtils.isEmpty(per_name)||TextUtils.isEmpty(num)||TextUtils.isEmpty(add)||TextUtils.isEmpty(locals))
                {
                    Toast.makeText(MainActivity.this,"Please Enter all the details",Toast.LENGTH_SHORT).show();
                }else if(num.length()>10||num.length()<10){
                    Toast.makeText(MainActivity.this,"Please Enter valid number",Toast.LENGTH_SHORT).show();
                }else{
                    Intent in = new Intent(MainActivity.this,OTP_Activity.class);
                    in.putExtra("num",num);
                    startActivity(in);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent in = new Intent(MainActivity.this,home.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
        }
    }
}