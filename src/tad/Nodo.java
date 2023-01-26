package tad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Samu
 */
public class Nodo {
    private int dato;
    private Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public Nodo(int dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    
    public boolean hasNext(){
        return siguiente != null;
    }
    
    public Nodo next(){
        return siguiente;
    }
    
    public void setNext(Nodo nuevo){
        siguiente = nuevo;
    }
    
    public int getDato(){
        return dato;
    }
    
}
