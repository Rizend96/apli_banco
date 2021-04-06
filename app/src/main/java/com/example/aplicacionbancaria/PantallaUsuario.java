package com.example.aplicacionbancaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionbancaria.database.DBHelper;
import com.example.aplicacionbancaria.usuario.DatosUsuario;

public class PantallaUsuario extends AppCompatActivity {
    private TextView tvUsuario, tvCorreo, tvCuenta, tvSaldo;
    private Button btnRegresar;
    private CardView Retiros,Depositos,Trasacciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_usuario);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvCuenta = findViewById(R.id.tvCuenta);
        tvSaldo = findViewById(R.id.tvSaldo);
        btnRegresar = findViewById(R.id.btnRegresar);
        Retiros = findViewById(R.id.Retiros);
        Depositos = findViewById(R.id.Depositos);
        Bundle datosP = getIntent().getExtras();

        tvUsuario.setText("Usuario: "+datosP.getString("nombre"));
        tvCorreo.setText("Email: "+datosP.getString("email"));
        tvCuenta.setText(datosP.getString("cuenta"));
        tvSaldo.setText(String.valueOf(datosP.getDouble("saldo")));

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaUsuario.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        Retiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long cuenta = Long.parseLong(tvCuenta.getText().toString());
                Double saldo = Double.parseDouble(tvSaldo.getText().toString());


                if(saldo > 2000){

                    Intent i = new Intent(PantallaUsuario.this, PantallaRetiro.class);
//                    System.out.println(cuenta+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  "+saldo);
                    i.putExtra("saldo",saldo);
                    i.putExtra("cuenta",cuenta);
                    startActivity(i);

                    //Toast.makeText(PantallaUsuario.this, "Retiro realizado", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(PantallaUsuario.this, "No cuenta con saldo suficiente", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Depositos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long cuenta = Long.parseLong(tvCuenta.getText().toString());
                Double saldo = Double.parseDouble(tvSaldo.getText().toString());


                if(saldo > 5000){

                    Intent i = new Intent(PantallaUsuario.this, PantallaDeposito.class);
                    System.out.println(cuenta+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  "+saldo);
                    i.putExtra("saldo",saldo);
                    i.putExtra("cuenta",cuenta);
                    startActivity(i);

                    //Toast.makeText(PantallaUsuario.this, "Retiro realizado", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(PantallaUsuario.this, "No cuenta con saldo suficiente", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}