package com.bp.pruebatts;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String EDIT_TEXT_SPEACH_CONTENT = "EDIT_TEXT_SPEACH_CONTENT";

    private EditText ttsEditText;
    private Button ttsButton;

    private TextToSpeech tts;

    //Permite iniciar las propiedades del TTS
    private TextToSpeech.OnInitListener ttsListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) { //Si el TTS está disponible...
                tts.setLanguage(Locale.getDefault()); //Establece el idioma por defecto del dispositivo.
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, ttsListener);
        ttsEditText = (EditText) findViewById(R.id.tts);
        ttsButton = (Button) findViewById(R.id.ttsBtn);

        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ttsEditText.getText().toString();
                //Inicia la lectura mediante el método speak().
                tts.speak(text //Texto que leerá.
                        , TextToSpeech.QUEUE_FLUSH //Vacía la cola de lectura y lee los mensajes en espera.
                        , null //Especifica la clase empleada para leer los mensajes.
                        , EDIT_TEXT_SPEACH_CONTENT); //Id único para la consulta TTS.
            }
        });
    }

    /* Libera el TTS. */
    @Override
    protected void onStop() {
        super.onStop();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
