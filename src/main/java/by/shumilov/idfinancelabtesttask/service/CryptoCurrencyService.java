package by.shumilov.idfinancelabtesttask.service;

import by.shumilov.idfinancelabtesttask.bean.CryptoCurrency;
import by.shumilov.idfinancelabtesttask.bean.Note;

import java.util.List;

public interface CryptoCurrencyService {
    List<CryptoCurrency> getAllCurrencies();

    CryptoCurrency findCurrencyById(Long id);

    CryptoCurrency findCurrencyBySymbol(String symbol);

    void save(CryptoCurrency cryptoCurrency);

    void saveNote(Note note, Double priceUsd);


}
