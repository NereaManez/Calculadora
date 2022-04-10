package es.ieslavereda.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b_aceptar, b_cancelar;
    private EditText ed_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_aceptar = findViewById(R.id.boton_aceptar);
        b_cancelar = findViewById(R.id.boton_cancelar);
        ed_nombre = findViewById(R.id.nombre);

        b_aceptar.setOnClickListener(this);
        b_cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_aceptar:
                startActivity(new Intent(MainActivity.this, Calculadora.class));
                finish();
                break;

            case R.id.boton_cancelar:
                ed_nombre.setText("");
                break;
        }
    }
}