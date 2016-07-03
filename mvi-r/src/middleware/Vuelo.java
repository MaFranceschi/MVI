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
public class Vuelo implements Serializable {
    
    public String id;
    public String linea;
    public String origen;
    public String destino;
    public String fecha;
    public String hora;
    public String capacidad;
    public String precio;

    public Vuelo(){
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLinea() {
        return linea;
    }
    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    

    
    public Vuelo(String id, String linea, String origen, String destino, String fecha, String hora, String capacidad, String precio) {
        this.id = id;
        this.linea = linea;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.precio = precio;
    }
    
     
     public static void EditarCapacidadVuelo(String vuelo){ 
        //se crea una lista de vuelos
ArrayList<Vuelo> listaVuelos = new ArrayList<Vuelo>();
//se llena la lista co todos los vuelos
listaVuelos = ConsultarVuelos();
//se crea un objeto vuelo para poder acceder manipular las funciones get y set
Vuelo objetoVuelo = new Vuelo();
int capacidadVuelo=0;
try {
                //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
                //archivo que tiene las reservas
        File xmlFile = new File("disponibilidad.xml");
                //objeto que caragra el archivo tipo document
        Document doc = (Document) builder.build(xmlFile);  
                //se obtiene el padre del xml
        Element rootNode = doc.getRootElement();
                //se obtiene el hijo "vuelo"
                List reservas = rootNode.getChildren("vuelo");
                //se declara un iterator para recorrer el archivo
                Iterator iter = reservas.iterator();
                //se recorre el archivo
                while(iter.hasNext()){
                Element reserva = (Element) iter.next();
                //se elimina el vuelo que se editara 
                    if(reserva.getAttributeValue("id").equals(vuelo)){
                        iter.remove();
                    }
                }
                
        XMLOutputter xmlOutput = new XMLOutputter();
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
                //se guardan los cambios
        xmlOutput.output(doc, new FileWriter("disponibilidad.xml"));
                
          } catch (IOException io) {
        io.printStackTrace();
      } catch (JDOMException e) {
        e.printStackTrace();
      }
//se recorre la lista de vuelos del xml
    for (int i=0; i< listaVuelos.size(); i++){
        // si vuelo en la lista donde esta parado el for es igual al vuelo que queremos editar de llena el objeto
        if(listaVuelos.get(i).getId().equals(vuelo)){
        capacidadVuelo = Integer.parseInt(listaVuelos.get(i).getCapacidad());
        capacidadVuelo = capacidadVuelo - 1;
        objetoVuelo.setId(listaVuelos.get(i).getId());
        objetoVuelo.setLinea(listaVuelos.get(i).getLinea());
        objetoVuelo.setOrigen(listaVuelos.get(i).getOrigen());
        objetoVuelo.setDestino(listaVuelos.get(i).getDestino());
        objetoVuelo.setFecha(listaVuelos.get(i).getFecha());
        objetoVuelo.setHora(listaVuelos.get(i).getHora());
        objetoVuelo.setCapacidad(Integer.toString(capacidadVuelo));
        objetoVuelo.setPrecio(listaVuelos.get(i).getPrecio());
        }
        }
// se llama a la funcion editar vuelo que insertara el vuelo de nuevo en el xml pero con la capacidad nueva
EditarVuelo(objetoVuelo.getId(),objetoVuelo.getLinea(),objetoVuelo.getOrigen(), objetoVuelo.getDestino(), objetoVuelo.getFecha(), 
        objetoVuelo.getHora(), objetoVuelo.getCapacidad(), objetoVuelo.getPrecio());
    
    }
    
    public static void EditarVuelo(String id,String linea,String origen, String destino, String fecha, String hora, String capacidad, String precio) {
    int numeroVuelos=0;
   
    try {
        //Se crea un SAXBuilder para poder parsear el archivo
     SAXBuilder builder = new SAXBuilder();
     //archivo con los vuelos
     File xmlFile = new File("disponibilidad.xml");
     ////objeto que caragra el archivo tipo document
     Document doc = (Document) builder.build(xmlFile);  
     //nodo raiz del xml
     Element rootNode = doc.getRootElement();
     XMLOutputter xmlOutput = new XMLOutputter();
     //se crea nodo vuelo
     Element vuelo = new Element("vuelo");  
     //se crea lista de vuelos
     List list = rootNode.getChildren( "vuelo" );   
     //se guarda el tamaño de la lista
     numeroVuelos = list.size();
     //id autogenerado de los vuelos
     
        //se crea el nodo de vuelos          
        vuelo.setAttribute(new Attribute("id", (id) ));
        vuelo.addContent(new Element("linea").setText(linea));
        vuelo.addContent(new Element("origen").setText(origen));
        vuelo.addContent(new Element("destino").setText(destino));
        vuelo.addContent(new Element("fecha").setText(fecha));
        vuelo.addContent(new Element("hora").setText(hora));
        vuelo.addContent(new Element("capacidad").setText(capacidad));
        vuelo.addContent(new Element("precio").setText(precio));
        //se agrega el nodo vuelo al noda raiz
        doc.getRootElement().addContent(vuelo);
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
                //guarda cambios en el xml
        xmlOutput.output(doc, new FileWriter("disponibilidad.xml"));
                
        System.out.println("Vuelo agregado!");
                
          } catch (IOException io) {
        io.printStackTrace();
      } catch (JDOMException e) {
        e.printStackTrace();
      }
    
    }
    
    public static void CargarVuelo(String linea,String origen, String destino, String fecha, String hora, String capacidad, String precio) {
    int numeroVuelos=0;
    int id=0;
    try {
        //Se crea un SAXBuilder para poder parsear el archivo
     SAXBuilder builder = new SAXBuilder();
     //archivo con los vuelos
     File xmlFile = new File("disponibilidad.xml");
     ////objeto que caragra el archivo tipo document
     Document doc = (Document) builder.build(xmlFile);  
     //nodo raiz del xml
     Element rootNode = doc.getRootElement();
     XMLOutputter xmlOutput = new XMLOutputter();
     //se crea nodo vuelo
     Element vuelo = new Element("vuelo");  
     //se crea lista de vuelos
     List list = rootNode.getChildren( "vuelo" );   
     //se guarda el tamaño de la lista
     numeroVuelos = list.size();
     //id autogenerado de los vuelos
     id = numeroVuelos+1;
        //se crea el nodo de vuelos          
        vuelo.setAttribute(new Attribute("id", Integer.toString(id) ));
        vuelo.addContent(new Element("linea").setText(linea));
        vuelo.addContent(new Element("origen").setText(origen));
        vuelo.addContent(new Element("destino").setText(destino));
        vuelo.addContent(new Element("fecha").setText(fecha));
        vuelo.addContent(new Element("hora").setText(hora));
        vuelo.addContent(new Element("capacidad").setText(capacidad));
        vuelo.addContent(new Element("precio").setText(precio));
        //se agrega el nodo vuelo al noda raiz
        doc.getRootElement().addContent(vuelo);
        // display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
                //guarda cambios en el xml
		xmlOutput.output(doc, new FileWriter("disponibilidad.xml"));
                
		System.out.println("Vuelo agregado con: "+id);
                
          } catch (IOException io) {
		io.printStackTrace();
	  } catch (JDOMException e) {
		e.printStackTrace();
	  }
    
    }
    
    
    
    public static ArrayList<Vuelo> ConsultarVuelos(){
        

        ArrayList<Vuelo> listaVuelos = new ArrayList<Vuelo>();
        
 //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File( "disponibilidad.xml" );
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile ); 
        //Se obtiene la raiz 'disponibilidad'
        Element rootNode = document.getRootElement();
        //Se obtiene la lista de hijos de la raiz 'vuelo'
        List list = rootNode.getChildren( "vuelo" );
        //Se recorre la lista de hijos de 'disponibilidad'
        for ( int i = 0; i < list.size(); i++ )
        {
            Vuelo objeto = new Vuelo();
            //Se obtiene el elemento 'vuelo'
            Element tabla = (Element) list.get(i);
            //Se obtiene el atributo 'id' que esta en el tag 'vuelo'
            String idVuelo = tabla.getAttributeValue("id");
            objeto.setId(idVuelo);
            //Se obtiene la lista de hijos del tag 'vuelo'   
            List lista_campos = tabla.getChildren(); 
            //Se recorre la lista de campos
            for ( int j = 0; j < lista_campos.size(); j++ ){
                //Se obtiene el elemento 'campo'
                Element campo = (Element)lista_campos.get( j );
                //Se obtienen los valores que estan entre los tags '<campo></campo>'
                //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                String linea = campo.getTextTrim();
                //dependiendo de la etiqueta que esta parada guarda en un atributo del objeto
                if (campo.getName()=="linea"){
                objeto.setLinea(linea);
                }
                if (campo.getName()=="origen"){
                objeto.setOrigen(linea);
                }
                if (campo.getName()=="destino"){
                objeto.setDestino(linea);
                }
                if (campo.getName()=="fecha"){
                objeto.setFecha(linea);
                }
                if (campo.getName()=="hora"){
                objeto.setHora(linea);
                }
                if (campo.getName()=="capacidad"){
                objeto.setCapacidad(linea);
                }
                if (campo.getName()=="precio"){
                objeto.setPrecio(linea);
                }
                //System.out.println( "\t"+linea+"\t\t");
            
            }
            //se inserta el objeto de vuelos en la lista de vuelos
            listaVuelos. add(objeto);
            //System.out.println( "");
        }
        //listaVuelos. add(objeto);
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }// se retorna la lista de vuelos
        return listaVuelos;
    
    }
}
