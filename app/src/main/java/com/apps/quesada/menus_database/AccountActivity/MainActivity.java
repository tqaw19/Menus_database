package com.apps.quesada.menus_database.AccountActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.quesada.menus_database.R;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1, e2, e3, e4, e5;
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esto elimina el toolbar de la app
        getSupportActionBar().hide();

        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.nombres);
        e2 = (EditText)findViewById(R.id.apellidos);
        e3 = (EditText)findViewById(R.id.email);
        e4 = (EditText)findViewById(R.id.password);
        e5 = (EditText)findViewById(R.id.confirm_password);
        b1 = (Button)findViewById(R.id.btn_register);
        b2 = (Button)findViewById(R.id.btn_ir);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v){
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s4.equals(s5)) {
                        Boolean chkemail = db.chkemail(s3);
                        if (chkemail == true) {
                            Boolean insert = db.insert(s1, s2, s3, s4);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Este correo ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
