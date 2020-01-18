package com.lucasambrosi.app;

import com.lucasambrosi.app.route.FileRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new FileRoute());

        for (; ; ) {
            context.start();
        }
    }
}
