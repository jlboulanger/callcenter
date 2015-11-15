package callcenter.backend;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import callcenter.backend.dao.ClientDAO;
import callcenter.backend.dao.EmployeeDAO;
import callcenter.backend.entity.Client;
import callcenter.backend.entity.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDAO employeeDao;

    @Test
    public void testEmployee1() {
        assertNotNull(employeeDao);
        //insert into employee(lastname, firstname, type, dt_create) values ('test', 'second', 'SUPERVISOR', '2015-01-01');
        //insert into employee(lastname, firstname, supervisor, type, dt_create) values ('test', 'first', 1, 'CALL_CENTER', '2015-01-01');
        Employee result = employeeDao.getEmployee(1);
        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("test", result.getLastName());
        assertEquals("first", result.getFirstName());
        assertEquals("SUPERVISOR", result.getType());
        assertNull(result.getUpdateDt());
        //update
        /*result.setType("MEGA_SUPERVISOR");
        Date testDt = new Date();
        result.setCreateDt(testDt);
        employeeDao.updateEmployee(result);
        //
        result = employeeDao.getClient(1);
        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("test", result.getLastName());
        assertEquals("first", result.getFirstName());
        assertEquals("MEGA_SUPERVISOR", result.getType());
        assertNotNull(result.getUpdateDt());
        assertFalse(testDt.equals(result.getCreateDt()));*/
    }

    @Test
    public void testInsert() {
        assertNotNull(employeeDao);
        Employee e = new Employee();
        e.setFirstName("test");
        e.setLastName("third");
        e.setType("CALL_CENTER");
        e.setSupervisor(new Employee());
        e.getSupervisor().setId(1);
        employeeDao.createEmployee(e);

        Employee result = employeeDao.getEmployee(3);
        assertNotNull(result);
        assertEquals(Integer.valueOf(3), result.getId());
        assertEquals("third", result.getLastName());
        assertEquals("test", result.getFirstName());
        assertEquals("CALL_CENTER", result.getType());
        assertEquals(Integer.valueOf(1), result.getSupervisor().getId());
        assertNull(result.getUpdateDt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNull() {
        assertNotNull(employeeDao);
        employeeDao.createEmployee(null);
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void testGetNoEmployee() {
        assertNotNull(employeeDao);
        employeeDao.getEmployee(25);
    }
}
