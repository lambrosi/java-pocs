package com.lucasambrosi.demo.application.controller;

import com.lucasambrosi.demo.application.exception.TaxIdNotAllowedException;
import com.lucasambrosi.demo.application.service.MicrometerMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class AppController {

    private final MicrometerMetricService micrometerMetricService;

    public AppController(MicrometerMetricService micrometerMetricService) {
        this.micrometerMetricService = micrometerMetricService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestParam String name,
                             @RequestParam String taxId) {
        return validateTaxId(taxId)
                .flatMap(it -> this.save(name, it))
                .doOnNext(micrometerMetricService::incrementUserSuccess)
                .doOnError(micrometerMetricService::incrementUserError);
    }

    private Mono<String> validateTaxId(final String taxId) {
        return Mono.just(taxId)
                .filterWhen(this::isValid)
                .switchIfEmpty(Mono.error(TaxIdNotAllowedException::new));
    }

    private Mono<Boolean> isValid(final String taxId) {
        //Perform API call to validate taxId in some bureau
        return Mono.just(!taxId.endsWith("7"));
    }

    private Mono<User> save(final String name, final String taxId) {
        return Mono.just(new User(name, taxId));
    }
}
