package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

/**
 * Created by Martin on 04/11/2016.
 */


import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.ListaDepartamentosActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.MainActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

// ELIMINARRRRR CLASEEEEEEEEEE ... NO USADA

public class BuscarReservasTask extends AsyncTask<FormBusqueda,Integer,List<Reserva>> {

    private BusquedaFinalizadaListener<Reserva> listener;

    public BuscarReservasTask(BusquedaFinalizadaListener<Reserva> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Reserva> reservas) {
        listener.busquedaFinalizada(reservas);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("reserva "+values[0]);


    }

    @Override
    protected List<Reserva> doInBackground(FormBusqueda... busqueda) {
        List<Reserva> todos = MainActivity.getReservas();
        return todos;
    }
}
