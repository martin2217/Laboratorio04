package dam.isi.frsf.utn.edu.ar.laboratorio04;

/**
 * Created by Martin on 04/11/2016.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarReservasTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

public class ListaReservasActivity extends AppCompatActivity {

    private Intent intent;
    private ListView listaReservas;
    private List<Reserva> lista;
    private ReservaAdapter reservasAdapter;
    private static Reserva reservaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        lista= new ArrayList<>();
        listaReservas= (ListView ) findViewById(R.id.listaReservas);
        reservaSeleccionada = new Reserva();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /* ELIMINAR, o ver que es interesante de aca para este metodo
        Intent intent = getIntent();
        Boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        if(esBusqueda){
            FormBusqueda fb = (FormBusqueda ) intent.getSerializableExtra("frmBusqueda");
            // faltan datos (huespeedes, permite fumar)
            new BuscarReservasTask(ListaReservasActivity.this).execute(fb);
            tvEstadoBusqueda.setText("Buscando....");
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista=Reserva.getReservasDisponibles();
        }*/
        intent = getIntent();
        lista = MainActivity.getReservas();
        reservasAdapter = new ReservaAdapter(ListaReservasActivity.this,lista);
        listaReservas.setAdapter(reservasAdapter);

        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO:  Eliminarrr

                /*reservaSeleccionada = (Reserva) listaReservas.getItemAtPosition(position);

                Intent i_100 = new Intent(ListaReservasActivity.this,AltaReservaActivity.class);
                startActivity(i_100);*/
            }
        });
    }

    public static Reserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }
}