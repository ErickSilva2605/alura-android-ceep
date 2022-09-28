package br.com.alura.ceep.ui.activity;

import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_NOTE_POSITION;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_NOTE_REQUEST;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_NOTE_RESULT;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_REQUEST_CODE_NOTE_CREATE;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_REQUEST_CODE_NOTE_EDIT;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_RESULT_CODE_NOTE_CREATED;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);
        configureNoteRecyclerView();
        configureAddNoteClickListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (isNoteCreatedResult(requestCode, resultCode, data)) {
            Note noteResult = (Note) data.getSerializableExtra(KEY_NOTE_RESULT);
            createNote(noteResult);
        } else if (isNoteEditedResult(requestCode, resultCode, data)) {
            Note noteResult = (Note) data.getSerializableExtra(KEY_NOTE_RESULT);
            int position = data.getIntExtra(KEY_NOTE_POSITION, -1);
            editNote(noteResult, position);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createNote(Note noteResult) {
        saveNote(noteResult);
        adapter.addNote(noteResult);
    }

    private void editNote(Note noteResult, int position) {
        updateNote(noteResult, position);
        adapter.update(position, noteResult);
    }

    private boolean isNoteCreatedResult(int requestCode, int resultCode, Intent data) {
        return requestCode == KEY_REQUEST_CODE_NOTE_CREATE
                && resultCode == KEY_RESULT_CODE_NOTE_CREATED
                && data != null
                && data.hasExtra(KEY_NOTE_RESULT)
                && data.hasExtra(KEY_NOTE_POSITION);
    }

    private boolean isNoteEditedResult(int requestCode, int resultCode, Intent data) {
        return requestCode == KEY_REQUEST_CODE_NOTE_EDIT
                && resultCode == KEY_RESULT_CODE_NOTE_CREATED
                && data != null
                && data.hasExtra(KEY_NOTE_RESULT);
    }

    private void configureNoteRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_list_recycler_view);
        configureAdapter(noteRecyclerView, dao.getAll());
    }

    private void configureAdapter(RecyclerView noteRecyclerView, List<Note> noteList) {
        adapter = new NoteListAdapter(this, noteList);
        noteRecyclerView.setAdapter(adapter);
        configureItemClickListener();
    }

    private void configureItemClickListener() {
        adapter.setOnItemClickListener((note, position) -> {
            openNoteFormToEdit(note, position);
        });
    }

    private void configureAddNoteClickListener() {
        TextView addNote = findViewById(R.id.note_list_add_note);
        addNote.setOnClickListener(view -> openNoteFormToCreate());
    }

    private void openNoteFormToCreate() {
        Intent intent = new Intent(this, NoteFormActivity.class);
        startActivityForResult(intent, KEY_REQUEST_CODE_NOTE_CREATE);
    }

    private void openNoteFormToEdit(Note note, int position) {
        Intent intent = new Intent(this, NoteFormActivity.class);
        intent.putExtra(KEY_NOTE_REQUEST, note);
        intent.putExtra(KEY_NOTE_POSITION, position);
        startActivityForResult(intent, KEY_REQUEST_CODE_NOTE_EDIT);
    }

    private void saveNote(Note note) {
        dao.insert(note);
    }

    private void updateNote(Note note, int position) {
        dao.update(position, note);
    }
}