package dao;

import model.Autor;
import model.Carti;
import model.Genuri;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CartiDao {
    static String connectionUrl = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String user = "root";
    private static final String password = "daniel";


    public Carti findById(int id) {
        Carti carti = new Carti();
        String query = "select c.id, c.denumire, c.editura, c.an_aparitie, a.id, a.nume, a.prenume, g.id,g.denumire from (carti c " +
                "inner join autor a on c.id_autor=a.id " +
                "inner join genuri g on c.id_gen=g.id) " +
                "where c.id= " + id;


        try {
            Connection connection = DriverManager.getConnection(connectionUrl, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                carti.setId(resultSet.getInt(1));
                carti.setDenumire(resultSet.getString(2));
                carti.setEditura(resultSet.getString(3));
                carti.setAnAparitie(resultSet.getDate(4));


                Autor autor = new Autor();
                autor.setId(resultSet.getInt(5));
                autor.setNume(resultSet.getString(6));
                autor.setPrenume(resultSet.getString(7));


                Genuri genuri = new Genuri();
                genuri.setId(resultSet.getInt(8));
                genuri.setGen(resultSet.getString(9));

                carti.setAutor(autor);
                carti.setGenuri(genuri);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return carti;
    }


        public void save(Carti carti) {
            String query = "Insert into carti( denumire, editura, id_autor, id_gen) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = null;
            try {
                Connection connection = DriverManager.getConnection(connectionUrl, user, password);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, carti.getDenumire());
                preparedStatement.setString(2, carti.getEditura());
                preparedStatement.setInt(3,carti.getAutor().getId());
                preparedStatement.setInt(4,carti.getGenuri().getId());

                preparedStatement.executeUpdate();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    public List<Carti> getAll() {

        List<Carti> books = new ArrayList<>();
        String query = "SELECT c.id, c.denumire, c.editura, c.an_aparitie," +
                "              a.id, a.nume, a.prenume," +
                "              g.id, g.denumire" +
                "              from carti as c" +
                "              inner join autor as a on c.id_autor=a.id" +
                "              inner join genuri as g on c.id_gen=g.id ";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Carti carti = new Carti();
                carti.setId(resultSet.getInt("c.id"));
                carti.setDenumire(resultSet.getString("c.denumire"));

                Autor autor = new Autor();
                autor.setId(resultSet.getInt("a.id"));
                autor.setNume(resultSet.getString("a.nume"));
                autor.setPrenume(resultSet.getString("a.prenume"));

                carti.setAutor(autor);
                carti.setEditura(resultSet.getString("c.editura"));

                carti.setAnAparitie(resultSet.getDate("c.an_aparitie"));

                Genuri genuri = new Genuri();
                genuri.setId(resultSet.getInt("g.id"));
                genuri.setGen(resultSet.getString("g.denumire"));

                carti.setGenuri(genuri);

                books.add(carti);


            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;

    }

    public void deleteById(int id) {

        String query = "DELETE FROM carti WHERE id=?";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

