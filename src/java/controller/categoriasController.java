/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.CategoriasDao;
import Model.Categorias;
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
public class categoriasController 
 {

    /**
     * Creates a new instance of categoriasController
     */
    private Categorias cat2;
    private List<Categorias> ListaCat;
    char ope2='\0';
    private String tituloOpe;
    private String txtBusqueda;
    
    
    //inyectar dependencias de los session Bean
    
    @EJB
    CategoriasDao catDao;
    
    public categoriasController() 
    {
        
       
     cat2= new Categorias();   
    
    }
    
    
    public Categorias getCat()
    {
     return cat2;
    }
    
    public void setCat (Categorias cat2)
    {
    
        this.cat2= cat2;
        
    }
    
    public List <Categorias> getListaCat()
    {
     return ListaCat;
    }
    
    public void setListaCat(List <Categorias> ListaCat)
    {
      this.ListaCat= ListaCat;
    
    }
    
    public String gettituloOpe()
    {
       return tituloOpe;
    }
    
    public void setTituloOpe(String tituloOpe )
    {
      this.tituloOpe = tituloOpe;
    
    }

    public String getTxtBusqueda()
    {
      return txtBusqueda;
    }
    
     public void setTxtBusqueda(String txtBusqueda) {
        this.txtBusqueda = txtBusqueda;
    }
    
    
    @PostConstruct
    public void inicializar()
    {
       
      ListaCat= catDao.selectCategorias();
    }

    
    // Aca va el calculo
    
    
    //Metodos de redireccionamiento de paginas;
    
    public String doverIndex()
    {
      return "index";
    }
    
    public String doVolver()
    {
      ListaCat= catDao.selectCategorias();
      return "index";
    }
    
    public String doNuevo()
    {
       ope2 ='A';
       setTituloOpe("Nueva Categoria");
       cat2 = new Categorias();
       return "Nuevo";
       
    }
    
    public String doGuardar()
    {
      if(ope2 =='A')
      {

          catDao.updateCategorias(cat2);
      } else
      {
          catDao.insertCategorias(cat2);
      }
       // catDao.insertCategorias(cat2);
      // ListaCat=catDao.selectCategorias();
       return doVolver();
    
    }
    
    
    public String doPrepararModificacion( Categorias c)
    {
            ope2 ='M';
            setTituloOpe("Modificar Categoria");
            cat2 = c;
            return "Nuevo";
    }
    
    public String doBorrar(Categorias c)
    {
     catDao.deleteCategorias(c);
     return doVolver();
    }
    
    //Busqueda
    
    public String doBuscar()
    {
      ListaCat= catDao.selectCategorias(txtBusqueda);
      return "index";
    }
    


    }
