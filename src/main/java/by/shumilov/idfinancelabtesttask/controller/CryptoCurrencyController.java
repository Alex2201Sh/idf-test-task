package by.shumilov.idfinancelabtesttask.controller;

import by.shumilov.idfinancelabtesttask.bean.CryptoCurrency;
import by.shumilov.idfinancelabtesttask.bean.Note;
import by.shumilov.idfinancelabtesttask.service.CryptoCurrencyService;
import by.shumilov.idfinancelabtesttask.service.ObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CryptoCurrencyController {
    private final CryptoCurrencyService service;
    private final ObjectSerializer serializer;

    @Autowired
    public CryptoCurrencyController(CryptoCurrencyService service, ObjectSerializer serializer) {
        this.service = service;
        this.serializer = serializer;
    }

    @GetMapping()
    public ResponseEntity<String> getAllCurrencies() {
        HttpHeaders headers = new HttpHeaders();
        List<CryptoCurrency> allCurrencies = service.getAllCurrencies();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(allCurrencies), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurrencyById(@PathVariable("id") long id) {
        HttpHeaders headers = new HttpHeaders();
        CryptoCurrency currencyById = service.findCurrencyById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(serializer.objectToJson(currencyById), headers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createNotification(@RequestBody Note note) {
        Double priceUsd = service.findCurrencyBySymbol(note.getCryptoCurrencySymbol()).getPriceUsd();
        service.saveNote(note, priceUsd);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/currencies")
                .build().toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(serializer.objectToJson(service.getAllCurrencies()));
    }
}
