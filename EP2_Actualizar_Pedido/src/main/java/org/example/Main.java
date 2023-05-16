package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    static Connection conexion;
    public static void main(String[] args) throws SQLException {
        conectar();
        ActualizarPedido();
        cerrar();
    }

    private static void ActualizarPedido() throws SQLException {
        String query = "update pedido \n" +
                "set Fecha = ?, Monto = ?,IdCliente = ?, IdVendedor = ?," +
                " IdTransp = ?\n" +
                "where NroPedido = ?";

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,"1999-06-06");
            statement.setString(2,"300");
            statement.setString(3,"3");
            statement.setString(4,"3");
            statement.setString(5,"3");
            statement.setString(6,"1");
            statement.executeUpdate();
            System.out.println("Se actualizó correctamente");
        } catch (SQLException ex){
            throw ex;
        }
    }

    private static void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/floreria";
        conexion = DriverManager.getConnection(url,"root","root");
    }

    private static void cerrar() throws SQLException{
        if(conexion!=null){
            conexion.close();
        }
    }
}