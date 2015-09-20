package callcenter.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import callcenter.backend.dao.ClientDAO;
import callcenter.backend.entity.Client;

@Component
public class BusinessBean {

    @Autowired
    private ClientDAO dao;

    public ClientDAO getDao() {
        return dao;
    }

    public void setDao(ClientDAO dao) {
        this.dao = dao;
    }

    public Client getClientById(Integer id) {
        return dao.getClient(id);
    }
}
