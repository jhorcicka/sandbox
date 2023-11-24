package nl.hi.kuba.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.BeforeClass;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import eu.rekawek.toxiproxy.Proxy;
import eu.rekawek.toxiproxy.ToxiproxyClient;
import eu.rekawek.toxiproxy.model.ToxicDirection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TestingApplicationTests {
    @LocalServerPort
    private int port;

    private int proxyPort = 20001;

    @TestConfiguration
    static class TestConfig {
        @Bean
        RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().setReadTimeout(Duration.of(5, ChronoUnit.SECONDS));
        }
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private GenericContainer<?> toxiproxy = new GenericContainer<>(DockerImageName
            .parse("ghcr.io/shopify/toxiproxy:2.3.0")
            .asCompatibleSubstituteFor("shopify/toxiproxy")).withExposedPorts(8474, proxyPort);

    @Test
    void proxyOnlyWorks() throws IOException {
        toxiproxy.start();
        ToxiproxyClient toxiproxyClient = new ToxiproxyClient(toxiproxy.getHost(), toxiproxy.getFirstMappedPort());

        InetAddress hostRunningTheAppEndpoint = InetAddress.getLocalHost();

        final Proxy proxy = toxiproxyClient.createProxy("my-proxy", "0.0.0.0:" + proxyPort,
                hostRunningTheAppEndpoint.getHostAddress() + ":" + port);
        proxy.enable();

        ResponseEntity<String> stringResponseEntity =
                this.restTemplate.getForEntity("http://localhost:" + toxiproxy.getMappedPort(proxyPort) + "/",
                        String.class);
        assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());
    }

    @Test
    void proxyWithLatency() throws IOException {
        ToxiproxyClient client = new ToxiproxyClient(toxiproxy.getHost(), toxiproxy.getFirstMappedPort());

        InetAddress hostRunningTheAppEndpoint = InetAddress.getLocalHost();

        Proxy proxy = client.createProxy("my-proxy", "0.0.0.0:" + proxyPort,
                hostRunningTheAppEndpoint.getHostAddress() + ":" + port);
        proxy.toxics().latency("latency", ToxicDirection.DOWNSTREAM, 4_000);

        ResponseEntity<String> stringResponseEntity =
                this.restTemplate.getForEntity("http://localhost:" + toxiproxy.getMappedPort(proxyPort) + "/",
                        String.class);
        assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());
    }

    @Test
    void proxyWithLatencyTimeout() throws IOException {
        ToxiproxyClient client = new ToxiproxyClient(toxiproxy.getHost(), toxiproxy.getFirstMappedPort());

        InetAddress hostRunningTheAppEndpoint = InetAddress.getLocalHost();

        Proxy proxy = client.createProxy("my-proxy", "0.0.0.0:" + proxyPort,
                hostRunningTheAppEndpoint.getHostAddress() + ":" + port);
        proxy.toxics().latency("latency", ToxicDirection.DOWNSTREAM, 6_000);

        ResourceAccessException resourceAccessException = assertThrows(ResourceAccessException.class,
                () -> this.restTemplate.getForEntity("http://localhost:" + toxiproxy.getMappedPort(proxyPort) + "/",
                        String.class));

        assertEquals(SocketException.class, resourceAccessException.getRootCause().getClass());
    }

    @Test
    void worksNoProxy() {
        ResponseEntity<String> stringResponseEntity =
                this.restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());
    }

    @Test
    public void testMutationMethod() {
        assertEquals(1, TestingApplication.mutationTestMethod(1));
        assertEquals(0, TestingApplication.mutationTestMethod(0));
        assertEquals(0, TestingApplication.mutationTestMethod(-1));
    }

    @Test
    public void whenExecutedThenSuccess() {
        assertTrue(true);
    }

    @RepeatedTest(value = 3, name = "Repeated test {currentRepetition}/{totalRepetitions}")
    public void whenExecutedThenRepeatedSuccess(TestInfo testInfo) {
        assertTrue(true);
    }

}
