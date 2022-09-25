package ru.egerev.serverSocketTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.RSAOtherPrimeInfo;

public class Client {

    private static final int PORT = 8080;
    private static final String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCALHOST, PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Считываем город
            System.out.println(reader.readLine());
            String answear = null;

            // Вводим и отправляем свой вариант города
            BufferedReader answearRead = new BufferedReader(new InputStreamReader(System.in));
            answear = answearRead.readLine();
            writer.println(answear);

            // Считываем вердикт по нашему варианту города от сервера
            System.out.println(reader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
