package dam.isi.frsf.utn.edu.ar.laboratorio04;

/**
 * Created by Martin on 04/11/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ReservaAdapter extends ArrayAdapter<Reserva> {
    private LayoutInflater inflater;
    private Context contexto;

    public ReservaAdapter(Context contexto, List<Reserva> items) {
        super(contexto, R.layout.fila_departamento, items);
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View row = convertView;
        if (row == null) row = inflater.inflate(R.layout.fila_reserva, parent, false);
        TextView txtCiudad = (TextView) row.findViewById(R.id.ciudadR);
        txtCiudad.setText(this.getItem(position).getDepartamento().getCiudad().getNombre());
        TextView txtDescripcion = (TextView) row.findViewById(R.id.descripcionR);
        txtDescripcion.setText(this.getItem(position).getDepartamento().getDescripcion());
        TextView txtPrecio = (TextView) row.findViewById(R.id.precioR);
        txtPrecio.setText("$" + (df.format(this.getItem(position).getAlojamiento().getPrecio())));
        TextView txtConfirmar = (TextView) row.findViewById(R.id.confirmar);
        boolean auxBoolean = false;
        if(this.getItem(position).getConfirmada()!=null) auxBoolean=this.getItem(position).getConfirmada();
        String auxConfirmar = "Pendiente.";
        if(auxBoolean) auxConfirmar="Confirmada.";
        txtConfirmar.setText("Estado: " + auxConfirmar);
        return (row);

    }
}