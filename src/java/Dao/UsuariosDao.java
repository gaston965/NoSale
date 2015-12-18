/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Usuarios;
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
public class UsuariosDao 
{
  @PersistenceContext
  EntityManager em;
  
  public List<Usuarios> selectUsuarios()
  {
     Query u= em.createQuery("Select u From Usuarios u ORDER BY u.usu ASC");
     return u.getResultList();
  }
  
  public List<Usuarios> selectUsuarios(String texto)
  {
     Query u=em.createQuery("Select u From Usuarios u where u.usu LIKE :texto OR u.pkIdUsu LIKE :texto");
     u.setParameter("texto","%"+ texto +"%");
     return u.getResultList();
  
  }
  
  public void insertUsuario(Usuarios u)
  {
   em.persist(u);
  }
  
  public void updateUsuario(Usuarios u)
  {
   em.merge(u);
  }        
  
  public void deleteUsuario(Usuarios u)
  {
  
  em.remove(em.merge(u));
      
  }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
