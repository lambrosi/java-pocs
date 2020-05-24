package com.lucasambrosi.demo.application.service;

import com.lucasambrosi.demo.application.controller.User;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Service;

@Service
public class MicrometerMetricService {

    private static final String CREATE_USER_SUCCESS = "user.success";
    private static final String CREATE_USER_ERROR = "user.error";

    public void incrementUserSuccess(User user) {
        this.increment(CREATE_USER_SUCCESS);
    }

    public void incrementUserError(Throwable throwable) {
        this.increment(CREATE_USER_ERROR,
                "error.class", throwable.getClass().getCanonicalName());
    }

    private void increment(String name, String... tags) {
        Metrics.counter(name, tags).increment();
    }

}
