package model.dao;

import model.entities.Pelicula;
import model.entities.Vehiculo;
import model.factory.DatabaseFactory;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculoDAO implements DAO<Pelicula, Integer>{
    private final String SQL_FINDALL = "SELECT * FROM vehiculo WHERE 1=1 ";

    private final String SQL_FIND_BY_FILTER =
            "SELECT p.titulo, p.descripcion, p.ano, c.nombre " +
                    "FROM peliculas p " +
                    "INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula " +
                    "INNER JOIN categorias c ON pc.id_categoria = c.id ";



    private final String SQL_SEARCH_Start =
            "SELECT * FROM vehiculo WHERE UPPER(matricula) LIKE UPPER('%";
    private final String SQL_SEARCH_Final = "%')";

    private final String SQL_ADD
            = "INSERT INTO `pelicula` (`Titulo`, `Precio`, `Duracion`, `Trailer`, `Sinopsis`, `N_Votos`, `S_Puntuacion`, `Fecha_Estreno`, `URL`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `pelicula` WHERE ID_Pelicula=";
    private final String SQL_UPDATE = "UPDATE `pelicula` SET ";
    private MotorSQL motorSql;

    public VehiculoDAO(String db) {
        motorSql = DatabaseFactory.getDatabase(db);
    }

    @Override
    public int add(Pelicula bean) {
        return 0;
    }

    @Override
    public int delete(Integer e) {
        return 0;
    }

    @Override
    public int update(Pelicula bean) {
        return 0;
    }

    @Override
    public ArrayList<Pelicula> findAll(Pelicula bean) {
        return null;
    }


    public ArrayList<Vehiculo> findAllVehicles(Vehiculo bean) {
        ArrayList<Vehiculo> Vehiculos = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {
            //1º)
            motorSql.connect();


            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Vehiculo vehiculo= new Vehiculo();

                vehiculo.setMatricula(rs.getString(1));
                vehiculo.setModelo(rs.getString(2));
                vehiculo.setKilometros(rs.getInt(3));


                Vehiculos.add(vehiculo);

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return Vehiculos;
    }
    public ArrayList<Pelicula> findAllByCategory(String category) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = SQL_FIND_BY_FILTER
                + " WHERE c.nombre like '%" + category+ "%'";

        // SELECT p.titulo, p.descripcion, p.ano, c.nombre
        //FROM peliculas p
        //INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula
        //INNER JOIN categorias c ON pc.id_categoria = c.id
        //WHERE c.nombre like ('%Drama%');

        try {
            //1º)
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Pelicula pelicula = new Pelicula();
                String titulo = rs.getString(1);
                String descp = rs.getString(2);
                int anyo = rs.getInt(3);

                pelicula.setTitulo(titulo);
                pelicula.setSinopsis(descp);
                pelicula.setFechaEstreno(String.valueOf(anyo));
                peliculas.add(pelicula);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return peliculas;
    }
    public ArrayList<Vehiculo> filterType(String tipo) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        String sql = (SQL_SEARCH_Start+tipo+SQL_SEARCH_Final);
        try {
            //1º)
            motorSql.connect();


            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Vehiculo vehiculo= new Vehiculo();

                vehiculo.setMatricula(rs.getString(1));
                vehiculo.setModelo(rs.getString(2));
                vehiculo.setKilometros(rs.getInt(3));

                vehiculos.add(vehiculo);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return vehiculos;
    }

}
