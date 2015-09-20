package callcenter.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.StringUtils;

import callcenter.backend.entity.Client;

public class EmployeeDAO {

    private JdbcTemplate tpl;
    private DataSource ds;
    private RowMapper<Client> rm = (rs, i) -> {return new Client();};//TODO
    
    public Client getClient(Integer id) {
        String query = "select id, lastname, firstname, type, dt_create_, dt_update from client where id = ?";
        return tpl.query(query,new Object[]{id}, rm).stream().limit(1).findFirst().get();
    }

    //TODO transaction - requieres new
    public void createClient(Client c) throws IllegalArgumentException {
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
    }
    

}
