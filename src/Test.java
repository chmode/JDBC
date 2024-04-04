import entites.Personne;
import services.IService;
import services.ServicePersonne;
import services.ServicePersonneFile;
import utils.Database;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
    
    public static void main(String[] args) {
       //ServicePersonne ser  = new ServicePersonne();
        //ServicePersonneFile ser  = new ServicePersonneFile();

       IService<Personne> ser ;
       String version  = "db";
      
       if(version.equals("file")) {
    	   ser = new ServicePersonneFile();
       }else {
    	   ser = new ServicePersonne();
       }
        
        
        
        Personne p1 = new Personne("kamali", "Jamali", 10);
        Personne p2 = new Personne("Bakouri", "Salim", 10);
        
        try {      
            //ser.ajouter1(p2);
        	ser.ajouter(p1);
            ser.ajouter(p2);
            List<Personne> list = ser.readAll();
            System.out.println("Before delete : "+list);
            ser.delete(p2);
            list = ser.readAll();
            System.out.println("After delete : "+list);
            
            
            } catch (Exception ex) {      
            	System.out.println(ex);    
            }
    }
    
}