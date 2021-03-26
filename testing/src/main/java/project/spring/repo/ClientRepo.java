package project.spring.repo;

import project.spring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {

    List<Client> findByLastname(String lastName);

    Client findClientById(Long id);

    Client findClientByTicketId(Long id);

    Client findClientByLastname(String lastName);

    List<Client> findAllById(Long id);
}

//public interface ClientDao extends JpaRepository<Client, Long> {



    /*

    public Client findId(Long id){
        return HibernateUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public void save(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public Ticket findTicketById(Long id_num_of_tick) {
        return HibernateUtil.getSessionFactory().openSession().get(Ticket.class, id_num_of_tick);
    }

    public List<Client> findAll() {
        List<Client> clientList = (List<Client>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Client").list();
        return clientList;
    }

     */










//}
