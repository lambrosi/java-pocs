package com.lucasambrosi.demo.application.controller;

import com.lucasambrosi.demo.application.exception.InvalidNameException;
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
import reactor.util.function.Tuple2;

@RestController
@RequestMapping
public class AppController {

    private final MicrometerMetricService micrometerMetricService;

    public AppController(MicrometerMetricService micrometerMetricService) {
        this.micrometerMetricService = micrometerMetricService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestParam(required = false) String name,
                             @RequestParam String taxId) {
        return validateInput(name, taxId)
                .flatMap(this::save)
                .doOnNext(micrometerMetricService::incrementUserSuccess)
                .doOnError(micrometerMetricService::incrementUserError);
    }

    private Mono<Tuple2<String, String>> validateInput(final String name, final String taxId) {
        return this.isValid(taxId).zipWhen(it -> this.isNameValid(name));
    }

    private Mono<String> isValid(final String taxId) {
        //Perform API call to validate taxId in some bureau
        return Mono.just(taxId)
                .filter(it -> !it.endsWith("7"))
                .switchIfEmpty(Mono.error(TaxIdNotAllowedException::new));
    }

    private Mono<String> isNameValid(final String name) {
        return Mono.just(name)
                .filter(it -> it.split(" ").length > 1)
                .switchIfEmpty(Mono.error(InvalidNameException::new));
    }

    private Mono<User> save(Tuple2<String, String> tuple) {
        return Mono.just(new User(tuple.getT1(), tuple.getT2()));
    }
}
