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
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget =
                client.target("http://localhost:8080/jersey-1.0-SNAPSHOT/rest/message").path("employees");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Employee employee = new Employee();
        employee.setId(42);
        employee.setName("John Doe");
        
        Response response = invocationBuilder.post(Entity.entity(employee, MediaType.APPLICATION_XML));

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }   
    
    private static void get() {
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
