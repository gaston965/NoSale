/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Categorias;
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
public class CategoriasDao {
    
     @PersistenceContext
     EntityManager em;
    
    public List<Categorias> selectCategorias()
    {
         Query q = em.createQuery("Select c From Categorias c order by c.pkIdCat asc");
        return q.getResultList();
     }
    
    public List <Categorias> selectCategorias(String texto)
    {
      Query q = em.createQuery("Select c From  Categorias c where c.cat  LIKE :texto");
      q.setParameter("texto", "%"+ texto + "%" );
      return q.getResultList();
    
    }
    
    public void insertCategorias(Categorias c)
    {
    
     em.persist(c);
    
    }
    
    public void deleteCategorias(Categorias c)
    {
      em.merge(c);
    }
    
    public void updateCategorias(Categorias c)
    {
     em.remove(em.merge(c));
    }
    // Add busines

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
