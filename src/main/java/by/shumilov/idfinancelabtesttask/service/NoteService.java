package by.shumilov.idfinancelabtesttask.service;

import by.shumilov.idfinancelabtesttask.bean.Note;

import java.util.List;

public interface NoteService {

    List<Note> getNoteList();
    Note createNote(Note note, Double priceUsd);
}
