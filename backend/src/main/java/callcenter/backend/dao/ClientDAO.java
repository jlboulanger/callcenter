package callcenter.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import callcenter.backend.entity.Client;
import callcenter.backend.entity.ContactInfo;

@Transactional
public class ClientDAO {

    private JdbcTemplate tpl;
    private NamedParameterJdbcTemplate namedTpl;
    private DataSource ds;
    private ContactDAO contactDao;
   
    private RowMapper<Client> rm = (rs, i) -> {
        Client c = new Client();
        c.setFirstName(rs.getString("firstname"));
        c.setLastName(rs.getString("lastname"));
        c.setType(rs.getString("type"));
        c.setCreateDt(rs.getDate("dt_create"));
        c.setUpdateDt(rs.getDate("dt_update"));
        c.setId(rs.getInt("id"));
        return c;
    
    };
    
    public Client getClient(Integer id) {
        String query = "select id, lastname, firstname, type, dt_create, dt_update from client where id = ? ";
        Client c = tpl.queryForObject(query,new Object[]{id}, rm);
        c.setContacts(contactDao.getClientContactInfo(id));
        return c;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Number createClient(Client c, ContactInfo contact) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (StringUtils.isEmpty(c.getLastName()) || StringUtils.isEmpty(c.getFirstName())) {
            throw new IllegalArgumentException("names are empty");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(ds).withTableName("client").usingGeneratedKeyColumns("id")
                        .usingColumns("lastname", "firstname", "type", "dt_create", "dt_update");
        Map<String, Object> params = new HashMap<>(5);
        params.put("lastname", c.getLastName());
        params.put("firstname", c.getFirstName());
        params.put("type", "PROSPECT");
        params.put("dt_create", new Date());
        params.put("dt_update", null);
        Number pk = insert.executeAndReturnKey(params);
        //add adress
        if (contact != null) {
            contactDao.createContactInfo(contact, pk);
        }
        return pk;
    }

    public void updateClient(Client c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (c.getId() == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (StringUtils.isEmpty(c.getLastName()) || StringUtils.isEmpty(c.getFirstName())) {
            throw new IllegalArgumentException("names are empty");
        }
        String query = "update client set lastname = :lastname, firstname = :firstname, dt_update = :update, type = :type where id = :id ";
        Map<String, Object> params = new HashMap<>(5);
        params.put("lastname", c.getLastName());
        params.put("firstname", c.getFirstName());
        params.put("update", new Date());
        params.put("type", c.getType());
        params.put("id", c.getId());
        namedTpl.update(query, params);
    }

    public void updateType(String type, Integer id) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String query = "update client set type = :type where id = :id ";
        Map<String, Object> params = new HashMap<>(5);
        params.put("type", type);
        params.put("id", id);
        namedTpl.update(query, params);
    }
 
    public void lock(Integer employeId, Integer clientId) throws IllegalArgumentException {
        if (employeId == null) {
            throw new IllegalArgumentException("employeId is null");
        }
        if (clientId == null) {
            throw new IllegalArgumentException("clientId is null");
        }
        String query = "update client set locked_by = :employeId where id = :id ";
        Map<String, Object> params = new HashMap<>(5);
        params.put("employeId", employeId);
        params.put("id", clientId);
        namedTpl.update(query, params);
    }

    public JdbcTemplate getTpl() {
        return tpl;
    }

    public void setTpl(JdbcTemplate tpl) {
        this.tpl = tpl;
    }

    public NamedParameterJdbcTemplate getNamedTpl() {
        return namedTpl;
    }

    public void setNamedTpl(NamedParameterJdbcTemplate namedTpl) {
        this.namedTpl = namedTpl;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public ContactDAO getContactDao() {
        return contactDao;
    }

    public void setContactDao(ContactDAO contactDao) {
        this.contactDao = contactDao;
    }

}
