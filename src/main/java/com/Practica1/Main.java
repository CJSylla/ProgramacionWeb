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

            System.out.println("\nTarea 4:");
            System.out.println("La cantidad de <form method='post'> es de: " + getCantForm(documento, "post"));
            System.out.println("La cantidad de <form method='get'> es de: " + getCantForm(documento, "get"));

            System.out.println("\nTarea 5:");
            getCantInputForm(documento);

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

    private static int getCantForm(Document html, String method) {
        return html.select("form[method='" + method + "']").size();
    }

    private static void getCantInputForm(Document html) {
        int indSuperior = 1;
        for(Element form : html.select("form")) {
            int indInferior = 1;
            System.out.println("\tForm #" + indSuperior + ":");
            for(Element input : form.select("input")) {
                System.out.println("El input #" + indInferior
                        + " es de tipo " + input.attr("type"));
                indInferior++;
            }
            indSuperior++;
        }
    }


}
