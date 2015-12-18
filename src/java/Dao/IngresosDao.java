/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Ingresos;
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
public class IngresosDao 
{
    
    @PersistenceContext
    EntityManager em;
    
    public List<Ingresos> selectPersonas()
    {
     Query i= em.createQuery("Select i From Ingresos i Where i.ing Like :texto OR i.pkIdIng LIKE :texto ");
     return i.getResultList();
     
    }
    
    public List<Ingresos>selectPersonas(String texto)
    {
      Query i=em.createQuery("Select i From Ingresos i where i.ing LIKE :texto OR i.pkIdIng LIKE :texto");
      i.setParameter("texto","%" + texto + "%");
      return i.getResultList();
    }
    
    public void insertIngreso(Ingresos i)
    {
      em.persist(i);
    }
    
    public void updateIngreso(Ingresos i)
    {
     em.merge(i);
    
    }
    
    public void deletePersonas(Ingresos i)
    {
     em.remove(em.merge(i));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
