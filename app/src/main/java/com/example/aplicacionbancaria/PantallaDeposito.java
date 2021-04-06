package com.example.aplicacionbancaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionbancaria.database.DBHelper;

public class PantallaDeposito extends AppCompatActivity {
    private EditText etDepositar;
    private TextView tvSaldoActual2;
    private Button btnDepositar,btnCancelar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_deposito);
        etDepositar = findViewById(R.id.etDepositar);
//        tvSaldoActual2 = findViewById(R.id.tvSaldoActual2);
        btnDepositar = findViewById(R.id.btnDepositar);
        btnCancelar2 = findViewById(R.id.btnCancelar2);

        Bundle datosPan = getIntent().getExtras();
        Long cuenta = datosPan.getLong("cuenta");
        Double saldo = datosPan.getDouble("saldo");
        //etRetirar.setText("0");

//        tvSaldoActual2.setText(String.valueOf(datosPan.getDouble("saldo")));

        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double saldo2 = Double.valueOf(etDepositar.getText().toString());
                Double saldo_nuevo = saldo+saldo2-2000;
                DBHelper db = new DBHelper(PantallaDeposito.this);
                Bundle datosP = db.retirarSaldo(cuenta,saldo_nuevo);
                Toast.makeText(PantallaDeposito.this, "Retiro Exitoso!", Toast.LENGTH_SHORT).show();
                Bundle datosP2 = db.verificarSaldo(cuenta,saldo_nuevo);
                String cuentaR = datosP2.getString("cuenta");
                Intent i = new Intent(PantallaDeposito.this, PantallaUsuario.class);
                i.putExtra("cuenta", cuentaR);
                i.putExtra("saldo", saldo_nuevo);
                i.putExtra("nombre", datosP2.getString("nombre"));
                i.putExtra("email", datosP2.getString("email"));
                startActivity(i);


            }
        });


        btnCancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaDeposito.this, PantallaUsuario.class);
                startActivity(i);
            }
        });
    }
}