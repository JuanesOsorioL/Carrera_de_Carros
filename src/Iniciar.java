
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Carreras.BD.DBUtils;
import Carreras.Conductores.conductor;
import Carreras.Carros.carro;
import Carreras.Pistas.pista;
import java.sql.*;

public class Iniciar {

    public static void main(String[] args) throws SQLException {

        Scanner entrada=new Scanner(System.in);

        int menu ;
        System.out.println("Bienvenido a La Fórmula 1 ");
        System.out.println(" Ingrese el numero de la opción Que desea hacer");
        System.out.println("1. Iniciar partida Nueva");
        System.out.println("2. Ver lista de ganadores");
        menu =Integer.parseInt(entrada.nextLine());

        switch (menu){

            case 1:
                System.out.print("Ingrese el Nombre de la Pista : ");
                String Nombre =entrada.nextLine();
                System.out.print("Ingrese la Longitud de la Pista "+"'"+Nombre+"'"+" en Km: ");
                int Kilometro =(Integer.parseInt(entrada.nextLine())*1000);

                int Carriles = 0;
                boolean Control = true;
                while ( Control ) {
                    System.out.print("Ingrese la Cantidad de carriles o Cantidad de Jugadores que participaran de la carrera ");
                    Carriles =Integer.parseInt(entrada.nextLine());
                    if (Carriles>=3){
                        Control = false;
                    }
                    else{
                        System.out.println("Ingresa minimo 3 participantes.");
                    }
                };
                carro Carro;
                conductor Conductor;
                pista Pista = new pista(Nombre,Kilometro,Carriles);
                for (int i = 1; i <= Carriles; i++){
                    System.out.print("Ingrese el Nombre del Conductor Para el Jugador # "+i+" : ");
                    String NombreConductor=entrada.nextLine();
                    Conductor= new conductor(NombreConductor);

                    System.out.print("Ingrese el Numero del Carro del Jugador "+NombreConductor+" : ");
                    int NumeroCarro =Integer.parseInt(entrada.nextLine());
                    Carro=new carro(NumeroCarro,Conductor);

                    Pista.setCarro(Carro);
                }
                ArrayList <carro> podio =new ArrayList<carro>();
                Control=true;
                int posicionPodio=1;
                boolean Controlciclo = true;
                System.out.println("en sus marcas!!");
                System.out.println("Listos!!");
                System.out.println("Fuera!!!! Go Go GO");


                while (Controlciclo){
                    for (int i = 0; i< Pista.getCarro().size(); i++) {
                       Carro= (carro) Pista.getCarro().get(i);

                        if (posicionPodio!=1){
                            for (carro c:podio ) {
                               if(c.getNumerocarro()==Carro.getNumerocarro()) {
                                   Control=false;
                                   break;
                               }
                            }
                        }

                        if (Control){
                            System.out.println("Turno del Jugador :"+Carro.getConductor().getNombre());
                            System.out.println("avanza una distancia de : "+ Carro.acelerar());
                            System.out.println("distancia total recorrida es : "+ Carro.getDistanciaRecorrida());
                            if (Carro.getDistanciaRecorrida()>Pista.getKilometro()){
                                if (posicionPodio<=3){
                                    podio.add(Carro);
                                    posicionPodio++;
                                }else{
                                    Controlciclo=false;
                                }
                            }
                        }
                        Control=true;
                    }
                }
             ////////

                 try {
                     DBUtils.Connection();
                     for (int i = 0; i< podio.size(); i++) {
                        int posicion=i+1;
                        int cantidad = DBUtils.validarGanadores(posicion,podio.get(i).getConductor().getNombre());
                        if (cantidad==0){
                            int resulta = DBUtils.setGuardarGanadores(posicion,podio.get(i).getConductor().getNombre());
                            if (resulta==1){
                                System.out.println("Participante "+ podio.get(i).getConductor().getNombre()+" Se guardo como en la posicion # "+posicion);
                            }else{
                                System.out.println("Participante "+ podio.get(i).getConductor().getNombre()+" No se guardo en la BD de Ganadores");
                            }
                        }else{
                            int resulta = DBUtils.setActualizarGanador(cantidad);
                            if (resulta==1){
                                System.out.println("Participante "+ podio.get(i).getConductor().getNombre()+" Se guardo como en la posicion # "+posicion);
                            }else{
                                System.out.println("Participante "+ podio.get(i).getConductor().getNombre()+" No Se Actualizo en la BD");
                            }
                        }
                     }
                 }catch (SQLException e) {
                    e.printStackTrace();
                }
                 finally {
                     DBUtils.desconectar();
                 }
                break;

            case 2:
               DBUtils.Connection();
                DBUtils.GetGanadores();

                break;

            default :
                break;
        }

        }
                 /*

        try {
            DBUtils.Connection();
        for (int i = 0; i< 3; i++) {
            int posicion=i+1;

          //  int cantidad = DBUtils.validarGanadores(posicion,"carlos");//0 si no existe
            int cantidad = DBUtils.setGuardarGanadores(posicion,"carlos");

            ///    int cantidad = DBUtils.validarGanadores(posicion,podio.get(i).getConductor().getNombre());
         //   if (cantidad==0){
           //     DBUtils.setGuardarGanadores(posicion,podio.get(i).getConductor().getNombre());
          //  }else{
          //      DBUtils.setActualizarGanador(cantidad);
        //    }
            System.out.println(cantidad);
            //

        }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtils.desconectar();
        }
*/

    }

