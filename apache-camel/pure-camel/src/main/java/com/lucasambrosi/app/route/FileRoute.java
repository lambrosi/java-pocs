package com.lucasambrosi.app.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

public class FileRoute extends RouteBuilder {

    private static final String PATH = "/home/lucas/Desktop/camel/";
    private static final String INPUT_FOLDER = "in";
    private static final String OUTPUT_FOLDER = "out";

    @Override
    public void configure() throws Exception {
        from("file:" + buildInputFolder())
                .process(this::process)
                .to("file:" + buildOutputFolder());
    }

    private void process(Exchange exchange) {
        Message inputMessage = exchange.getIn();

        log("Processing file '%s'", inputMessage.getHeader("CamelFilePath").toString());

        log("Content: '%s'", inputMessage.getBody(String.class));
    }

    private String buildInputFolder() {
        return PATH + INPUT_FOLDER;
    }

    private String buildOutputFolder() {
        return PATH + OUTPUT_FOLDER;
    }

    private void log(String message, String arg) {
        System.out.println(String.format(message, arg));
    }
}
