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
import callcenter.backend.entity.Client;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class ClientDaoTest {

    @Autowired
    private ClientDAO clientDao;

    @Test
    public void testClient1() {
        //insert into client(lastname, firstname, type, dt_create) values ('test', 'first', 'PROSPECT', '2015-01-01');
        assertNotNull(clientDao);
        Client result = clientDao.getClient(1);
        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("test", result.getLastName());
        assertEquals("first", result.getFirstName());
        assertEquals("PROSPECT", result.getType());
        assertNull(result.getUpdateDt());
        assertNotNull(result.getContacts());
        assertFalse(result.getContacts().isEmpty());
        //update
        result.setType("CLIENT");
        Date testDt = new Date();
        result.setCreateDt(testDt);
        clientDao.updateClient(result);
        //
        result = clientDao.getClient(1);
        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("test", result.getLastName());
        assertEquals("first", result.getFirstName());
        assertEquals("CLIENT", result.getType());
        assertNotNull(result.getUpdateDt());
        assertFalse(testDt.equals(result.getCreateDt()));
    }

    @Test
    public void testInsert() {
        assertNotNull(clientDao);
        Client c = new Client();
        c.setFirstName("test");
        c.setLastName("third");
        c.setType("PROSPECT");
        clientDao.createClient(c, null);

        Client result = clientDao.getClient(3);
        assertNotNull(result);
        assertEquals(Integer.valueOf(3), result.getId());
        assertEquals("third", result.getLastName());
        assertEquals("test", result.getFirstName());
        assertEquals("PROSPECT", result.getType());
        assertNull(result.getUpdateDt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNull() {
        assertNotNull(clientDao);
        clientDao.createClient(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNoName() {
        assertNotNull(clientDao);
        Client c = new Client();
        c.setFirstName("test");
        clientDao.createClient(c, null);
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void testGetNoClient() {
        assertNotNull(clientDao);
        clientDao.getClient(25);
    }
}
