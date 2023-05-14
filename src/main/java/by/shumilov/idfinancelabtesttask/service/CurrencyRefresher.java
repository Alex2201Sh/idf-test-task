package by.shumilov.idfinancelabtesttask.service;

import by.shumilov.idfinancelabtesttask.bean.CryptoCurrency;
import by.shumilov.idfinancelabtesttask.bean.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CurrencyRefresher {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final NoteService noteService;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyRefresher.class);

    @Autowired
    public CurrencyRefresher(CryptoCurrencyService service, NoteService noteService) {
        this.cryptoCurrencyService = service;
        this.noteService = noteService;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1)
    @Async
    public void refreshData() {
        RestTemplate restTemplate = new RestTemplate();
        List<Note> noteList = noteService.getNoteList();
        noteList
                .forEach(note -> {
                    CryptoCurrency cryptoCurrencyFromExternalApi = Objects.requireNonNull(restTemplate
                            .getForObject(("https://api.coinlore.net/api/ticker/?id=" +
                                            cryptoCurrencyService.findCurrencyBySymbol(note.getCryptoCurrencySymbol())
                                                    .getId()),
                                    CryptoCurrency[].class))[0];
                    double delta = (cryptoCurrencyFromExternalApi.getPriceUsd() - note.getPriceUsd()) / note.getPriceUsd();
                    if (Math.abs(delta) >= 0.00001) {
                        logger.warn("%s %s %s".formatted(
                                cryptoCurrencyFromExternalApi.getSymbol(),
                                note.getUserName(),
                                delta * 100));
                    }
                    cryptoCurrencyService.save(cryptoCurrencyFromExternalApi);
                });
    }
}
