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
import callcenter.backend.dao.ContactDAO;
import callcenter.backend.entity.Client;
import callcenter.backend.entity.ContactInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class ContactInfoDaoTest {

    @Autowired
    private ContactDAO contactDao;

    @Test
    public void testAddress1() {
        /*
         * insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '221B backer street', '', 'LONDON', 'England', 'postal code', '555-89745', '2015-01-01');
insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '222B backer street', '', 'LONDON', 'England', 'postal code', '555-89999', '2015-01-01');
insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '223B backer street', '', 'LONDON', 'England', 'postal code', '555-88888', '2015-01-01');
         */
        assertNotNull(contactDao);
        List<ContactInfo> results = contactDao.getClientContactInfo(1);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        results.stream().forEach(a -> {
            assertEquals(Integer.valueOf(1), a.getClientId());
            assertNotNull(a.getId());
            assertNotNull(a.getAddress1());
            assertNotNull(a.getCity());
            assertNotNull(a.getCountry());
            assertNotNull(a.getCode());
            assertNotNull(a.getCreateDt());
            assertNull(a.getUpdateDt());
        });
        Integer pk = results.get(0).getId();
        results.get(0).setAddress2("first floor");
        Date testDt = new Date();
        results.get(0).setCreateDt(testDt);
        //update
        contactDao.update(results.get(0));
        //
        ContactInfo result = contactDao.getContactInfo(pk);
        assertEquals(pk, result.getId());
        assertEquals("first floor", result.getAddress2());
        assertNotNull(result.getUpdateDt());
        assertFalse(testDt.equals(result.getCreateDt()));
    }

    @Test
    public void testInsert() {
        assertNotNull(contactDao);
        ContactInfo c = new ContactInfo();
        c.setAddress1("address1");
        c.setCity("BREST");
        c.setCountry("France");
        Number pk =  contactDao.createContactInfo(c, 2);

        List<ContactInfo> results = contactDao.getClientContactInfo(2);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.size() == 3);
        ContactInfo inserted = results.stream().filter(a -> a.getId().equals(pk)).findFirst().get();
        assertEquals(Integer.valueOf(2), inserted.getClientId());
        assertNotNull(inserted.getId());
        assertEquals("address1", inserted.getAddress1());
        assertEquals("BREST", inserted.getCity());
        assertEquals("France", inserted.getCountry());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNull() {
        assertNotNull(contactDao);
        contactDao.createContactInfo(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNoClientId() {
        assertNotNull(contactDao);
        ContactInfo c = new ContactInfo();
        c.setAddress1("address1");
        contactDao.createContactInfo(c, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNoAddress() {
        assertNotNull(contactDao);
        ContactInfo c = new ContactInfo();
        contactDao.createContactInfo(c, 1);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testGetNoAddress() {
        assertNotNull(contactDao);
        contactDao.getContactInfo(25);
    }
}
