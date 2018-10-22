package com.apps.quesada.menus_database.AccountActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.quesada.menus_database.HomeActivity;
import com.apps.quesada.menus_database.R;

public class Login extends AppCompatActivity {
EditText e1, e2;
Button b1,b2;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Esto elimina en toolbar de la app
        getSupportActionBar().hide();

        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.password);
        b1 = (Button)findViewById(R.id.btn_login);
        b2 = (Button)findViewById(R.id.btn_signup);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email, password);
                if (Chkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Acceso correcto", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                    //progressDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Correo o password inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
