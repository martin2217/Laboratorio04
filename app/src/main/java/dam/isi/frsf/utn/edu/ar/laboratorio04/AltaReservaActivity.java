package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    private Button confirmarReserva;
    private EditText fechaInicio;
    private EditText fechaFin;
    private Reserva reserva;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        fechaInicio = (EditText) findViewById(R.id.txtFechaInicio);
        fechaFin = (EditText) findViewById(R.id.txtFechaFin);
        confirmarReserva = (Button) findViewById(R.id.btnConfirmarReserva);
        confirmarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaInicioDate = new Date();
                Date fechaFinDate = new Date();

                String stringAux = fechaInicio.getText().toString();
                String stringFechaInicio = stringAux.substring(0,2) + "/" +
                        stringAux.substring(2,4) + "/" + stringAux.substring(4,8);

                stringAux = fechaFin.getText().toString();
                String stringFechaFin = stringAux.substring(0,2) + "/" +
                        stringAux.substring(2,4) + "/" + stringAux.substring(4,8);

                try {
                    fechaInicioDate = formato.parse(stringFechaInicio);
                    fechaFinDate = formato.parse(stringFechaFin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*Integer idAux = 0;
                if(departamento.getReservas()!=null){
                    idAux = departamento.getReservas().get(departamento.getReservas().size()-1).getId()+1;
                }

                Reserva reserva = new Reserva(idAux,fechaInicioDate,fechaFinDate,departamento);*/

                reserva.setFechaInicio(fechaInicioDate);
                reserva.setFechaFin(fechaFinDate);
                reserva.setConfirmada(true);

                //departamento.getReservas().add(reserva);

                Toast.makeText(v.getContext(), "Reserva dada de alta con éxito." , Toast.LENGTH_SHORT).show();

                //new AlarmaReserva(this, reserva,usuario);

                //Intent i = new Intent(this,MainActivity.class);
                //i.putExtra("usuario",usuario);
                //startActivity(i);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        intent = getIntent();
        reserva = ListaReservasActivity.getReservaSeleccionada();
    }



}
