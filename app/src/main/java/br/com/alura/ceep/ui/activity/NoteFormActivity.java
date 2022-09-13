package br.com.alura.ceep.ui.activity;

import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_NOTE_RESULT;
import static br.com.alura.ceep.ui.activity.NoteActivityConstants.KEY_RESULT_CODE_NOTE_CREATED;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;

public class NoteFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_ADD = "Inserir nota";
    public static final String APPBAR_TITLE_EDIT = "Editar nota";
    private EditText edtTitle;
    private EditText edtDescription;
    private Note selectedNote = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);
        setTitle(APPBAR_TITLE_ADD);
        initFields();
    }

    private void initFields() {
        edtTitle = findViewById(R.id.note_form_title);
        edtDescription = findViewById(R.id.note_form_description);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_form_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (isMenuNoteFormSave(item)) {
            setNote();
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMenuNoteFormSave(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_note_form_save;
    }

    private void setNote() {
        selectedNote = new Note(edtTitle.getText().toString(),
                edtDescription.getText().toString());
    }

    private void finishForm() {
        returnNote();
        finish();
    }

    private void returnNote() {
        Intent resultData = new Intent();
        resultData.putExtra(KEY_NOTE_RESULT, selectedNote);
        setResult(KEY_RESULT_CODE_NOTE_CREATED, resultData);
    }
}