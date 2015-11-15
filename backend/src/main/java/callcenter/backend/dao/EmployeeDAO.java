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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import callcenter.backend.entity.Client;
import callcenter.backend.entity.Employee;

@Transactional
public class EmployeeDAO {

    private JdbcTemplate tpl;
    private DataSource ds;
    private ClientDAO clientDao;
    private RowMapper<Employee> rm = (rs, i) -> {
        Employee e = new Employee();
        e.setFirstName(rs.getString("firstname"));
        e.setLastName(rs.getString("lastname"));
        e.setType(rs.getString("type"));
        e.setCreateDt(rs.getDate("dt_create"));
        e.setUpdateDt(rs.getDate("dt_update"));
        e.setId(rs.getInt("id"));
        Employee supervisor = new Employee();
        supervisor.setId(rs.getInt("supervisor"));
        e.setSupervisor(supervisor);
        return e;
    };

    public Employee getEmployee(Integer id) {
        String query = "select id, lastname, firstname, type, supervisor, dt_create, dt_update from employee where id = ?";
        return tpl.queryForObject(query,new Object[]{id}, rm);
    }

    public void createEmployee(Employee e) throws IllegalArgumentException {
        if (e == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (StringUtils.isEmpty(e.getLastName()) || StringUtils.isEmpty(e.getFirstName())) {
            throw new IllegalArgumentException("names are empty");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(ds).withTableName("employee").usingGeneratedKeyColumns("id")
                        .usingColumns("lastname", "firstname", "type", "supervisor", "dt_create", "dt_update");
        Map<String, Object> params = new HashMap<>(5);
        params.put("lastname", e.getLastName());
        params.put("firstname", e.getFirstName());
        params.put("supervisor", e.getSupervisor().getId());
        params.put("type", e.getType());
        params.put("dt_create", new Date());
        params.put("dt_update", null);
        Number pk = insert.executeAndReturnKey(params);
    }

    public ClientDAO getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public JdbcTemplate getTpl() {
        return tpl;
    }

    public void setTpl(JdbcTemplate tpl) {
        this.tpl = tpl;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

}
