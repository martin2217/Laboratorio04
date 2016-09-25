package dam.isi.frsf.utn.edu.ar.lab01c2016;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textEmail;
    private TextView textCuit;
    private TextView textImporte;
    private TextView textDays;
    private TextView textCalculado;
    private TextView textSalida;
    private SeekBar seekControl;
    private String[] montos;
    private String[] tasas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        montos = res.getStringArray(R.array.montos_array);
        tasas = res.getStringArray(R.array.tasas_array);

        textEmail = (TextView) findViewById(R.id.text_email);
        textCuit = (TextView) findViewById(R.id.text_cuit);
        textImporte = (TextView) findViewById(R.id.text_importe);
        textDays = (TextView) findViewById(R.id.text_days);
        textCalculado = (TextView) findViewById(R.id.text_calculado);
        textSalida = (TextView) findViewById(R.id.text_salida);
        seekControl = (SeekBar) findViewById(R.id.seek_bar);



        seekControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Double capital = (double) 0;
            int days = 0;

            public void onProgressChanged(SeekBar seekBar, int progreso, boolean fromUser){
                days = progreso;
                textDays.setText(days + " días.");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                String auxImporte =  textImporte.getText().toString();
                if(auxImporte.length()>0) capital = Double.parseDouble(auxImporte);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                textDays.setText(days + " días.");

                Double tasaInteres = (double) 0;

                if(capital>Float.parseFloat(montos[0])){ // > al mínimo
                    if(days<30){

                        if(capital<Double.parseDouble(montos[1])){
                            tasaInteres = Double.parseDouble(tasas[0]);
                        }
                        else if(capital<Double.parseDouble(montos[2])){
                            tasaInteres = Double.parseDouble(tasas[2]);
                        }
                        else {
                            tasaInteres = Double.parseDouble(tasas[4]);
                        }
                    }
                    else{

                        if(capital<Double.parseDouble(montos[1])){
                            tasaInteres = Double.parseDouble(tasas[1]);
                        }
                        else if(capital<Double.parseDouble(montos[2])){
                            tasaInteres = Double.parseDouble(tasas[3]);
                        }
                        else {
                            tasaInteres = Double.parseDouble(tasas[5]);
                        }
                    }
                }

                Double aux = (double)1+(tasaInteres/100);
                Double aux2 = Math.pow(aux,((double)days/360));

                Double resultado = capital + capital*(aux2-1);
                resultado=(double)((int)(resultado*100)/(double)100);

                textCalculado.setText(resultado.toString());
            }
        });

        final Button button = (Button) findViewById(R.id.btn_OK);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                textSalida.setTextColor(getResources().getColor(R.color.color_ok));
                if("0.0".equals(textCalculado.getText().toString())==false){
                    textSalida.setText("Plazo fijo realizado. Recibirá $" + textCalculado.getText() + ".");
                }
                else{
                    textSalida.setTextColor(getResources().getColor(R.color.color_error));
                    textSalida.setText("Error, complete los campos y coloque un importe mayor a $0.");
                }




            }
        });
    }
}
