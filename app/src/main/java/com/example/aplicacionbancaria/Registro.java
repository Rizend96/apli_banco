package com.example.aplicacionbancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacionbancaria.database.DBHelper;
import com.example.aplicacionbancaria.usuario.DatosUsuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    private EditText etNombre, etID, etEmailR, etPassR;
    private Button btnRegistrar, btnVolver;
    Validaciones validar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        db = new DBHelper(this);
        DatosUsuario usuario = new DatosUsuario();
        Validaciones validar = new Validaciones();
        etNombre = findViewById(R.id.etNombre);
        etID = findViewById(R.id.etID);
        etEmailR = findViewById(R.id.etEmailR);
        etPassR = findViewById(R.id.etPassR);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVolver = findViewById(R.id.btnVolver);

        Intent intent = new Intent(Registro.this, Login.class);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String documento = etID.getText().toString();
                String email = etEmailR.getText().toString();
                String pass = etPassR.getText().toString();

                if (nombre.isEmpty() || documento.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Registro.this, "Error, No ha llenado los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (validar.validarEmail(email) && validar.validarPass(pass)) {

                        usuario.setNombres(nombre);
                        usuario.setDocumento(documento);
                        usuario.setEmail(email);
                        usuario.setPass(pass);
                        double saldo = 1000000;
                        int cuenta = (int)(1000000000 * Math.random());
                        usuario.setCuenta(cuenta);
                        usuario.setSaldo(saldo);
                        usuario.setStatus("Activo");
                        usuario.setTipo("Usuario");

                        Boolean check = db.registrar(usuario);

                        if (check == true){
                            Toast.makeText(Registro.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Registro.this,"Error, no se pudo registrar",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Registro.this, "El email y contraseña ingresados no son validos.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

    }


}