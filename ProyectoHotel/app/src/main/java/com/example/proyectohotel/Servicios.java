package com.example.proyectohotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Servicios extends AppCompatActivity {

    private CheckBox Wifi, Alimentacion, Gym, Piscina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_servicios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Wifi = findViewById(R.id.Wifi);
        Alimentacion = findViewById(R.id.Alimentacion);
        Gym = findViewById(R.id.Gym);
        Piscina = findViewById(R.id.Piscina);

    }

    public void IrV3(View view){

        String nombre =getIntent().getStringExtra("Nombre");
        String hotel =getIntent().getStringExtra("hotel");

        ArrayList<String> servicios = new ArrayList<>();
        if (Wifi.isChecked()) servicios.add("Wi-Fi");
        if (Piscina.isChecked()) servicios.add("Piscina");
        if (Gym.isChecked()) servicios.add("Gimnasio");
        if (Alimentacion.isChecked()) servicios.add("Alimentacion");

        Intent i = new Intent(this, Liquidacion.class);
        i.putExtra("hotel", hotel);
        i.putExtra("Nombre", nombre);
        i.putStringArrayListExtra("Servicios", servicios);
        startActivity(i);
    }
}