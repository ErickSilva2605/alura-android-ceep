package br.com.alura.ceep.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;

public class NoteListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Notas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);
    }
}