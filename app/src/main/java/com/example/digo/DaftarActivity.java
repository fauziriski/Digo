package com.example.digo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DaftarActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPasswordRepeat;
    private EditText edtEmail;
    private EditText edtPhone;
    private Button btnBuatAkun;
    private AwesomeValidation mAwesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);




        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        edtUsername = (EditText)findViewById(R.id.userid);
        edtPassword = (EditText)findViewById(R.id.pass1);
        edtPasswordRepeat = (EditText)findViewById(R.id.pass2);
        edtEmail = (EditText)findViewById(R.id.email);
        edtPhone = (EditText)findViewById(R.id.handphone);
        btnBuatAkun = (Button)findViewById(R.id.btnBuatAkun);

        btnBuatAkun.setOnClickListener(this);
        String regexUsername = ".{6,}";
        //Validate Username
        mAwesomeValidation.addValidation(DaftarActivity.this,R.id.userid, regexUsername,R.string.err_username);
        //Validate Email
        mAwesomeValidation.addValidation(DaftarActivity.this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.err_email);

        //Validate phone
        String regexPhone = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";
        mAwesomeValidation.addValidation(DaftarActivity.this,R.id.handphone,regexPhone,R.string.err_phone);

        //Validate Password AND Password Confirmation
        String regexPassword = ".{8,}";
        mAwesomeValidation.addValidation(DaftarActivity.this, R.id.pass1, regexPassword, R.string.err_password);
        // to validate a confirmation field (don't validate any rule other than confirmation on confirmation field)
        mAwesomeValidation.addValidation(DaftarActivity.this, R.id.pass2, R.id.pass1, R.string.err_password_confirmation);
    }

    private void signUp(){
        Log.d(TAG,"Daftar");
        if(!mAwesomeValidation.validate()){
            Log.d(TAG,"VALIDATE GAGAL");
            return;
        }

        final String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        final String username  = edtUsername.getText().toString();
        final String phone = edtPhone.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"Pendaftaran Berhasil");
                        if(task.isSuccessful()){


                            User mUser = new User(username,email,phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(DaftarActivity.this,"Pendaftaran Berhasil",Toast.LENGTH_SHORT).show();

                                        Intent login = new Intent(DaftarActivity.this,LoginActivity.class);
                                        startActivity(login);
                                    }
                                    else{
                                        Toast.makeText(DaftarActivity.this,"Pendaftaran Gagal Dilakukan",Toast.LENGTH_SHORT).show();
                                        Log.d(TAG,task.getException().getMessage());
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(DaftarActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public void goToLoginActivity(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.btnBuatAkun){
            signUp();
        }
    }
}
