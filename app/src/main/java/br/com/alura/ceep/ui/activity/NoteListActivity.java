package br.com.alura.ceep.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.recyclerview.adapter.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Notas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);
        setMockNoteList();
        configureNoteRecyclerView();
    }

    private void setMockNoteList() {
        NoteDAO dao = new NoteDAO();
        dao.insert(new Note("Primeira nota.....", "Descrição ....................... "));
        dao.insert(new Note("Segunda nota", "Descrição para teste do StaggeredGridLayoutManager...... "));
    }

    private void configureNoteRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_list_recycler_view);
        List<Note> noteList = new NoteDAO().getAll();
        configureAdapter(noteRecyclerView, noteList);
    }

    private void configureAdapter(RecyclerView noteRecyclerView, List<Note> noteList) {
        noteRecyclerView.setAdapter(new NoteListAdapter(this, noteList));
    }
}