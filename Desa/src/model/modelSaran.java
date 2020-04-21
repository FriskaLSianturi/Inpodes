/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.hibernate.Query;
import util.NewHibernateUtil;

/**
 *
 * @author frisca
 */
public class modelSaran  extends implcrud implements interfaces.interfaceRead{

    @Override
    public <T> List<T> read() {
        session = NewHibernateUtil.getSessionFactory().openSession();
           Query q = session.createQuery("from Saran");
           List<T> lsaran = q.list();
           return lsaran;
    }
    
}
