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

/**
 *
 * @author z3rh10
 */
public class PersonajesManager {

    Session session = null;
    public String mess = "";
    int maxResults = 100;

    public PersonajesManager() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Personajes getPersonajes(int id) {
        Personajes Personajes = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Personajes as personajes where personajes.idp=" + id);
            Personajes = (Personajes) q.uniqueResult();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }

        return Personajes;
    }

    public List getPersonajes(int pageSize, int page) {
        List<Personajes> personajesList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Personajes personajes order by personajes.idp");
            personajesList = (List<Personajes>) q.list().subList(page * pageSize, (page + 1) * pageSize);
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return personajesList;
    }

    public List getPersonajes() {
        List<Personajes> personajesList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Personajes personajes order by personajes.lanzamientop DESC");
            personajesList = (List<Personajes>) q.list();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return personajesList;
    }
    
    public List getPersonajesRol(String rol) {
        List<Personajes> personajesList = null;

        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Query q = session.createQuery("from Personajes personajes where personajes.rol ='" + rol + "' order by personajes.idp");
            personajesList = (List<Personajes>) q.list();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return personajesList;
    }

    public List searchPersonajes(Personajes personajesSample) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        List<Personajes> personajesList = null;
        Example example = Example.create(personajesSample).ignoreCase().excludeZeroes().enableLike(MatchMode.START);

        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            personajesList = (List<Personajes>) session.createCriteria(Personajes.class).
                    add(example).addOrder(Order.asc("idp")).list();
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
        }
        return personajesList;
    }

    public boolean savePersonajes(Personajes personajes) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(personajes);
            tx.commit();
        } catch (HibernateException ex) {
            mess = ex.getMessage();
            tx.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deletePersonajes(Personajes personajes) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }

        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.delete(personajes);
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
