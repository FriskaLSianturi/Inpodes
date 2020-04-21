/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Saran;
import interfaces.interfaceCrud;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author frisca
 */
public class implcrud  implements interfaceCrud{
    Session session;
    Transaction trans;
    @Override
    public <T> void add(T t) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.save(t);
        trans.commit();
        session.close();      
    }

    @Override
    public <T> void update(T t) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.update(t);
        trans.commit();
        session.close();      
    }

    @Override
    public <T> void delete(T t) {
        session = NewHibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        session.delete(t);
        trans.commit();
        session.close();      
     }

//    @Override
//    public <T> List<T> read() {
//           session = NewHibernateUtil.getSessionFactory().openSession();
//           Query q = session.createQuery("from Saran");
//           List<T> lsaran = q.list();
//           return lsaran;
//    }
    
}
