package br.com.alura.ceep.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;

public class NoteFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_ADD = "Inserir nota";
    public static final String APPBAR_TITLE_EDIT = "Editar nota";
    private EditText edtTitle;
    private EditText edtDescription;
    private Note selectedNote = null;
    private final NoteDAO dao = new NoteDAO();

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
        int itemId = item.getItemId();
        if (itemId == R.id.menu_note_form_save) {
            setNote();
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNote() {
        selectedNote = new Note(edtTitle.getText().toString(),
                edtDescription.getText().toString());
    }

    private void finishForm() {
        saveNote();
        finish();
    }

    private void saveNote() {
        dao.insert(selectedNote);
    }
}