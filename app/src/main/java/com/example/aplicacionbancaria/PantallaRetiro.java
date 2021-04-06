package com.example.aplicacionbancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionbancaria.database.DBHelper;

public class PantallaRetiro extends AppCompatActivity {
    private EditText etRetirar;
    private TextView tvSaldoActual;
    private Button btnRetirar,btnCancelar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_retiro);
        etRetirar = findViewById(R.id.etRetirar);
        tvSaldoActual = findViewById(R.id.tvSaldoActual);
        btnRetirar = findViewById(R.id.btnRetirar);
        btnCancelar1 = findViewById(R.id.btnCancelar1);

        Bundle datosPan = getIntent().getExtras();
        Long cuenta = datosPan.getLong("cuenta");
        Double saldo = datosPan.getDouble("saldo");
        etRetirar.setText("0");

        tvSaldoActual.setText(String.valueOf(datosPan.getDouble("saldo")));

        btnRetirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double saldo2 = Double.valueOf(etRetirar.getText().toString());

                if(saldo2 < saldo){

                Double saldo_nuevo = saldo-saldo2-5000;
                DBHelper db = new DBHelper(PantallaRetiro.this);
                Bundle datosP = db.retirarSaldo(cuenta,saldo_nuevo);
                Toast.makeText(PantallaRetiro.this, "Retiro Exitoso!", Toast.LENGTH_SHORT).show();
                Bundle datosP2 = db.verificarSaldo(cuenta,saldo_nuevo);
                String cuentaR = datosP2.getString("cuenta");
                Intent i = new Intent(PantallaRetiro.this, PantallaUsuario.class);
                i.putExtra("cuenta", cuentaR);
                i.putExtra("saldo", saldo_nuevo);
                i.putExtra("nombre", datosP2.getString("nombre"));
                i.putExtra("email", datosP2.getString("email"));
                startActivity(i);

                }else{
                    Toast.makeText(PantallaRetiro.this, "Retiro Fallido!", Toast.LENGTH_SHORT).show();
                }




            }
        });

        btnCancelar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaRetiro.this, PantallaUsuario.class);
                startActivity(i);
            }
        });
    }
}