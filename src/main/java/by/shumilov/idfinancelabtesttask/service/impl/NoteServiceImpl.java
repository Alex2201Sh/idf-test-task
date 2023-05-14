package by.shumilov.idfinancelabtesttask.service.impl;

import by.shumilov.idfinancelabtesttask.bean.Note;
import by.shumilov.idfinancelabtesttask.repository.NoteRepository;
import by.shumilov.idfinancelabtesttask.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    @Autowired
    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note createNote(Note note, Double priceUsd) {
        note.setPriceUsd(priceUsd);
        return repository.save(note);
    }

    @Override
    public List<Note> getNoteList() {
        return repository.findAll();
    }
}
