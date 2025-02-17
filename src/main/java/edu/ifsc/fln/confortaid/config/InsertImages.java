package edu.ifsc.fln.confortaid.config;

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

        String sqlUsuario = "INSERT INTO foto_usuario (usuario_id, foto) VALUES (?, ?)";
        String sqlServico = "INSERT INTO foto_servico (servico_id, foto) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Insert images into
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUsuario)) {
                // Insert images into foto_cliente
                try (InputStream inputStream = new FileInputStream("src/main/resources/img/01ana.jpg")) {
                    pstmt.setInt(1, 1);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/01bia.jpg")) {
                    pstmt.setInt(1, 2);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                // Insert images into foto_profissional
                try (InputStream inputStream = new FileInputStream("src/main/resources/img/01dany.jpg")) {
                    pstmt.setInt(1, 3);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/01emy.jpg")) {
                    pstmt.setInt(1, 4);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }
            }


            try (PreparedStatement pstmt = conn.prepareStatement(sqlServico)) {


                try (InputStream inputStream = new FileInputStream("src/main/resources/img/04dany.jpg")) {
                    pstmt.setInt(1, 1);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/03dany.jpg")) {
                    pstmt.setInt(1, 2);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/02emy.jpg")) {
                    pstmt.setInt(1, 3);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/03emy.jpg")) {
                    pstmt.setInt(1, 4);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

                try (InputStream inputStream = new FileInputStream("src/main/resources/img/02dany.jpg")) {
                    pstmt.setInt(1, 5);
                    pstmt.setBlob(2, inputStream);
                    pstmt.executeUpdate();
                }

            }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    