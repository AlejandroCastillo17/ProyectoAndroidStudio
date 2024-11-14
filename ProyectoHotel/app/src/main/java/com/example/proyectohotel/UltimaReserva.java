package com.example.proyectohotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UltimaReserva extends AppCompatActivity {

    private TextView tvU, tvH, tvS, tvN, tvC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ultima_reserva);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvU = (TextView) findViewById(R.id.tvU);
        tvH = (TextView) findViewById(R.id.tvH);
        tvS = (TextView) findViewById(R.id.tvS);
        tvN = (TextView) findViewById(R.id.tvN);
        tvC = (TextView) findViewById(R.id.tvC);

        SharedPreferences ReservacionesHotel = getSharedPreferences("HotelReservation", Context.MODE_PRIVATE);
        String usuario = ReservacionesHotel.getString("Nombre", " Usuario No Encontrado ");
        String hotel = ReservacionesHotel.getString("Hotel", " Usuario No Encontrado ");
        String servicios = ReservacionesHotel.getString("Servicios", " Usuario No Encontrado ");
        int noches = ReservacionesHotel.getInt("Noches", 0);
        Float costo = ReservacionesHotel.getFloat("Costo", 0);

        tvU.setText(usuario);
        tvH.setText(hotel);
        tvS.setText(servicios);
        tvN.setText(String.valueOf(noches));
        tvC.setText(String.valueOf(costo));
    }

    public void VolverMenu (View view) {
        Intent i = new Intent(this, MenuP.class);
        startActivity(i);
    }
}