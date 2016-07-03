package com.davidbejar.utplnotaexamen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    //Inicialización de variables


    int aciertos = 0;
    int preguntas = 40;
    int errores = 10;
    int n = 0;
    double porcentaje = 0;
    double calificacion = 0;
    private Object item;
    public TextView acerca_de;
    private ImageButton utplImagen;

    public final String UTPL_URL = "www.utpl.edu.ec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Cast of the UI Objects
        final Button boton = (Button) findViewById(R.id.button) ;
        final EditText TextBox = (EditText) findViewById(R.id.editText);
        final EditText TextBox1 = (EditText) findViewById(R.id.editText1);
        final EditText TextBox2 = (EditText) findViewById(R.id.editText2);
        final EditText TextBox3 = (EditText) findViewById(R.id.editText3);
        final TextView TextView5 = (TextView) findViewById(R.id.textView5);



        /*
         * Set the in and out animations. Using the fade_in/out animations
         * provided by the framework.
         */
        final Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        final Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        // END_INCLUDE(setup)







        acerca_de = (TextView) findViewById(R.id.texto_autor);
        utplImagen = (ImageButton) findViewById(R.id.imageView);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //valida los textbox si están vacíos con 0
                if (String.valueOf(TextBox.getText().toString()).equals("")) {
                    TextBox.setText("0");
                }
                if (String.valueOf(TextBox1.getText().toString()).equals("")) {
                    TextBox1.setText("0");
                }
                if (String.valueOf(TextBox2.getText().toString()).equals("")) {
                    TextBox2.setText("0");
                }
                if (String.valueOf(TextBox3.getText().toString()).equals("")) {
                    TextBox3.setText("0");
                }

                //Asigna los valores de la caja de texto
                preguntas = Integer.parseInt(TextBox.getText().toString());
                aciertos = Integer.parseInt(TextBox1.getText().toString());
                errores = Integer.parseInt(TextBox2.getText().toString());
                n = Integer.parseInt(TextBox3.getText().toString());

                //Calcula la cantidad de preguntas acertadas
                porcentaje = aciertos - (errores / (n - 1));

                //Calcula por medio de 2 reglas de 3 el puntaje sobre 14
                calificacion = (((porcentaje * 100) / preguntas) * 14) / 100;

                //Imprime en el label la calificación final
                if (calificacion < 8){
                    TextView5.setTextColor(getResources().getColor(R.color.rojo));
                    TextView5.setText(String.valueOf(calificacion) + "\nsupletorio");
                    TextView5.startAnimation(in);
                } else{
                    TextView5.setText(String.valueOf(calificacion)+"");
                    TextView5.setTextColor(getResources().getColor(R.color.negro));
                    TextView5.startAnimation(in);
                }
            }
        });
    };


    //Al dar click en el menu Acerca de de la barra de acción
    public void clickAcercaDe(MenuItem item){
        acercaDe();
    }

    //Este método muestra el diáologo de acerca de...
    public void acercaDe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.acerca_de);
        } else {
            builder.setTitle(getText(R.string.acerca_de));
            builder.setMessage((getText(R.string.creado_por))+"\nVersion 2.1"+"\nmail: davidbejar2009@gmail.com");

        }
        //Crea el boton de ok para el diálogo y crea un clickListener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        //Estas líneas crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }




    public void clickFormula(MenuItem item){
        formula();
    }

    //Este método muestra el diáologo de las instrucciones con la formula...
    public void formula(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.instrucciones);
        } else {
            builder.setTitle(getText(R.string.titulo_formula));
            builder.setMessage(   (getText(R.string.formula))+"\n" + (getText(R.string.explicacion))     );

        }
        //Crea el boton de ok para el diálogo y crea un clickListener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        //Estas líneas crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //Este método maneja el click en el botón menu del hardware del equipo
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            acercaDe();
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

