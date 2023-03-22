package nl.hi.kuba.insights.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.localforwarder.library.inputs.contracts.Telemetry;

@RestController
public class TelemetryController {
    @Autowired
    TelemetryClient telemetryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetryController.class);
    @GetMapping("/telemetry")
    public String telemetry(@RequestParam(name = "id", defaultValue = "42") String id) {
        LOGGER.info("Endpoint /telemetry?id=" +  id + " has been called. ");
        telemetryClient.trackEvent("URI /telemetry?id=" + id + " has been triggered. ");
        telemetryClient.trackException(new RuntimeException("Test KUBA exception. "));
        telemetryClient.flush();
        return "Test{id=" + id + "}";
    }
}
