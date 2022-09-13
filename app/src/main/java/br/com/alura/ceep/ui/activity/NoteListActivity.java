package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.recyclerview.adapter.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Notas";
    private final NoteDAO dao = new NoteDAO();
    private NoteListAdapter adapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);
        setMockNoteList();
        configureNoteRecyclerView();
        configureAddNoteClickListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1
                && resultCode == 2
                && data != null
                && data.hasExtra("noteResult")) {
            Note noteResult = (Note) data.getSerializableExtra("noteResult");
            saveNote();
            adapter.addNote(noteResult);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setMockNoteList() {
        dao.insert(new Note("Primeira nota.....", "Descrição ....................... "));
        dao.insert(new Note("Segunda nota", "Descrição para teste do StaggeredGridLayoutManager...... "));
    }

    private void configureNoteRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_list_recycler_view);
        noteList = dao.getAll();
        configureAdapter(noteRecyclerView, noteList);
    }

    private void configureAdapter(RecyclerView noteRecyclerView, List<Note> noteList) {
        adapter = new NoteListAdapter(this, noteList);
        noteRecyclerView.setAdapter(adapter);
    }

    private void configureAddNoteClickListener() {
        TextView addNote = findViewById(R.id.note_list_add_note);
        addNote.setOnClickListener(view -> openNoteForm());
    }

    private void openNoteForm() {
        Intent intent = new Intent(this, NoteFormActivity.class);
        startActivityForResult(intent, 1);
    }

    private void saveNote(Note note) {
        dao.insert(note);
    }
}