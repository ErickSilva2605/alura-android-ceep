package br.com.alura.ceep.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.adapter.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Notas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);
        new NoteDAO().insert(new Note("Primeira nota", "Testando primeira nota....."));
        new NoteDAO().insert(new Note("Segunda nota", "Testando segunda nota....."));
        configureNoteListView();
    }

    private void configureNoteListView() {
        ListView noteListView = findViewById(R.id.note_list_listview);
        List<Note> noteList = new NoteDAO().getAll();
        noteListView.setAdapter(new NoteListAdapter(this, noteList));
    }
}