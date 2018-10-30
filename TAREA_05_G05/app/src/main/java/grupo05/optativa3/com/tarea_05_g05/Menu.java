package grupo05.optativa3.com.tarea_05_g05;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity
{
    TextView tvInfoEnviada;//nick de usuario
    TextView tvNombresCompletos;//nombres de usuario
    TextView tvEdad;//edad de usuario
    TextView tvEmail;//email de usuario
    ImageView tvImage;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        tvInfoEnviada = (TextView) findViewById(R.id.id_nombre2);
        tvNombresCompletos=(TextView)findViewById(R.id.txt_nombresCompletos);
        tvEdad=(TextView)findViewById(R.id.txt_edad);
        tvEmail=(TextView)findViewById(R.id.txt_email);
        tvImage=(ImageView)findViewById(R.id.img_usuario);

        Bundle bdl = getIntent().getExtras();
        String datos = bdl.get("info").toString();
        //REColocacion del formulario dependiendo del tipo de usuario
        tvInfoEnviada.setText(datos);
        if( datos.equals("alexis") ){
            tvImage.setImageDrawable(ContextCompat.getDrawable(Menu.this,R.drawable.ac));
            tvNombresCompletos.setText("Alexis Chalco Loya");
            tvEdad.setText("50");
            tvEmail.setText("alexischalco@hotmail.com");

        }else if(datos.equals("deysi")){
            tvImage.setImageDrawable(ContextCompat.getDrawable(Menu.this,R.drawable.df));
            tvNombresCompletos.setText("Deysi Fernandez Yacelga");
            tvEdad.setText("23");
            tvEmail.setText("dfernandez@hotmail.com");



        }else if (datos.equals("jhonathan")){
            tvImage.setImageDrawable(ContextCompat.getDrawable(Menu.this,R.drawable.jp));
            tvNombresCompletos.setText("Jhonathan Paucar");
            tvEdad.setText("19");
            tvEmail.setText("jmpaucarm@hotmail.com");

        }else{

            tvNombresCompletos.setText("admin");
            tvEdad.setText("0");
            tvEmail.setText("admin@bussienes.com");



        }







    }
}
