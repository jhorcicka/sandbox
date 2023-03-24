package nl.hi.kuba.insights.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;

@RestController
public class TelemetryController {
    @Autowired
    TelemetryClient telemetryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetryController.class);
    @GetMapping("/telemetry")
    public String telemetry(@RequestParam(name = "id", defaultValue = "42") String id) {
        LOGGER.info("INFO: Endpoint /telemetry?id={} has been called. ", id);
        LOGGER.warn("WARN: Endpoint /telemetry?id={} has been called. ", id);
        LOGGER.error("ERROR: Endpoint /telemetry?id={} has been called. ", id);
        telemetryClient.trackEvent("URI /telemetry?id=" + id + " has been triggered. ");
        telemetryClient.trackException(new RuntimeException("Test KUBA exception. "));
        telemetryClient.trackMetric("KUBA-metric", 42);
        telemetryClient.trackPageView("KUBA: telemetry page");
        telemetryClient.trackDependency("KUBA dependency", "KUBA command", new Duration(42L), true);
        telemetryClient.trackRequest(new RequestTelemetry("KUBA request", new Date(), 1L, "200", true));
        telemetryClient.trackHttpRequest("KUBA request", new Date(), 1L, "200", true);
        telemetryClient.trackTrace("KUBA trace");
        telemetryClient.flush();
        return "Test{id=" + id + "}";
    }
}
