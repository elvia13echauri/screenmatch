package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=5abc53fa";
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraMenu(){
        System.out.println("Porfavor escribe el nombre de la serie que deses buscar: ");
        //buscando datos generales de la serie
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //buscando datos de las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE +
                    nombreSerie.replace(" ", "+") +
                    "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        
        //mostrar solo el titulo de los episodios para las temporadas
//        for (int i = 0; i < datos.totalDeTemporadas() ; i++){
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        temporadas.forEach(t -> t.episodios()
                .forEach(e -> System.out.println(e.titulo())));

        //Convertir todas la informacion en una lista de tipo DatosEpisodio
        List<DatosEpisodio>

    }
}
