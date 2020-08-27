package com.example.back;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.back.fireModel.Fmodel;
import com.example.back.popup.Popup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private static final String TAG="Login";
    FirebaseDatabase fb;
    Fmodel fmodel;
    DatabaseReference databaseReference;
    EditText email, password;
    String vemail, vpass;
    private FirebaseAuth mAuth;
    ProgressDialog p;
    Button login;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Button kill_login = findViewById(R.id.kill_login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        p=new ProgressDialog(Login.this);
        mAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Main.class));
            }
        });
        //auth listener
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthChanged:signed_in:" + user.getUid());
                }else{
                    Log.d(TAG,"onAuthChanged:signed_out:");
                }
            }
        };

        //go back to login activity
        kill_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
    }


    //call to custom popup with custom title and message
    public void popup(String t, String m) {
        Popup popup = new Popup(t, m);
        popup.show(getSupportFragmentManager(), "no tag");
    }

    //updateui method is removed for simplicity


    //called by the onclick function of the log in button
    public void login() {

        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            popup("Error", "make sure all fileds are filled in first");
        } else {
            p.setTitle("loading ...");
            p.setMessage("checking data");
            p.setCanceledOnTouchOutside(false);
            p.show();
            //get string from input fields
            vemail = email.getText().toString();
            vpass = password.getText().toString();
            //connect to firebase

            // email and password sign in with firebase auth
            mAuth.signInWithEmailAndPassword(vemail, vpass)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                p.dismiss();
                                Log.d(TAG, "signInWithEmail:success");
                                startActivity(new Intent(Login.this,Home.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                p.dismiss();
                                popup("ooops...","something went wrong");
                                startActivity(new Intent(Login.this,Home.class));


                            }

                            // ...
                        }
                    });


        }

    }
}
