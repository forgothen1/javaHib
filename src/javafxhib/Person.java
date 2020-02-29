/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxhib;

/**
 *
 * @author Brane
 */
public class Person {
     
   int id;
    String ime;
    String prezime;
    String adresa;
    String dohodak;
    
    
   public int getId(){
    return id;}
    public void setId(int id)
    {this.id=id;} 
    public String getIme(){
    return ime;}
    public void setIme(String ime){
    this.ime=ime;}
    public String getPrezime(){
    return prezime;}
    public void setPrezime(String prezime){
    this.prezime=prezime;
    }
    public String getAdresa(){
    return adresa;}
    public void setAdresa(String adresa){
    this.adresa=adresa;}
    public String getDohodak()
    {return dohodak;}
    public void setDohodak(String dohodak)
    {this.dohodak=dohodak;}
    public Person(){}
  public Person (int id){
  this.id=id;
  }
  
  public Person (int id, String ime, String prezime, String adresa, String dohodak){
  this.id=id;
  this.ime=ime;
  this.prezime=prezime;
  this.adresa=adresa;
  this.dohodak=dohodak;
  }
    
    public Person(String ime,String prezime,String adresa,String dohodak){
 
    this.ime=ime;
    this.prezime=prezime;
    this.adresa=adresa;
    this.dohodak=dohodak;
    }
     @Override
    public String toString() {
        return id +"  "+  ime + ";" + prezime+";"+ adresa+";"+dohodak;
    }
    
}
