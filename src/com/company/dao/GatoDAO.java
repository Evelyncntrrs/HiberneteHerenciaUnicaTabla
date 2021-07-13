package com.company.dao;


import com.company.entidades.Gato;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class GatoDAO implements InterfazDAO{

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /** Este metodo lista todos los gatos */
    public static List<Gato> listarTodo(){
        Session session = sessionFactory.openSession();
        List<Gato> gatos = (List<Gato>) session.createQuery("FROM Gato").list();
        session.close();
        return gatos;
    }

    /** busca un gato por su ID */
    public static Gato buscar(int id){
        Session session = sessionFactory.openSession();
        return session.load(Gato.class, id);
    }

    /** busca por nombre con un prepared statement
     * y devuelve el primer resultado */
    public static Gato buscar(String nombre){
        Session session = sessionFactory.openSession();
        Gato gato = (Gato) session.createQuery("FROM Gato WHERE nombre LIKE :nombre").setParameter("nombre","%"+nombre+"%").list().get(0);
        session.close();
        return gato;
    }

    /**
     * @param nombre = parte del nombre a buscar
     * @return devuelve la lista de gatos encontrados
     */
    public static List<Gato> buscarTodos(String nombre){
        Session session = sessionFactory.openSession();
        List gatos = session.createQuery("FROM Gato WHERE nombre LIKE :nombre").setParameter("nombre","%"+nombre+"%").list();
        session.close();
        return (List<Gato>) gatos;
    }

    public static boolean guardar(Gato gato) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(gato);
            session.getTransaction().commit();
        }catch (EntityNotFoundException e){
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public void mostrarTodo() {
        for(Gato gato: listarTodo())
            System.out.println(gato);
    }

    public static boolean eliminarPorID(int id) {
        Session session = sessionFactory.openSession();
        Gato gatoAEliminar = session.load(Gato.class, id);
        session.close();
        return eliminar(gatoAEliminar);
    }

    public static boolean eliminar(Gato gatoAEliminar) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(gatoAEliminar);
            session.getTransaction().commit();
        }catch (EntityNotFoundException e){
            return false;
        }
        session.close();
        return true;
    }
}