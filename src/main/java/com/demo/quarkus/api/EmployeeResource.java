package com.demo.quarkus.api;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.jboss.logging.Logger;

import com.demo.quarkus.api.entity.EmployeeRecord;
import com.demo.quarkus.api.service.EmployeeService;

import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.StatusType;
import oracle.jdbc.proxy.annotation.GetCreator;

@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    //Inject the default JBoss Logger
    @Inject
    private Logger logger ;

    @Inject
    private transient EmployeeService empService ;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeRecord> getEmployees(){
        logger.info("Request received::/employees");
        return empService.getEmployees().orElse(List.of()) ;
    }

    @POST
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeRecord getEmployee(@NotNull @PathParam("id") final String empId){

        return empService.getEmployeeById(empId) ;
    }

    @POST
    @Path("/employee/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@NotNull  final EmployeeRecord emp){
        logger.info("Received request to update employee" + emp.id());
        return Response.ok(empService.updateEmployee(emp)).build() ;
    }
}
