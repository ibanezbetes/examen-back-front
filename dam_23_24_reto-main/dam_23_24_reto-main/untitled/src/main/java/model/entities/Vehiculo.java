package model.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Vehiculo {

    private String matricula;
    private String modelo;
    private int kilometros;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }


    public static String fromArrayToJson(ArrayList<Pelicula> peliculas) {
        String resp = "[";
        for (Pelicula pelicula : peliculas) {
            resp += "{"
                    + "'titulo':'" + pelicula.getTitulo() + "', "
                    + "'trailer':'" + pelicula.getTrailer() + "',"
                    + " 'sinopsis':'" + pelicula.getSinopsis() + "', "
                    + "'fechaEstreno':" + pelicula.getFechaEstreno() + ", "
                    + "'duracion':" + pelicula.getDuracion()
                    + ", 'nVotos':" + pelicula.getnVotos() + ", 'sPuntuacion':"
                    + pelicula.getnVotos() + ", 'id':" + pelicula.getId() + ", "
                    + "'precio':" + pelicula.getPrecio() + "}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Vehiculo> vehiculos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(vehiculos);

        return resp;
    }
}
