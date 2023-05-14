package by.shumilov.idfinancelabtesttask.service.impl;

import by.shumilov.idfinancelabtesttask.bean.CryptoCurrency;
import by.shumilov.idfinancelabtesttask.bean.Note;
import by.shumilov.idfinancelabtesttask.repository.CryptoCurrencyRepository;
import by.shumilov.idfinancelabtesttask.service.CryptoCurrencyService;
import by.shumilov.idfinancelabtesttask.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository repository;
    private final NoteService noteService;

    @Autowired
    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository repository, NoteService noteService) {
        this.repository = repository;
        this.noteService = noteService;
    }

    @Override
    public List<CryptoCurrency> getAllCurrencies() {
        return repository.findAll();
    }

    @Override
    public CryptoCurrency findCurrencyById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CryptoCurrency findCurrencyBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    @Override
    public void save(CryptoCurrency cryptoCurrency) {
        repository.save(cryptoCurrency);
    }

    @Override
    public void saveNote(Note note, Double priceUsd) {
        noteService.createNote(note, priceUsd);
    }
}
