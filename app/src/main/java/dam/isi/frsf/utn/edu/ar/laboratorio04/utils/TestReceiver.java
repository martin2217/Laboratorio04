package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.net.URI;
import dam.isi.frsf.utn.edu.ar.laboratorio04.ListaReservasActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.MainActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.R;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

public class TestReceiver extends BroadcastReceiver {
    private static final String tag = "TestReceiver";

    @Override
    public void onReceive(Context context, Intent intent){
        if(System.currentTimeMillis() % 3 == 0) {

            AlarmaReserva.cancelarAlarma();

            Reserva reserva = (Reserva) intent.getSerializableExtra("reserva");

            reserva.setConfirmada(true);
            MainActivity.actualizarReserva(reserva);

            NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent i= new Intent(context, ListaReservasActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, reserva.getId(), i, PendingIntent.FLAG_ONE_SHOT);

            // Ringtone de la notificación
            SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(context);
            String ringtone = prefs.getString("ringtone", "content://settings/system/notification_sound");
            Uri UriRingtone = Uri.parse(ringtone);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pi)
                    .setContentTitle("Confirmacion de reserva")
                    .setContentText("La reserva ha sido confirmada!")
                    .setAutoCancel(true)
                    .setSound(UriRingtone);


            notifManager.notify(1, builder.build());
        }
    }
}