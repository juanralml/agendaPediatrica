/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.service;

import entidades.Hijo;
import entidades.HijoVacuna;
import entidades.HijoVacunaPK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
import javax.ws.rs.core.PathSegment;
import jsonObjects.ObjetoVacunaHijoApp;
import jsonObjects.OrdenFiltradoVH;

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
    
    
    @POST
    @Path("listHijoVacuna")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<ObjetoVacunaHijoApp> getHijoVacuna(OrdenFiltradoVH objeto) {
        List<ObjetoVacunaHijoApp> list  = new ArrayList<ObjetoVacunaHijoApp>();
        //ejecutar query nativo para obtener vacunas
        Query query = this.getEntityManager().createNativeQuery("select\n" +
"a.id id_vacuna,? hijo_ci, a.descripcion nombre_vacuna,case when hijo_ci is null then 'no' else 'si' end aplicado, esquema_ideal_meses::text, coalesce(to_char(fecha,'DD/MM/YYYY')::text,'')\n" +
"from vacunas a\n" +
"left join hijo_vacuna b on b.vacuna_id = a.id and b.hijo_ci = ?")
                .setParameter(1, objeto.getHijoCi())
                .setParameter(2, objeto.getHijoCi());
        //guardar resultado en una lista
        List<Object[]> result= query.getResultList();
        //recorrer lista
        for (Object[] a : result) {
            //obtener varialbes
            Integer a1 =  (Integer) a[0];
            String a2 = (String) a[1];
            String a3 = (String) a[2];
            String a4 = (String) a[3];
            String a5 = (String) a[4];
            String a6 = (String) a[5];
            //cargar en json a responser
            ObjetoVacunaHijoApp preList= new ObjetoVacunaHijoApp(a1,a2,a3,a4,a5,a6);
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
