package Carreras.Carros;
import Carreras.Conductores.conductor;
public class carro {

    private int Numerocarro;
    private int DistanciaRecorrida;
    private conductor conductor;


    public carro(int numerocarro, Carreras.Conductores.conductor conductor) {
        Numerocarro = numerocarro;
        DistanciaRecorrida = 0;
        this.conductor = conductor;
    }


    public  int acelerar(){
        int numeroAleatorio = (int) (Math.random()*6+1);
        int Distancia=(numeroAleatorio*100);
        this.DistanciaRecorrida=this.DistanciaRecorrida+Distancia;
        return Distancia;
    }

    public int getNumerocarro() {
        return Numerocarro;
    }

    public void setNumerocarro(int numerocarro) {
        Numerocarro = numerocarro;
    }

    public int getDistanciaRecorrida() {
        return DistanciaRecorrida;
    }

    public void setDistanciaRecorrida(int distanciaRecorrida) {
        DistanciaRecorrida = distanciaRecorrida;
    }

    public Carreras.Conductores.conductor getConductor() {
        return conductor;
    }

    public void setConductor(Carreras.Conductores.conductor conductor) {
        this.conductor = conductor;
    }
}
