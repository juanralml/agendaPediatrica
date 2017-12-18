/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.service;

import entidades.Hijo;
import entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
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
import jsonObjects.ObjetoHijo;

/**
 *
 * @author usuario
 */
@Stateless
@Path("entidades.hijo")
public class HijoFacadeREST extends AbstractFacade<Hijo> {
    @PersistenceContext(unitName = "agenda_pedriatricaPU")
    private EntityManager em;

    public HijoFacadeREST() {
        super(Hijo.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Hijo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Hijo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Hijo find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Hijo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Hijo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("por_padre")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Hijo> find_padre(Hijo entity) {
       return this.getEntityManager().createNamedQuery("Hijo.findByPadreId")
                .setParameter("padreId", entity.getPadreId())
                .getResultList();
    }
    
    
    @POST
    @Path("listHijoPorPadre")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<ObjetoHijo> getHijoVacuna(Usuarios entity) {
        List<ObjetoHijo> list  = new ArrayList<ObjetoHijo>();
        //ejecutar query nativo para obtener vacunas
        Query query = this.getEntityManager().createNativeQuery("select\n" +
        "ci,\n" +
        "nombres as nombre,\n" +
        "extract(year from age(fecha_nacimiento))||' años y '  ||  extract(month from age(fecha_nacimiento)) || ' meses' edad,\n" +
        "fecha_nacimiento::text,\n" +
        "sexo\n" +
        "from hijo\n"+
        "where padre_id=?")
        .setParameter(1, (int) entity.getId());
        //guardar resultado en una lista
        List<Object[]> result= query.getResultList();
        //recorrer lista
        for (Object[] a : result) {
            //obtener varialbes
            String a1 =  (String) a[0];
            String a2 = (String) a[1];
            String a3 = (String) a[2];
            String a4 = (String) a[3];
            String a5 = (String) a[4];
            //cargar en json a responser
            ObjetoHijo preList= new ObjetoHijo(a1,a2,a3,a4,a5);
            list.add(preList);
        }
        //retorno
       return list;
    }

    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("agenda_pedriatricaPU").createEntityManager();
        return em;
    }
    
}
