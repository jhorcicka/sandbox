package nl.hi.kuba.testing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import eu.rekawek.toxiproxy.Proxy;
import eu.rekawek.toxiproxy.ToxiproxyClient;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
class TestContainersTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Rule
    public Network network = Network.newNetwork();
    private static final int MONGO_PORT = 27017;
    private static final int TOXIPROXY_PORT = 8666;
    @Rule
    final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
            .withExposedPorts(MONGO_PORT)
            .withNetworkAliases("mongo")
            .withNetwork(network);
    @Rule
    public ToxiproxyContainer toxiproxy = new ToxiproxyContainer(DockerImageName
            .parse("ghcr.io/shopify/toxiproxy:2.5.0")
            .asCompatibleSubstituteFor("shopify/toxiproxy")).withNetwork(network);

    @Test
    public void testIt() throws IOException {
        mongoDBContainer.start();
        toxiproxy.start();

        final ToxiproxyClient toxiproxyClient = new ToxiproxyClient(toxiproxy.getHost(), toxiproxy.getControlPort());
        final String listen = "0.0.0.0:" + TOXIPROXY_PORT;
        final String upstream = "mongo:" + MONGO_PORT;
        final Proxy proxy = toxiproxyClient.createProxy("proxy", listen, upstream);
        //proxy.toxics().limitData("name", ToxicDirection.DOWNSTREAM, 100); // It looks like yo
        final String requestUrl = "http://" + toxiproxy.getHost() + ":" + toxiproxy.getMappedPort(TOXIPROXY_PORT);
        final String response = restTemplate.getForObject(requestUrl, String.class);
        log.error("======================================================");
        log.error(response);
        log.error("======================================================");
        assertTrue(response.contains(
                "It looks like you are trying to access MongoDB over HTTP on the native driver port."));
    }
}
