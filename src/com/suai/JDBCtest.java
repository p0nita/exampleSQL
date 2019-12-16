package com.suai;

import java.sql.*;
import java.util.logging.*;
import java.util.Scanner;

public class JDBCtest {

    public static void main(String[] args) {
        Connection connection = null;
        int  i = 1;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/table", "postgres", "1707");
            System.out.println("Соединение установлено");
            Statement statement = null;
            statement = connection.createStatement();
            Scanner sc = new Scanner(System.in);
//            statement.executeUpdate("INSERT INTO users (ID, Nickname, Password) VALUES(1,'Rick', 'morty')");
//            while(i != 4) {
//                System.out.println("Введите ваше имя: ");
//                String n = sc.nextLine();
//                System.out.println("Введите ваш пароль: ");
//                String p = sc.nextLine();
//                statement.executeUpdate("INSERT INTO users (Nickname, Password) VALUES('" + n + "'" + ",'" + p + "')");
//                i++;
//            }
//            i = 2;
//            statement.executeUpdate("INSERT INTO users (ID, Nickname, Password) VALUES(2,'Rick', 'morty')");
            int result1;
//            while(i!=5) {
//                System.out.println("Введите количество побед: ");
////                char w = sc.next().charAt(0);
//                int w = sc.nextInt();
//                System.out.println("Введите общее число игр: ");
//                int t = sc.nextInt();
//                statement.executeUpdate("UPDATE users SET Wins = '" + w + "' WHERE ID = "+ i + "");
//                statement.executeUpdate("UPDATE users SET Total = '" + t + "' WHERE ID = " + i +"");
//                i++;
//            }
//            statement.executeUpdate("UPDATE users SET Nickname = 'admin' where ID = 1");
            ResultSet result2 = statement.executeQuery("SELECT * FROM users WHERE ID >= 1 AND ID < 5");
            while (result2.next()) {
                System.out.println( "Номер в базе: " + result2.getInt("ID")+ "    " + result2.getString("Nickname")+"    "+ result2.getInt("Wins")+ "\t" + result2.getInt("Total"));
            }
            System.out.println("Введите ваше имя: ");
            String name = sc.nextLine();
            System.out.println("Введите ваш пароль: ");
            String pass = sc.nextLine();
//            ResultSet res3 =  statement.executeQuery("SELECT count(*) FROM users");
            ResultSet res3 =  statement.executeQuery("SELECT * FROM users");
            boolean alert = false;
            while(res3.next()){
                if(name.equals(res3.getString("Nickname")) && pass.equals(res3.getString("Password"))){
                    System.out.println("ID: " + res3.getInt("ID"));
                    alert = true;
                }
            }
            if(!alert) System.out.println("ERROR! Неправильный никнейм или пароль!");
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
