package nl.hi.kuba.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
public class JerseyService {
    @GET
    public String getMsg() {
        return "Hello World !! - Jersey 2";
    }

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployeeById(@PathParam("id") Integer id) {
        if (id < 0) {
            return Response.noContent().build();
        }

        Employee emp = new Employee();

        emp.setId(id);
        emp.setName("Lokesh Gupta");

        GenericEntity<Employee> entity = new GenericEntity<>(emp, Employee.class);
        return Response.ok().entity(entity).build();
    }

    @POST
    @Path("/employees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) {
        if (employee == null) {
            return Response.status(400).entity("Please add employee details !!").build();
        }

        if (employee.getName() == null) {
            return Response.status(400).entity("Please provide the employee name !!").build();
        }

        GenericEntity<Employee> entity = new GenericEntity<>(employee, Employee.class);
        return Response.ok().entity(entity).build();
    }
}
