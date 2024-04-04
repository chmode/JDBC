package services;

import entites.Personne;

import java.sql.SQLException;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import utils.Database;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePersonneFile implements IService<Personne> {

    private File f;
   

    public ServicePersonneFile() {
        f = new File("personne.csv");

    }

    @Override
    public void ajouter(Personne t)  {
        try {
        	BufferedWriter bw = new BufferedWriter(new FileWriter(f, true)); //tr:app
        	bw.write( t.getNom() + ";" + t.getPrenom() + ";" + t.getAge());
        	bw.newLine();
        	bw.close();
			System.out.println("Saved to file: "+ f.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
   
            

    @Override
    public boolean delete(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean update(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Personne> readAll() {
    List<Personne> arr=new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
    String l;
     while ((l=br.readLine()) != null) {                
               String[] data = l.split(";");
               if(l.length() > 0) {
            	   String nom=data[0];
                   String prenom=data[1];
                   int age=Integer.parseInt(data[2]);
                   Personne p=new Personne(nom, prenom, age);
                   arr.add(p);
               }
               
     }
     
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return arr;
    }
}
