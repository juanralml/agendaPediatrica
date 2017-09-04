/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.service;

import entidades.Hijo;
import entidades.HijoVacuna;
import entidades.HijoVacunaPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author usuario
 */
@Stateless
@Path("entidades.hijovacuna")
public class HijoVacunaFacadeREST extends AbstractFacade<HijoVacuna> {
    @PersistenceContext(unitName = "agenda_pedriatricaPU")
    private EntityManager em;

    private HijoVacunaPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;hijoCi=hijoCiValue;vacunaId=vacunaIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entidades.HijoVacunaPK key = new entidades.HijoVacunaPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> hijoCi = map.get("hijoCi");
        if (hijoCi != null && !hijoCi.isEmpty()) {
            key.setHijoCi(hijoCi.get(0));
        }
        java.util.List<String> vacunaId = map.get("vacunaId");
        if (vacunaId != null && !vacunaId.isEmpty()) {
            key.setVacunaId(new java.lang.Integer(vacunaId.get(0)));
        }
        return key;
    }

    public HijoVacunaFacadeREST() {
        super(HijoVacuna.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(HijoVacuna entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, HijoVacuna entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entidades.HijoVacunaPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public HijoVacuna find(@PathParam("id") PathSegment id) {
        entidades.HijoVacunaPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<HijoVacuna> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<HijoVacuna> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("por_ci_hijo")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<HijoVacuna> find_padre(Hijo entity) {
       return this.getEntityManager().createNamedQuery("HijoVacuna.findByHijoCi")
                .setParameter("hijoCi", entity.getCi())
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("agenda_pedriatricaPU").createEntityManager();
        return em;
    }
    
}
