package br.com.alura.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.model.Note;

public class NoteDAO {

    private final static ArrayList<Note> noteList = new ArrayList<>();

    public List<Note> getAll() {
        return (List<Note>) noteList.clone();
    }

    public void insert(Note... noteList) {
        NoteDAO.noteList.addAll(Arrays.asList(noteList));
    }

    public void update(int position, Note note) {
        noteList.set(position, note);
    }

    public void remove(int position) {
        noteList.remove(position);
    }

    public void swap(int initPosition, int finalPosition) {
        Collections.swap(noteList, initPosition, finalPosition);
    }

    public void removeAll() {
        noteList.clear();
    }
}
