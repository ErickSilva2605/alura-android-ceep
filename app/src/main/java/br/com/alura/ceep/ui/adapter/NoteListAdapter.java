package br.com.alura.ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;

public class NoteListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Note> noteList;

    public NoteListAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Note getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewInflated = LayoutInflater.from(context)
                .inflate(R.layout.item_note, viewGroup, false);
        Note note = noteList.get(position);
        setTitle(viewInflated, note);
        setDescription(viewInflated, note);

        return viewInflated;
    }

    private void setDescription(View viewInflated, Note note) {
        TextView description = viewInflated.findViewById(R.id.item_note_description);
        description.setText(note.getDescription());
    }

    private void setTitle(View viewInflated, Note note) {
        TextView title = viewInflated.findViewById(R.id.item_note_title);
        title.setText(note.getTitle());
    }
}
