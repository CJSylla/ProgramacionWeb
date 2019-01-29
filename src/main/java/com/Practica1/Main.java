package com.Practica1;

import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("URL a analizar: ");
        String url = scanner.next();

        try {
            // Para Establecer la conexión con la URL
            Connection conexion = Jsoup.connect(url);

            // Para obtener el recurso al que pertenece la URL
            Connection.Response respuesta = conexion.execute();

            // Para obtener el documento HTML de la URL
            Document documento = conexion.get();

            System.out.println("\nTarea 1:");
            System.out.println("La cantidad de lineas del recurso es de: " + getCantLineasRecurso(respuesta));

            System.out.println("\nTarea 2:");
            System.out.println("La cantidad de <p> es de: " + getCantParrafos(documento));

            System.out.println("\nTarea 3:");
            System.out.println("La cantidad de <img> dentro de <p> es de: " + getCantImagParrafo(documento));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int getCantLineasRecurso(Connection.Response recurso) {
        return recurso.body().split("\n").length;
    }

    private static int getCantParrafos(Document html) {
        return html.select("p").size();
    }

    private static int getCantImagParrafo(Document html) {
        return html.select("p img").size();
    }

}
