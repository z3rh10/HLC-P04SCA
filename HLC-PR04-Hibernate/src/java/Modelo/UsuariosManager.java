/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import java.util.*;
import org.hibernate.Criteria;

/**
 *
 * @author z3rh10
 */
public class UsuariosManager {

    Session session = null;
    public String mess = "";
    int maxResults = 100;

    public UsuariosManager() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Usuarios getUsuarios(int id) {
        Usuarios Usuarios = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Usuarios as usuarios where usuarios.idu=" + id);
            Usuarios = (Usuarios) q.uniqueResult();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }

        return Usuarios;
    }

    public List getUsuarios(int pageSize, int page) {
        List<Usuarios> usuariosList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Usuarios usuarios order by usuarios.idu");
            usuariosList = (List<Usuarios>) q.list().subList(page * pageSize, (page + 1) * pageSize);
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return usuariosList;
    }

    public List getUsuarios() {
        List<Usuarios> usuariosList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Usuarios usuarios order by usuarios.idu");
            usuariosList = (List<Usuarios>) q.list();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return usuariosList;
    }

    public List searchUsuarios(Usuarios usuariosSample) {

        List<Usuarios> usuariosList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        org.hibernate.Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Usuarios.class);

        Usuarios usuarios = new Usuarios();
        
        usuarios.setIdu(usuariosSample.getIdu());
        usuarios.setUsu(usuariosSample.getUsu());
        usuarios.setContra(usuariosSample.getContra());
        usuarios.setApodo(usuariosSample.getApodo());
        usuarios.setTelefono(usuariosSample.getTelefono());
        usuarios.setEmail(usuariosSample.getEmail());
        usuarios.setDiaalta(usuariosSample.getDiaalta());

        Example example = Example.create(usuarios).ignoreCase().excludeZeroes().enableLike(MatchMode.ANYWHERE);
        crit.add(example);
//        if (usuariosSample.getIdu() != null) {
//            crit.add(Restrictions.eq("ciudad", clienteSample.getCiudad()));
//        }
        crit.addOrder(Order.asc("idu"));

        try {
            usuariosList = (List<Usuarios>) crit.list();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return usuariosList;
    }

    public boolean saveUsuarios(Usuarios usuarios) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(usuarios);
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUsuarios(Usuarios usuarios) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.delete(usuarios);
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
