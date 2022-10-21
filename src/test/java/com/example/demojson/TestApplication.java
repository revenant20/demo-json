package com.example.demojson;

public class TestApplication {

    public static void main(String[] args) {
        var application = DemoJsonApplication.createSpringApplication();
        application.addInitializers(new AbstractIntegrationTest.Initializer());
        application.run(args);
    }

}
