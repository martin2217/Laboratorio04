package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.AlarmaReserva;

public class AltaReservaActivity extends AppCompatActivity {

    private Button confirmarReserva;
    private EditText fechaInicio;
    private EditText fechaFin;
    private Intent intent;
    private Departamento departamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        fechaInicio = (EditText) findViewById(R.id.txtFechaInicio);
        fechaFin = (EditText) findViewById(R.id.txtFechaFin);

        departamento=(Departamento) getIntent().getExtras().get("departamento");

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
                    // TODO: Usar calendar
                    fechaInicioDate = formato.parse(stringFechaInicio);
                    fechaFinDate = formato.parse(stringFechaFin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*Integer idAux = 0;
                if(departamento.getReservas()!=null){
                    idAux = departamento.getReservas().get(departamento.getReservas().size()-1).getId()+1;
                }*/

                Reserva reservaNueva = new Reserva(MainActivity.getReservas().size()+1, fechaInicioDate, fechaFinDate, departamento);

                MainActivity.addReserva(reservaNueva);

                Toast.makeText(v.getContext(), "Reserva dada de alta con éxito (pendiente)." , Toast.LENGTH_SHORT).show();

                new AlarmaReserva(AltaReservaActivity.this, reservaNueva);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        intent = getIntent();
    }



}
