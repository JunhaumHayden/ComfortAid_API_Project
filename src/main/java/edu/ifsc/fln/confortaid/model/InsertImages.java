package edu.ifsc.fln.confortaid.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertImages {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:comfortaid";
        String user = "sa";
        String password = "";

        String sqlCliente = "INSERT INTO foto_cliente (cliente_id, foto) VALUES (?, ?)";
        String sqlProfissional = "INSERT INTO foto_profissional (profissional_id, foto) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Create tables if they do not exist
                try (PreparedStatement createTableStmt = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS foto_cliente (cliente_id INT PRIMARY KEY, foto BLOB)")) {
                    createTableStmt.execute();
                }
                try (PreparedStatement createTableStmt = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS foto_profissional (profissional_id INT PRIMARY KEY, foto BLOB)")) {
                    createTableStmt.execute();
                }
                // Insert images into foto_cliente
                try (PreparedStatement pstmt = conn.prepareStatement(sqlCliente)) {
                    try (InputStream inputStream = new FileInputStream("src/main/resources/img/camera.png")) {
                        pstmt.setInt(1, 1);
                        pstmt.setBlob(2, inputStream);
                        pstmt.executeUpdate();
                    }

                    try (InputStream inputStream = new FileInputStream("src/main/resources/img/camera.png")) {
                        pstmt.setInt(1, 2);
                        pstmt.setBlob(2, inputStream);
                        pstmt.executeUpdate();
                    }
                }

                // Insert images into foto_profissional
                try (PreparedStatement pstmt = conn.prepareStatement(sqlProfissional)) {
                    try (InputStream inputStream = new FileInputStream("src/main/resources/img/camera.png")) {
                        pstmt.setInt(1, 1);
                        pstmt.setBlob(2, inputStream);
                        pstmt.executeUpdate();
                    }

                    try (InputStream inputStream = new FileInputStream("src/main/resources/img/camera.png")) {
                        pstmt.setInt(1, 2);
                        pstmt.setBlob(2, inputStream);
                        pstmt.executeUpdate();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    