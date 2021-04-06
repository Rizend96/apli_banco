package com.example.aplicacionbancaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionbancaria.database.DBHelper;
import com.example.aplicacionbancaria.usuario.DatosUsuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText etEmail, etPass;
    private TextView tvRespuesta;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Validaciones validar = new Validaciones();
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvRespuesta = findViewById(R.id.respuesta);
        btnEntrar = findViewById(R.id.btnEntrar);

        tvRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Login.this,"",Toast.LENGTH_LONG).show();
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Error, No ha llenado los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (validar.validarEmail(email) && validar.validarPass(pass)) {

                        //ENviar invormacion a otro activity
                        DBHelper db = new DBHelper(Login.this);
                        Bundle datosP = db.verificarUsuario(email,pass);

                        if(datosP.getString("email") != null){
                            if(datosP.getString("status").equals("Activo")){

                                if(datosP.getString("tipo").equals("Usuario")){

                                    Intent i = new Intent(Login.this,PantallaUsuario.class);
                                    i.putExtras(datosP);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Intent i = new Intent(Login.this,PantallaAdmin.class);
                                    i.putExtras(datosP);
                                    startActivity(i);
                                    finish();
                                }
                            }else{
                            Toast.makeText(Login.this, "El usuario esta deshabilitado", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Login.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Login.this, "El email o la contraseña no son válidos.", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }
}
