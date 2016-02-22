package callcenter.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import callcenter.backend.entity.ContactInfo;

@Transactional
public class ContactDAO {

    private JdbcTemplate tpl;
    private NamedParameterJdbcTemplate namedTpl;
    private DataSource ds;
    private RowMapper<ContactInfo> rm = (rs, i) -> {
        ContactInfo c = new ContactInfo();
        c.setAddress1(rs.getString("address1"));
        c.setAddress2(rs.getString("address2"));
        c.setCity(rs.getString("city"));
        c.setCountry(rs.getString("country"));
        c.setCode(rs.getString("code"));
        c.setPhone(rs.getString("phone"));
        c.setCreateDt(rs.getDate("dt_create"));
        c.setUpdateDt(rs.getDate("dt_update"));
        c.setClientId(rs.getInt("client_Id"));
        c.setId(rs.getInt("id"));
        return c;
        
    
    };
    
    public List<ContactInfo> getClientContactInfo(Integer clientId) {

        String query = "select id, client_Id, address1, address2, city, country, code, phone, dt_create, dt_update"
                        + " from contact_info where client_Id = ? order by id";
        return tpl.query(query,new Object[]{clientId}, rm);
    }

    public void deleteContactInfo(Integer id) {
        tpl.update("delete from contact_info where id = ? ", id);
    }

    public ContactInfo getContactInfo(Integer id) {

        String query = "select id, client_Id, address1, address2, city, country, code, phone, dt_create, dt_update"
                        + " from contact_info where id = ? ";
        return tpl.queryForObject(query,new Object[]{id}, rm);
    }
    
    public Number createContactInfo(ContactInfo c, Number clientId) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (StringUtils.isEmpty(c.getAddress1()) || clientId == null) {
            throw new IllegalArgumentException("missing mandatory param are empty");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(ds).withTableName("contact_info").usingGeneratedKeyColumns("id")
                        .usingColumns("client_Id", "address1", "address2", "city", "country", "code", "phone",
                                      "dt_create");
        Map<String, Object> params = new HashMap<>(5);
        params.put("client_Id", clientId);
        params.put("address1", c.getAddress1());
        params.put("address2", c.getAddress2());
        params.put("city", c.getCity());
        params.put("country", c.getCountry());
        params.put("code", c.getCode());
        params.put("phone", c.getPhone());
        params.put("dt_create", new Date());
        params.put("dt_update", null);
        Number pk = insert.executeAndReturnKey(params);
        return pk;
    }

    public void update(ContactInfo c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (c.getId() == null) {
            throw new IllegalArgumentException("no id provided is null");
        }
        String query = "update contact_info  set address1 = :address1, address2 = :address2, city = :city, country = :country,"
                        + " code = :code, phone = :phone, dt_update = :update where id = :id";
        Map<String, Object> params = new HashMap<>(5);
        params.put("address1", c.getAddress1());
        params.put("address2", c.getAddress2());
        params.put("city", c.getCity());
        params.put("country", c.getCountry());
        params.put("code", c.getCode());
        params.put("phone", c.getPhone());
        params.put("update", new Date());
        params.put("id", c.getId());
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
    
}
