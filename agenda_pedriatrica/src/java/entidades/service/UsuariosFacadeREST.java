/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.service;

import entidades.Hijo;
import entidades.Usuarios;
import entidades.Vacunas;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import jsonObjects.NotificacionesVacunas;

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
    
    
    @POST
    @Path("notifiVac")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<NotificacionesVacunas> getNotification(Usuarios entity) {
        //declarar lista
        List<NotificacionesVacunas> notif  = new ArrayList<NotificacionesVacunas>();
        
        
        //ejecutar query nativo para obtener vacunas
        Query query = this.getEntityManager().createNativeQuery("select a.id,b.ci,a.descripcion,a.esquema_ideal_meses,\n" +
                "((a.esquema_ideal_meses*30)::numeric -(current_date-b.fecha_nacimiento))::integer\n" +
                "from vacunas a\n" +
                "join hijo b on (a.esquema_ideal_meses*30)::integer -(current_date-b.fecha_nacimiento)::integer < 15 \n" +
                "and padre_id = ?\n" +
                "where a.id not in (select vacuna_id from hijo_vacuna where hijo_ci = b.ci)").setParameter(1, entity.getId());
        //guardar resultado en una lista
        List<Object[]> result= query.getResultList();
        //recorrer lista
        for (Object[] a : result) {
            //obtener varialbes
            Integer a1 = (Integer) a[0];
            String a2 = (String) a[1];
            String a3 = (String) a[2];
            Integer a4 = (Integer) a[3];
            Integer a5 = (Integer) a[4];
            //cargar en json a responser
            NotificacionesVacunas preNotif= new NotificacionesVacunas(a1,a2,a3,a4,a5);
            notif.add(preNotif);
        }
        //retorno
       return notif;
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("agenda_pedriatricaPU").createEntityManager();
        return em;
    }
    
}
