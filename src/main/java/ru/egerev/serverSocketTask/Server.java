package ru.egerev.serverSocketTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;
    private static String city = "????";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    writer.println(city);
                    String answear = reader.readLine();
                    writer.println(checkWord(answear) ? "OK" : "NOT OK");

                } catch (IOException e) {
                    System.out.println("Не могу стартовать сервер");
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkWord(String answear) {
        String nameCityForCheck = city;
        if (nameCityForCheck.endsWith("ь") || nameCityForCheck.endsWith("ы")) {
            nameCityForCheck = nameCityForCheck.substring(0, nameCityForCheck.length() - 1);
        }
        if (nameCityForCheck.endsWith(answear.toLowerCase().substring(0, 1)) || city.equals("????")) {
            city = answear;
            return true;
        }
        return false;
    }


}
