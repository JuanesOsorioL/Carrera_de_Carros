package Carreras.Pistas;
import Carreras.Carros.carro;

import java.util.ArrayList;


public class pista  {

    private String Nombre;
    private int Kilometro;
    private int Carriles;
   // private ArrayList <carro>carro ;
   ArrayList <carro> carros =new ArrayList<carro>();


    public pista( String Nombre, int Kilometro, int Carriles) {
        this.Nombre = Nombre;
        this.Kilometro = Kilometro;
        this.Carriles = Carriles;

    }


    public ArrayList getCarro() {
        return carros;
    }
/* public void setCarro(ArrayList carro) {
        this.carro = carro;
    }
    */

    public void setCarro(carro car) {
        this.carros.add(car);
    }


    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getKilometro() {
        return this.Kilometro;
    }

    public void setKilometro(int Kilometro) {
        this.Kilometro = Kilometro;
    }

    public int getCarriles() {
        return this.Carriles;
    }

    public void setCarriles(int Carriles) {
        this.Carriles = Carriles;
    }
}