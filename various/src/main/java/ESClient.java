import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.util.Map;

/**
 * @author j.horcicka (GMC)
 * @since 27.4.15
 */
public class ESClient {

    public static void main(String[] args) {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "jhorcicka")
                .build();
        System.out.println("Before creation.");
        Client client = new TransportClient(settings)
            .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

        String json = "{" +
                "\"user\":\"mickey mouse\"," +
                "\"postDate\":\"2000-10-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        IndexResponse response = client.prepareIndex("chat2", "message")
                .setSource(json)
                .execute()
                .actionGet();

        System.out.println("Before close.");
        client.close();
    }
}
