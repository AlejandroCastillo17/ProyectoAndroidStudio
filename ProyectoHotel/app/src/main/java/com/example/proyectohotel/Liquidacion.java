package com.example.proyectohotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Liquidacion extends AppCompatActivity {

    private TextView titulouno, etconfi, ethotel, etnombre, etservicios, etcosto;
    private Button btnl, btnc;
    private EditText etnoches;
    private int no =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liquidacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titulouno = (TextView) findViewById(R.id.titulouno);
        btnl = (Button) findViewById(R.id.btnL);
        etnoches = (EditText) findViewById(R.id.etnoches);

        etconfi = (TextView) findViewById(R.id.tvconfi);
        ethotel = (TextView) findViewById(R.id.ethotel);
        etnombre = (TextView) findViewById(R.id.tvnombre);
        etservicios = (TextView) findViewById(R.id.etservicios);
        etcosto = (TextView) findViewById(R.id.etcosto);
        btnc = (Button) findViewById(R.id.btnC);
    }

    public void generarliquidacion(View view){
        String nombre =getIntent().getStringExtra("Nombre");
        String hotel =getIntent().getStringExtra("hotel");
        ArrayList<String> servicios = getIntent().getStringArrayListExtra("Servicios");

        StringBuilder servicioSelecc = new StringBuilder("Servicios seleccionados:\n");
        for (String servicio : servicios) {
            servicioSelecc.append("- ").append(servicio).append("\n");
        }

        int noches =Integer.parseInt(etnoches.getText().toString());
        no =noches;
        double Costototal = Calculoliquidacion(noches, servicios);


        titulouno.setVisibility(TextView.INVISIBLE);
        btnl.setVisibility(TextView.INVISIBLE);
        etnoches.setVisibility(TextView.INVISIBLE);
        etnoches.setText("");

        etconfi.setVisibility(TextView.VISIBLE);
        etnombre.setVisibility(TextView.VISIBLE);
        ethotel.setVisibility(TextView.VISIBLE);
        etservicios.setVisibility(TextView.VISIBLE);
        etcosto.setVisibility(TextView.VISIBLE);
        btnc.setVisibility(TextView.VISIBLE);

        etnombre.setText(nombre);
        ethotel.setText(hotel);
        etservicios.setText(servicioSelecc);
        etcosto.setText(String.valueOf(Costototal));
    }

    public double Calculoliquidacion(int noches, ArrayList<String> servicios) {

        // Calculo del costo dependiente al hotel seleccionado
        String hotel =getIntent().getStringExtra("hotel");
        double costodenoche;

        switch (hotel) {
            case "Hotel Medellín":
                costodenoche = 120000;
                break;
            case "Hotel Bogota":
                costodenoche = 100000;
                break;
            case "Hotel Cali":
                costodenoche = 80000;
                break;
            case "Hotel Santa Marta":
                costodenoche = 90000;
                break;
            default:
                costodenoche = 0;
                break;
        }
        double Costototal = costodenoche * noches;

        // variables estaticas de Tarifas adicionales por servicios
        double wifi = 10000;
        double piscina = 15000;
        double gym = 20000;
        double alimentacion = 35000;

        // calcula el costo de los servicios adicionales seleccionados
        if (servicios.contains("Wi-Fi")) {
            Costototal += wifi * noches;
        }
        if (servicios.contains("Piscina")) {
            Costototal += piscina * noches;
        }
        if (servicios.contains("Gimnasio")) {
            Costototal += gym * noches;
        }
        if (servicios.contains("Alimentacion")){
            Costototal += alimentacion * noches;
        }

        return Costototal;
    }

    public void IrSucces (View view) {

        SharedPreferences ReservacionesHotel = getSharedPreferences("HotelReservation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ReservacionesHotel.edit();
        editor.putString("Nombre", etnombre.getText().toString());
        editor.putString("Hotel", ethotel.getText().toString());
        editor.putString("Servicios", etservicios.getText().toString());
        editor.putInt("Noches", no);
        editor.putFloat("Costo", Float.parseFloat(etcosto.getText().toString()));
        editor.apply();

        titulouno.setVisibility(TextView.VISIBLE);
        btnl.setVisibility(TextView.VISIBLE);
        etnoches.setVisibility(TextView.VISIBLE);

        etconfi.setVisibility(TextView.INVISIBLE);
        etnombre.setVisibility(TextView.INVISIBLE);
        ethotel.setVisibility(TextView.INVISIBLE);
        etservicios.setVisibility(TextView.INVISIBLE);
        etcosto.setVisibility(TextView.INVISIBLE);
        btnc.setVisibility(TextView.INVISIBLE);

        Intent i = new Intent(this, Success.class);
        i.putExtra("nombre", etnombre.getText().toString());
        i.putExtra("hotel", ethotel.getText().toString());
        i.putExtra("servicios", etservicios.getText().toString());
        i.putExtra("noches", etnoches.getText().toString());
        i.putExtra("costo", etcosto.getText().toString());
        startActivity(i);

    }

}