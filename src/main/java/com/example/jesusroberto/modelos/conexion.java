package com.example.jesusroberto.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    static private String DB = "taqueria";
    static private String USER = "adminTacos";
    static private String PWD = "1234";
    static public Connection connection;
    public static void crearConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ DB ,USER,PWD);
            System.out.println("Conexión establecida con exito!! ;)");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
