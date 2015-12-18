/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.GastosDao;
import Model.Gastos;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author gastonb
 */
@ManagedBean
@RequestScoped
public class gastosController {
    
    //Objetos necesarios
    private Gastos gas;
    private List<Gastos> listaGas;

    /**
     * Creates a new instance of gastosController
     */
    //Operaciones
    
    char ope='\0';
    private String tituloOpe;
    private String txtBusqueda;
    
    
    // Inyectar dependencias a los session Bean
    
    @EJB
    GastosDao gasDao;
    public gastosController() 
    {
        
      gas= new Gastos();
     }
    
    public Gastos getGas()
    {
     return gas;
    }
    
    public void setGas(Gastos gas)
    {
      this.gas=gas;
    }
    
    public List<Gastos> getListaGas()
    {
     return listaGas;
    }
    
    public void setListaGas(List<Gastos> listaGas)
    {
     this.listaGas= listaGas;
    }
    
    public String getTituloOpe()
    {
     return tituloOpe;
    }
    
    public void setTituloOpe(String tituloOpe)
    {
     this.tituloOpe=tituloOpe;
    }
    
    public String getTxtBusqueda()
    {
     return txtBusqueda;
    }
    
    public void setTxtBusqueda(String txtBusqueda)
    {
      this.txtBusqueda= txtBusqueda;
    }
    
    //Metodo que se ejecuta despues de cargar la pagina 
    // Manejada por el controller;
    
    @PostConstruct
    public void inicializar()
    {
     listaGas = gasDao.selectGastos();
    }
    
    //Retornar gasto
  //  public long gastos(long gastin)
    //{
    //    return gasDao.selectGastos();
    //}
    
    // Metodos de redireccionamiento de paginas
    
    public String doverIndex()
    {
      return "index";
    }
    
    public String dovolver()
    {
      //Actualizar
        listaGas= gasDao.selectGastos();
        return "index";
    }
    
    public String doGuardar()
    { if( ope == 'A') 
        {
            gasDao.insertGastos(gas);
        } else 
        {
          gasDao.updateGastos(gas);
        
        }
        return dovolver();
    
    }
    
    public String doPrepararModificacion( Gastos g)
    {
       ope= 'M';
       setTituloOpe("Modificar Persona");
       gas = g;
       return "nuevo";
    
    }
    
     public String doBorrar(Gastos g) {
        gasDao.deleteGastos(g);
        return dovolver();
    }
    
    //Búsqueda:
    public String doBuscar(){
        listaGas = gasDao.selectGastos(tituloOpe);
        return "index";
    }
    
    
    
    
    
    
    
}
