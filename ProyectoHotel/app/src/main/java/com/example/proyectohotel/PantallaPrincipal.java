package com.example.proyectohotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PantallaPrincipal extends AppCompatActivity {

    private RadioGroup grupohotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        grupohotel =(RadioGroup) findViewById(R.id.grupohotel);
    }

    public void IrV2(View view){
        int Id = grupohotel.getCheckedRadioButtonId();
        RadioButton seleccion = findViewById(Id);
        String Hotelnombre = seleccion.getText().toString();
        String nombre =getIntent().getStringExtra("Nombre");

        Intent i = new Intent(this, Servicios.class);
        i.putExtra("hotel", Hotelnombre);
        i.putExtra("Nombre", nombre);
        startActivity(i);
    }

}