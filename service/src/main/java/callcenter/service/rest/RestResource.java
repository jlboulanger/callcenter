package callcenter.service.rest;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import callcenter.backend.entity.Client;
import callcenter.backend.entity.ContactInfo;
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

    @POST
    @Path("/client/save")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Client saveClient(Client c) {
        return business.saveClient(c);
    }
//-----------------------------------------------------------
    @POST
    @Path("/contact/save")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ContactInfo saveContactInfo(ContactInfo c) {
        return business.saveContactInfo(c);
    }
    
    @GET
    @Path("/contact/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ContactInfo getContactInfoById(@PathParam("id") Integer id) {
        return business.getContactInfoById(id);
    }

    @DELETE
    @Path("/contact/{id}")
    public void deleteContactInfo(@PathParam("id") Integer id) {
        business.deleteContactInfo(id);
    }
}
