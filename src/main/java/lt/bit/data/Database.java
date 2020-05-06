package lt.bit.data;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class Database {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Person> getPersons(EntityManager em) {
        Query q = em.createQuery("select p from Person p");
        List<Person> personList = q.getResultList();
        return personList;
    }

    public static Person getPerson(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p;
    }

    public static void addPerson(EntityManager em, Person p) {
        List<Person> personList = getPersons(em);
        if (!personList.contains(p)) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            em.flush();
            tx.commit();
        }
    }

    public static void removePerson(EntityManager em, Integer id) {
        Person existing = em.find(Person.class, id);
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(existing);
            em.flush();
            tx.commit();
        }
    }

    public static void updatePerson(EntityManager em, Person p) {
        if (p == null) {
            return;
        }
        Person existing = em.find(Person.class, p.getId());
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            existing.setFirstName(p.getFirstName());
            existing.setLastName(p.getLastName());
            existing.setBirthDate(p.getBirthDate());
            existing.setSalary(p.getSalary());
            em.flush();
            tx.commit();
        }
    }

    public static List<Address> getPersonsAddresses(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p.getAddresses();
    }

    public static Address getAddress(EntityManager em, Integer id) {
        Address a = em.find(Address.class, id);
        return a;
    }

    public static void addAddress(EntityManager em, Integer id, Address a) {
        Person p = em.find(Person.class, id);
        if (p == null || a == null) {
            return;
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(a);
        em.flush();
        tx.commit();
    }

    public static void removeAddress(EntityManager em, Integer id) {
        Address existing = getAddress(em, id);
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(existing);
            em.flush();
            tx.commit();
        }
    }

    public static void updateAddress(EntityManager em, Address a) {
        if (a == null) {
            return;
        }
        Address existing = em.find(Address.class, a.getId());
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            existing.setAddress(a.getAddress());
            existing.setCity(a.getCity());
            existing.setPostalCode(a.getPostalCode());
            em.flush();
            tx.commit();
        }
    }

    public static List<Contact> getPersonsContacts(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p.getContacts();
    }

    public static Contact getContact(EntityManager em, Integer id) {
        Contact c = em.find(Contact.class, id);
        return c;
    }

    public static void addContact(EntityManager em, Integer id, Contact c) {
        Person p = getPerson(em, id);
        if (p == null || c == null) {
            return;
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(c);
        em.flush();
        tx.commit();
    }

    public static void removeContact(EntityManager em, Integer id) {
        Contact existing = getContact(em, id);
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(existing);
            em.flush();
            tx.commit();
        }
    }

    public static void updateContact(EntityManager em, Contact c) {
        if (c == null) {
            return;
        }
        Contact existing = getContact(em, c.getId());
        if (existing != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            existing.setContact(c.getContact());
            existing.setContactType(c.getContactType());
            em.flush();
            tx.commit();
        }
    }
}
