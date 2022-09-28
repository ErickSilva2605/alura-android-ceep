package br.com.alura.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private final Context context;
    private final List<Note> noteList;
    private OnItemClickListener onItemClickListener;

    public NoteListAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewInflated = LayoutInflater.from(context)
                .inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(viewInflated);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bindNote(note);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_note_title);
            description = itemView.findViewById(R.id.item_note_description);

            itemView.setOnClickListener(view -> {
                onItemClickListener.onItemClick();
            });
        }

        public void bindNote(Note note) {
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }

    public void addNote(Note note){
        noteList.add(note);
        notifyDataSetChanged();
    }
}
