package Carreras.BD;

import java.sql.*;

public class DBUtils {

    protected  static final String MYSQLDB="jdbc:mysql://localhost:3306/sofkacarrera";
    protected  static final String MYSQLDB_USER="esteban";
    protected  static final String MYSQLDB_PASSWORD="entrar";

    private static Connection connection=null;
    private static Statement statement=null;
    private static ResultSet resultSet=null;

    private static ResultSet ID= null;
    private static int IDGanador= 0;

    public static int getIDGanador() {
        return IDGanador;
    }

    public static void setIDGanador(int IDGanador) {
        DBUtils.IDGanador = IDGanador;
    }

    public static void setID(ResultSet ID) {
        DBUtils.ID = ID;
    }

    public static ResultSet getID() {
        return ID;
    }

//////////
public static void Connection() throws SQLException {
    connection= DriverManager.getConnection(MYSQLDB,MYSQLDB_USER,MYSQLDB_PASSWORD);
    statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
}

    public static  int validarGanadores(int posicion, String NombreConductor) throws SQLException {
        int valor;
        try {
            resultSet=statement.executeQuery("SELECT `ID`,`Cantidad` FROM `ganadores` WHERE  `Nombre` = '"+NombreConductor+"' AND `Posicion` = "+posicion);
            resultSet.next();
            valor=resultSet.getInt(2);
            DBUtils.setIDGanador(resultSet.getInt(1));
        }catch (SQLException e){
            valor=0;
        }
        return valor;
    }


public static int setGuardarGanadores(int posicion,String NombreConductor) throws SQLException {
    String sqlGanadores="INSERT INTO `ganadores` (`Nombre`, `Posicion`, `1`) " +
            "VALUES ('"+NombreConductor+"',"+posicion+",'"+1+"')";
    PreparedStatement preparedStatement=connection.prepareStatement(sqlGanadores);
    return preparedStatement.executeUpdate();
}


    public static int setActualizarGanador(int cantidad) throws SQLException {
        int Id=DBUtils.getIDGanador();
        cantidad ++;
        String sql="UPDATE `ganadores` SET `Cantidad` = "+cantidad+" where ID = "+Id;
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public static void desconectar() throws SQLException {
        if (statement!=null){statement.close(); }
        if (resultSet!=null){resultSet.close(); }
        if (connection!=null){connection.close(); }
    }
    ////////



    public static  void GetGanadores() throws SQLException {
        StringBuilder PrimerPuesto= new StringBuilder();
        StringBuilder SegundoPuesto= new StringBuilder();
        StringBuilder TercerPuesto= new StringBuilder();
        try {
            resultSet=statement.executeQuery("SELECT * FROM `ganadores`");
            while(resultSet.next()){
                switch (resultSet.getInt(3))
                {
                    case 1:
                       PrimerPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    case 2 :
                       SegundoPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    case 3:
                       TercerPuesto.append(" ").append(resultSet.getString(2)).append("    ").append(resultSet.getInt(4)).append("\n");
                        break;

                    default :
                        break;
                }
            }
            System.out.println("      Ganadores");
            System.out.println("    Primer Lugar");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(PrimerPuesto);
            System.out.println("    Segundo Lugar");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(SegundoPuesto);
            System.out.println("    Tercer Lugar ");
            System.out.println("  Nombre    Cantidad\n");
            System.out.println(TercerPuesto);
        }catch (SQLException ignored){
        }finally {
            DBUtils.desconectar();
        }
    }

}