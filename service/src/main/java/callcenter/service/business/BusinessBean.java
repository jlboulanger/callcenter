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

    public Client createClient(Client c) {//TODO create client + contact info
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

        //create or update
        if (c.getId() == null) {
            return createClient(c);
        } else {
            clientDao.updateClient(c);
            return getClientById(c.getId());
        }
    }

    public ContactInfo createContactInfo(ContactInfo c) {
        if (c == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        if (StringUtils.isEmpty(c.getAddress1()) && StringUtils.isEmpty(c.getAddress2())) { //TODO add method checking mandatory data
            throw new IllegalArgumentException("A full adress must be provided");
        }
        return getContactInfoById(contactDao.createContactInfo(c, c.getClientId()).intValue());
    }

    public ContactInfo getContactInfoById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        return contactDao.getContactInfo(id);
    }

    public ContactInfo saveContactInfo(ContactInfo c) {
        if (c == null) {
            throw new IllegalArgumentException("parameter is null");
        }
      //create or update
        if (c.getId() == null) {
            return createContactInfo(c);
        } else {
            contactDao.update(c);
            return getContactInfoById(c.getId());
        }
    }

    public void deleteContactInfo(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        contactDao.deleteContactInfo(id);
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
