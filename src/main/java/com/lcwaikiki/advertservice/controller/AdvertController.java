package com.lcwaikiki.advertservice.controller;

import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.repository.AdvertRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adverts")
public class AdvertController {
    private final AdvertRepository advertRepository;

    public AdvertController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @GetMapping
    public List<Advert> findAll() {
        return advertRepository.findAll();
    }

    @GetMapping("/{id}")
    public Advert findById(@PathVariable int id) throws AdvertNotFoundException {
        return advertRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Advert create(@RequestBody Advert advert) {
        return advertRepository.create(advert);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}")
    public void update(@Valid @RequestBody Advert advert, @PathVariable int id) throws AdvertNotFoundException {
        advertRepository.update(advert, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        advertRepository.delete(id);
    }
}
