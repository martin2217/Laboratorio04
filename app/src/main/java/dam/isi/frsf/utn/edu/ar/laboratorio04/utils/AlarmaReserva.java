package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.*;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;


public class AlarmaReserva {

    static Context contexto;
    static Reserva reserva;

    public AlarmaReserva (Context context, Reserva res){

        contexto=context;
        reserva=res;

        Intent intent = new Intent(context, TestReceiver.class);
        intent.putExtra("reserva", reserva);


        PendingIntent pi = PendingIntent.getBroadcast(context,reserva.getId(),intent,0);

        // Programar la alarma
        AlarmManager am =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, 3000, 3000, pi);
    }

    public static void cancelarAlarma(){

        // Posibilidad de alarma infinita
        // TODO: revisar como cancelarla correctamente
        if (contexto !=null && reserva != null) {
            Intent intent = new Intent(contexto, TestReceiver.class);

            PendingIntent pi = PendingIntent.getBroadcast(contexto, reserva.getId(), intent, 0);
            // Cancelar la alarma
            AlarmManager am = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
            am.cancel(pi);
        }
    }

}