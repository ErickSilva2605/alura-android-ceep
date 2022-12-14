package br.com.alura.ceep.ui.recyclerview.helpers.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.ui.recyclerview.adapter.NoteListAdapter;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final NoteListAdapter adapter;

    public NoteItemTouchHelperCallback(NoteListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int initialPosition = viewHolder.getAdapterPosition();
        int finalPosition = target.getAdapterPosition();
        swapNote(initialPosition, finalPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int notePosition = viewHolder.getAdapterPosition();
        removeNote(notePosition);
    }

    private void swapNote(int initialPosition, int finalPosition) {
        new NoteDAO().swap(initialPosition, finalPosition);
        adapter.swap(initialPosition, finalPosition);
    }

    private void removeNote(int position) {
        new NoteDAO().remove(position);
        adapter.remove(position);
    }
}
