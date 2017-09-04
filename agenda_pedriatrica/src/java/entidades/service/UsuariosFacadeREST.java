/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.service;

import entidades.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author usuario
 */
@Stateless
@Path("entidades.usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "agenda_pedriatricaPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuarios.class);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Usuarios> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("delete")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Usuarios delete_user(Usuarios entity) {
        EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("agenda_pedriatricaPU");
        Usuarios user = new Usuarios();
        EntityManager em2 = emf.createEntityManager();
        try {
            user = em2.find(Usuarios.class, entity.getId());
            em2.getTransaction().begin();
            em2.remove(user);
            em2.getTransaction().commit();
        } catch (Exception e) {
            em2.close();
        } finally{
            em2.close();
        }
        return user;
    }
    
    @POST
    @Path("add")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Usuarios add_user(Usuarios entity) {
        EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("agenda_pedriatricaPU");
        Usuarios user = new Usuarios();
        EntityManager em2 = emf.createEntityManager();
        try {
            user = em2.find(Usuarios.class, entity.getId());
            em2.getTransaction().begin();
            em2.persist(user);
            em2.getTransaction().commit();
        } catch (Exception e) {
            em2.close();
        }finally{
            em2.close();
        }
        return user;
    }
    
    @POST
    @Path("edit")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Usuarios edit_user(Usuarios entity) {
        EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("agenda_pedriatricaPU");
        Usuarios user = new Usuarios();
        EntityManager em2 = emf.createEntityManager();
        try {
            user = em2.find(Usuarios.class,entity.getId());
            user.setName(entity.getName());
            user.setLastName(entity.getLastName());
            user.setUserName(entity.getUserName());
            user.setEmail(entity.getEmail());
            user.setCi(entity.getCi());
            em2.getTransaction().begin();
            em2.persist(user);
            em2.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em2.getTransaction().rollback();
            em2.close();
        }finally{
            em2.close();
        }
        return user;
    }
    
    @POST
    @Path("get_by_ci")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Usuarios> find_ci(Usuarios entity) {
       return this.getEntityManager().createNamedQuery("Usuarios.findByCi")
                .setParameter("id", entity.getCi())
                .getResultList();
    } 
    @POST
    @Path("get_by_user_name")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Usuarios> find_user_name(Usuarios entity) {
       return this.getEntityManager().createNamedQuery("Usuarios.findByUserName")
                .setParameter("userName", entity.getUserName())
                .getResultList();
    }
    @POST
    @Path("get_by_email")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Usuarios> find_email(Usuarios entity) {
       return this.getEntityManager().createNamedQuery("Usuarios.findByEmail")
                .setParameter("email", entity.getEmail())
                .getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("agenda_pedriatricaPU").createEntityManager();
        return em;
    }
    
}
