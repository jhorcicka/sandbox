package cz.hk.gmc.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

public class ClientDemo {
    public static void main(String[] args) {
        get();
        post();
    }
    
    private static void post() {
        final Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        final WebTarget webTarget =
                client.target("http://localhost:8080/jersey-1.0-SNAPSHOT/rest/message").path("employees");
        final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        final Employee employee = new Employee(42, "John Doe");
        final Response response = invocationBuilder.post(Entity.entity(employee, MediaType.APPLICATION_JSON));

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }   
    
    private static void get() {
        final Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        final WebTarget webTarget =
                client.target("http://localhost:8080/jersey-1.0-SNAPSHOT/rest/message").path("employees").path("1");
        final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        final Response response = invocationBuilder.get();
        final Employee employee = response.readEntity(Employee.class);
        System.err.println("MYTODO: " + employee.toString());
        System.out.println(response.getStatus());
    }
}
