package cz.hk.gmc.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

public class ClientDemo {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget =
                client.target("http://localhost:8080/jersey-1.0-SNAPSHOT/rest/message").path("employees").path("1");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();
        Employee employee = response.readEntity(Employee.class);
        System.err.println("MYTODO: " + employee.toString());
        System.out.println(response.getStatus());
    }
}
