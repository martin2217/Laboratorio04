package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.ListaDepartamentosActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
        listener.busquedaFinalizada(departamentos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);


    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();

        int contador = 0;
        final Ciudad ciudadBuscada = busqueda[0].getCiudad();

        int huespedes=0;
        if(busqueda[0].getHuespedes()==null) huespedes=0;
        else huespedes=busqueda[0].getHuespedes();

        // TODO Como se manejan los valores nulos?
        //double min = busqueda[0].getPrecioMinimo();
        //double max = busqueda[0].getPrecioMaximo();

        // No tenido en cuenta..
        boolean permiteFumar =  busqueda[0].getPermiteFumar();

        // Expresiones lambda! ... android no soporta java 8? (filter)

        for(Departamento depto: todos){
            if(depto.getCiudad().getNombre().equals(ciudadBuscada.getNombre()) && (busqueda[0].getHuespedes()==null || depto.getCapacidadMaxima()>=huespedes)
                && (busqueda[0].getPrecioMaximo()==null || busqueda[0].getPrecioMaximo()>=depto.getPrecio())
                && (busqueda[0].getPrecioMinimo()==null || busqueda[0].getPrecioMinimo()<=depto.getPrecio())){
                resultado.add(depto);
            }
        }

        return resultado;
    }
}
