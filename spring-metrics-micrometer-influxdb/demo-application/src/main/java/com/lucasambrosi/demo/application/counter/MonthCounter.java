package com.lucasambrosi.demo.application.counter;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class MonthCounter {

    private static final Long DEFAULT_COUNTER = 0L;
    private final Map<Month, Long> counter;

    public MonthCounter() {
        this.counter = new HashMap<>();
    }

    public Long getCounterFor(Month month) {
        return counter.getOrDefault(month, DEFAULT_COUNTER);
    }

    public Long increaseCounter(Month month, Long newValue) {
        return counter.compute(
                month,
                (key, value) -> value == null ? newValue : newValue + value);
    }
}
