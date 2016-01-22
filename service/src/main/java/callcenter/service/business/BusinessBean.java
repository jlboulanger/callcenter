package callcenter.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import callcenter.backend.dao.ClientDAO;
import callcenter.backend.dao.ContactDAO;
import callcenter.backend.entity.Client;
import callcenter.backend.entity.ContactInfo;

@Component
public class BusinessBean {

    @Autowired
    private ClientDAO clientDao;
    @Autowired
    private ContactDAO contactDao;


    public Client getClientById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        return clientDao.getClient(id);
    }

    public Client createClient(Client c) {
        if (c == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        if (StringUtils.isEmpty(c.getLastName()) || StringUtils.isEmpty(c.getFirstName())) {
            throw new IllegalArgumentException("A full name must be provided");
        }
        return getClientById(clientDao.createClient(c, null).intValue());
    }

    public Client saveClient(Client c) {
        if (c == null) {
            throw new IllegalArgumentException("parameter is null");
        }

        if (StringUtils.isEmpty(c.getLastName()) || StringUtils.isEmpty(c.getFirstName())) {
            throw new IllegalArgumentException("A full name must be provided");
        }
        //System.out.println("client to save is " + c.toString());
        //create or update
        if (c.getId() == null) {
            return getClientById(clientDao.createClient(c, null).intValue());
        } else {
            clientDao.updateClient(c);
            return getClientById(c.getId());
        }
    }

    public ClientDAO getDao() {
        return clientDao;
    }

    public void setDao(ClientDAO dao) {
        this.clientDao = dao;
    }
    public ContactDAO getContactDao() {
        return contactDao;
    }

    public void setContactDao(ContactDAO contactDao) {
        this.contactDao = contactDao;
    }
}
