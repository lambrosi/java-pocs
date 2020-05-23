package com.lucasambrosi.demo.application.controller;

import com.lucasambrosi.demo.application.counter.MonthCounter;
import com.lucasambrosi.demo.application.exception.ValueNotAllowedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Month;

@RestController
@RequestMapping
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    private MonthCounter monthCounter = new MonthCounter();

    @PostMapping("/{month}")
    public Mono<ResultComputation> compute(@PathVariable Month month,
                                           @RequestParam Long value) {
        return validateValue(value)
                .map(newValue -> monthCounter.increaseCounter(month, newValue))
                .map(currentCounter -> new ResultComputation(month, currentCounter));
    }

    private Mono<Long> validateValue(final Long value) {
        return Mono.just(value)
                .filterWhen(this::isEven)
                .switchIfEmpty(Mono.error(ValueNotAllowedException::new));
    }

    private Mono<Boolean> isEven(final Long value) {
        return Mono.just(value % 2 == 0);
    }
}
