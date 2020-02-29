    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxhib;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Brane
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    public TableView <Person> table;
    @FXML
      public TableColumn<Person, String> ime;
     @FXML
      public TableColumn<Person, String> prezime;
      @FXML
      public TableColumn<Person, String> adresa;
       @FXML
      public TableColumn<Person, Integer> id;
       @FXML
       public TableColumn<Person,Integer> dohodak;
       @FXML
       public Button button2;
         Button smash = new Button("dodaj");
         @FXML
         public TextField pretrazivac;
         @FXML
         ChoiceBox box1;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

       @FXML //prozor za brisanje 
    private void paneRemove(ActionEvent event) 
       {
      
       GridPane pd = new GridPane();
         Stage s = new Stage();
       Scene s1= new Scene(pd,200,200);
       Button click = new Button("nadji");
        
       TextField tf =new TextField();
       pd.add(click,0,0);
         pd.add(tf,0,1);
         click.setOnAction((ActionEvent event1) -> {
             Session session = HibernateUtil.createSessionFactory().openSession();
             Transaction tx = null;
             tx=session.beginTransaction();
             
             
             Integer ie= Integer.parseInt(tf.getText()); // izvlaci broj iz textfielda i pretvara ga u int
             // session prima samo  tip  person;
             Person person = (Person) session.load(Person.class, ie);
             session.delete(person);
             tx.commit();
             
             session.close();
       });
     
        
       s.setScene(s1);
       s.show();
 table.refresh();
       }
       //pravljenje  novog prozora za dodavanje person
       @FXML
       private void dodavanje(ActionEvent event)
       { 
           Label label1 = new Label("ime");
       Label label2 = new Label("prezime");
       Label label3 = new Label("adresa");
       Label label4 = new Label("dohodak"); 
           TextField field1 = new TextField();
            TextField field2 = new TextField();
             TextField field3 = new TextField();
              TextField field4 = new TextField();
       GridPane gridPane = new GridPane();
      gridPane.add(label1,0,0);
            gridPane.add(field1,0,1);
         gridPane.add(label2,1,0);
       gridPane.add(field2,1,1);
          gridPane.add(label3,0,2);
       gridPane.add(field3,0,3);
          gridPane.add(label4,1,2);
       gridPane.add(field4,1,3);
       
    
       Stage s = new Stage();
       Scene s1= new Scene(gridPane,400,200);
       
       s.setScene(s1);
       s.show();
    
    gridPane.add(smash,2,3 );
smash.setOnAction(new EventHandler<ActionEvent>(){
               @Override
               public void handle(ActionEvent event) {
         Person person = new Person(field1.getText(),field2.getText(),field3.getText(),field4.getText());
                 System.out.println(person.toString());
       Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
       try {
tx = session.beginTransaction();
session.save(person);

tx.commit();
  System.out.println("Inserted Successfully");
} catch (HibernateException e) {
if (tx != null) {
tx.rollback();
}
System.out.println(e);
} finally {
HibernateUtil.createSessionFactory().close();
}
            
               
             table.refresh();  }
                 
}); 
       }
    
       @FXML //editing content from table
    private void eidtable (ActionEvent event){
             GridPane pane = new GridPane();
      Stage s = new Stage();
       Scene s1= new Scene(pane,400 ,200);
         Button click = new Button("nadji");
         
       TextField tf =new TextField();
       pane.add(click,0,0);
         pane.add(tf,0,1);
         click.setOnAction(new EventHandler<ActionEvent>(){
         @Override
           public void handle(ActionEvent event) {
         //  i=tf.getText(); // to get id
           Person person= new Person();
         pane.getChildren().remove(click); //clearing of stuff we dont need
         pane.getChildren().remove(tf);
             Label label1 = new Label("ime"); // adding simular look like in adding area
             Label label2 = new Label("prezime");
             Label label3 = new Label("adresa");
             Label label4 = new Label("dohodak"); 
            TextField field1 = new TextField(person.ime); //ading values to textfields that we get from ID
            TextField field2 = new TextField(person.prezime);
            TextField field3 = new TextField(person.adresa);
            TextField field4 = new TextField(person.dohodak);
            smash.setOnAction(new EventHandler<ActionEvent>(){
                         @Override // true button changing values in table on graphic side
                         public void handle(ActionEvent event) {
         Session session = HibernateUtil.createSessionFactory().openSession();
    Transaction tx = null;
   tx=session.beginTransaction();
   // izvlaci broj iz textfielda i pretvara ga u int
 Integer ie= Integer.parseInt(tf.getText()); 

             // session prima samo  tip  person;
             Person person = (Person) session.get(Person.class, ie);
         if(field1.getText()!=null){  person.setIme(field1.getText()); }
          if(field2.getText()!=null){  person.setPrezime(field2.getText()); }
         if(field3.getText()!=null){   person.setAdresa(field3.getText()); } 
         if(field4.getText()!=null){   person.setDohodak(field4.getText()); }
           
           
             tx.commit();
             
             session.close();
                            }
                                });
                pane.add(smash,2,3 );
               pane.add(label1,0,0);
               pane.add(field1,0,1);
               pane.add(label2,1,0);
               pane.add(field2,1,1);
               pane.add(label3,0,2);
               pane.add(field3,0,3);
               pane.add(label4,1,2);
               pane.add(field4,1,3);
         
           }
         });
       s.setScene(s1);
       s.show();
       table.refresh();
    }
    
     @FXML
    private void pretraga (ActionEvent event) throws SQLException
    {
    
       
           table.getItems().clear();
    
    String od = (String) box1.getValue();
     Session session = HibernateUtil.createSessionFactory().openSession();
   
Transaction tx = null;
//String nesto= "from Person " ;
//Query query = session.createQuery(nesto);
List <Person> person= null;
try {
tx = session.beginTransaction();

    switch (od){
        case "id": 
        
        { 
            String nesto= "from Person where id =  "+pretrazivac.getText()  ;
Query query = session.createQuery(nesto);
            person= query.list();
            table.getItems().addAll(person);
        }
        break;
        case "ime":
        {String nesto= "from Person where name = '"+pretrazivac.getText()+"'" ;
        System.out.println(nesto);
Query query = session.createQuery(nesto);
            person= query.list();
            table.getItems().addAll(person);}
        break;
        case "prezime":
         { 
            String nesto= "from Person where prezime =  '"+pretrazivac.getText()+"'"  ;
Query query = session.createQuery(nesto);
            person= query.list();
            table.getItems().addAll(person);
        }  break;
        case "adresa":
        { 
            String nesto= "from Person where adresa =   '"+pretrazivac.getText()+"'" ;
Query query = session.createQuery(nesto);
            person= query.list();
            table.getItems().addAll(person);
        }
             break;
        case "dohodak":
        { 
            String nesto= "from Person where dohodak =  "+pretrazivac.getText()  ;
Query query = session.createQuery(nesto);
            person= query.list();
            table.getItems().addAll(person);
        }
             break;
             default:
             {System.out.println("omaseno ");}
    }
    tx.commit();

} catch (HibernateException e) {
if (tx != null) {
tx.rollback();
}
System.out.println(e);
} finally {
 HibernateUtil.createSessionFactory().close();

}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         box1.getItems().addAll("id","ime","prezime","adresa","dohodak");
     
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
       prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
       adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
       dohodak.setCellValueFactory(new PropertyValueFactory<>("dohodak"));
       
       Session session = HibernateUtil.createSessionFactory().openSession();
   
Transaction tx = null;
String nesto= "from Person" ;
Query query = session.createQuery(nesto);
List <Person> person= null;
try {
tx = session.beginTransaction();
person= query.list();

tx.commit();

} catch (HibernateException e) {
if (tx != null) {
tx.rollback();
}
System.out.println(e);
} finally {
 HibernateUtil.createSessionFactory().close();

}
 table.getItems().addAll(person);

    }    
    
}
