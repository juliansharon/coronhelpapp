package julian.com.coronahelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name,mobile,address,email,pswrd;
    Button submit;
    String num,per_name,add,mail,pswd;
    TextView signin;
    User userprof;

    private FirebaseAuth firebaseAuth;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        myref= FirebaseDatabase.getInstance().getReference();

        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobileno);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        pswrd=findViewById(R.id.pswd);
        submit=findViewById(R.id.submit);
        signin=findViewById(R.id.loginintent);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OTP_Activity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
                //writedata();
            }
        });

    }

    private void signup() {
        num=mobile.getText().toString();
        per_name=name.getText().toString();
        add=address.getText().toString();
        mail=email.getText().toString();
        pswd=pswrd.getText().toString();

        if(TextUtils.isEmpty(per_name)||TextUtils.isEmpty(num)||TextUtils.isEmpty(add)||TextUtils.isEmpty(mail)||TextUtils.isEmpty(pswd))
        {
            Toast.makeText(MainActivity.this,"Please Enter all the details",Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(mail, pswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                writedata();
                                startActivity(new Intent(MainActivity.this,home.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    private void writedata() {
        String user_id=firebaseAuth.getCurrentUser().getUid();
        userprof=new User(mail,num,per_name,add);
        myref.child("Users").child(user_id).setValue(userprof);
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

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}