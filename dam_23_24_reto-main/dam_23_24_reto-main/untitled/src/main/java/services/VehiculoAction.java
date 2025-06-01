package services;

import model.dao.DAO;
import model.dao.VehiculoDAO;
import model.entities.Vehiculo;
import model.factory.DatabaseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class VehiculoAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FILTER":
                cadDestino = findByFilter(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        VehiculoDAO vehiculoDao = new VehiculoDAO(DatabaseFactory.POSTGRE); //SELECT * FROM PELICULAS
        ArrayList<Vehiculo> vehiculos = vehiculoDao.findAllVehicles(null);
        return Vehiculo.toArrayJSon(vehiculos);//[{}, {}]
    }

    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        VehiculoDAO vehiculoDAO = new VehiculoDAO(DatabaseFactory.POSTGRE);
        String tipo = request.getParameter("FILTRO");
        Vehiculo pelicula = new Vehiculo();
        pelicula.setMatricula(tipo);
        ArrayList<Vehiculo> vehiculos = vehiculoDAO.filterType(tipo);
        return Vehiculo.toArrayJSon(vehiculos);
    }
}
