/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Gastos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gastonb
 */
@Stateless
public class GastosDao 
{

    @PersistenceContext
    EntityManager em;
    
    public List<Gastos> selectGastos()
    {
         Query q = em.createQuery ("Select g From Gastos g order by g.gas asc");
        return q.getResultList();
     }
    
    public List <Gastos> selectGastos(String texto)
    {
      Query q = em.createQuery("Select g From Gastos g where g.gas LIKE :texto");
      q.setParameter("texto", "%"+ texto + "%");
      return q.getResultList();
    
    }
    
    public void insertGastos(Gastos g)
    {
    
     em.persist(g);
    
    }
    
    public void deleteGastos(Gastos g)
    {
     em.merge(g);
    }
    
    public void updateGastos(Gastos g)
    {
     em.remove(em.merge(g));
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
