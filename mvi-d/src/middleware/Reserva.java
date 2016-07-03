/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author MarcoA
 */
@XmlRootElement
public class Reserva implements Serializable {
 
    public String reserva;
    public String vuelo;
    public String nombre;
    
    public Reserva()
    {
        
    }

    public String getReserva() {
        return reserva;
    }
    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getVuelo() {
        return vuelo;
    }
    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static void EliminarReserva(String reservaEliminar){
try {
                //Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
                //archivo que tiene las reservas
		File xmlFile = new File("reserva.xml");
                //objeto que caragra el archivo tipo document
		Document doc = (Document) builder.build(xmlFile);  
                //se obtiene el padre del xml
		Element rootNode = doc.getRootElement();
                //se obtiene el hijo "reserva"
                List reservas = rootNode.getChildren("reserva");
                //se declara un iterator para recorrer el archivo
                Iterator iter = reservas.iterator();
                //se recorre el archivo
                while(iter.hasNext()){
                Element reserva = (Element) iter.next();
                //se elimina la reserva ya pagada
                    if(reserva.getAttributeValue("clave").equals(reservaEliminar)){
                        iter.remove();
                    }
                }
                
		XMLOutputter xmlOutput = new XMLOutputter();

		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
                //se guardan los cambios
		xmlOutput.output(doc, new FileWriter("reserva.xml"));
                
          } catch (IOException io) {
		io.printStackTrace();
	  } catch (JDOMException e) {
		e.printStackTrace();
	  }

}
    
    public static ArrayList<Reserva> ConsultarReservas(){
        //lista de reservaas
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        
 //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    //archivo que tiene las reservas
    File xmlFile = new File( "reserva.xml" );
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile ); 
        //Se obtiene la raiz 'reservas'
        Element rootNode = document.getRootElement();
        //Se obtiene la lista de hijos de la raiz 'vuelo'
        List list = rootNode.getChildren( "reserva" );
        //Se recorre la lista de hijos de 'disponibilidad'
        for ( int i = 0; i < list.size(); i++ )
        {//objeto de reservas
            Reserva objeto = new Reserva();
            //Se obtiene el elemento 'vuelo'
            Element tabla = (Element) list.get(i);
            
            //Se obtiene el atributo 'id' que esta en el tag 'vuelo'
            String idVuelo = tabla.getAttributeValue("clave");
            objeto.setReserva(idVuelo);
            
            //Se obtiene la lista de hijos del tag 'reserva'   
            List lista_campos = tabla.getChildren(); 
             //Se obtiene la lista de hijos del tag 'vuelo'  
            Element tabla2 = (Element) lista_campos.get(0);
            List Lista_nombres = tabla2.getChildren();
            //Se recorre la lista de campos
            for ( int j = 0; j < lista_campos.size(); j++ ){
                //Se obtiene el elemento 'campo'
                Element campo = (Element)lista_campos.get( j );
                Element hijosCampo = (Element)Lista_nombres.get( j );
                //Se obtienen los valores que estan entre los tags 
                //se guarda en el objeto los datos de la reserva
                String linea = campo.getAttributeValue("clave") ;
                String lineaNombre = hijosCampo.getTextTrim();
                if (campo.getName()=="vuelo"){
                    //linea= campo.getAttributeValue("vuelo");
                objeto.setVuelo(linea);
                }
                if (hijosCampo.getName()=="nombre"){
                    
                objeto.setNombre(lineaNombre);
                }
                //System.out.println( "\t"+linea+"\t\t");
            //listaVuelos.add(i,objeto);
            }
            //se agrega a la lista el objeto
            listaReservas. add(objeto);
           //System.out.println( "\"\t\t");
        }
        //listaVuelos. add(objeto);
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }// se retorna la lista de las reservas
        return listaReservas;
    
    }
    
    public static ArrayList<Reserva> ReservasPorNombre(String nombre){
        ArrayList<Reserva> listaReservas = new ArrayList();
        ArrayList<Reserva> reservas = ConsultarReservas();
        
        //Recorre una lista que contiene todas las reservas
        for(Reserva reserva : reservas)
        {
            //Compara el nombre otorgado con el nombre del archivo, para clasificar si la reserva pertenece a la persona
            if(reserva.nombre.equalsIgnoreCase(nombre))
                listaReservas.add(reserva);
        }
        
        return listaReservas;
    }
    
    public static void ReservarVuelo(String nombre, String vueloReserva){
    int numeroReservas=0;
    int id=0;
    try {
        //Se crea un SAXBuilder para poder parsear el archivo
     SAXBuilder builder = new SAXBuilder();
     //archivo que tiene las reservas
     File xmlFile = new File("reserva.xml");
     //Se crea el documento a traves del archivo
     Document doc = (Document) builder.build(xmlFile); 
     //se obtiene el padre del xml
     Element rootNode = doc.getRootElement();
     XMLOutputter xmlOutput = new XMLOutputter();
     Element reserva = new Element("reserva");  
     //se crea una lista de losh hijos reserva
     List list = rootNode.getChildren( "reserva" );
     //tamaño de la lista de reservas
     numeroReservas = list.size();
     //aqui se gerar el id automatico que sera la cantidad de nodos hijos mas 1
     id = numeroReservas+1;
        //se crea el nodo reserva con id         
        reserva.setAttribute(new Attribute("clave", Integer.toString(id) ));
        Element vuelo = new Element("vuelo"); 
        //se crean los hijos de reserva que son vuelo y nombre
        vuelo.setAttribute(new Attribute("clave", vueloReserva ));
        //se agregan el nodo hijo nombre en el nodo reserva 
        vuelo.addContent(new Element("nombre").setText(nombre));
        //se inserta el nodo vuelo en el nodo reserva
        reserva.addContent(vuelo);
        //se agrega el nodo reserva al nodo padre del xml
        doc.getRootElement().addContent(reserva);
        // display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
                //se guardan los cambios en el xml
		xmlOutput.output(doc, new FileWriter("reserva.xml"));
                
		System.out.println("Reserva agregada con id: " + id);
                
       
          } catch (IOException io) {
		io.printStackTrace();
	  } catch (JDOMException e) {
		e.printStackTrace();
	  }
    
    
    }
}
