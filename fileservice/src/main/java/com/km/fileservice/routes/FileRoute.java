package com.km.fileservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:/Users/kishore/Documents/input-folder?delete=true")
                .to("file:/Users/kishore/Documents/output-folder")
                .log("File moved from input-folder to output-folder successfully!");
    }
}
