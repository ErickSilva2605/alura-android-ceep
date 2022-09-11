package br.com.alura.ceep.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.alura.ceep.R;

public class NoteFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Inserir nota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);
        setTitle(APPBAR_TITLE);
    }
}