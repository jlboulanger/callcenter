package callcenter.service.rest;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import callcenter.backend.entity.Client;
import callcenter.service.business.BusinessBean;

@WebService
public class RestResource {

    @Autowired
    private BusinessBean business;
    
    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    @GET
    @Path("/client/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Client getClientById(@PathParam("id") Integer id) {
        return business.getClientById(id);
    }
}
