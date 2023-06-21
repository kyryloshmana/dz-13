import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {
    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";
    private final static String username = "homework";
    private final static String password = "homework";

    private final static String QUERY_INSERT = "insert into Persons values (?,?,?,?)";
    private final static String QUERY_UPDATE = "UPDATE Persons SET firstname=?, lastname=?, age=? WHERE id=?";
    private final static String QUERY_DELETE = "delete from Persons where id=?";

    private final static String QUERY_SELECT = "select * from Persons where id=?";
    private final static String QUERY_SELECT_ALL = "select * from Persons";

    public void updateInBD(int id, String firstname, String lastname, int age) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception){
            throw new RuntimeException("Check your data");
        }
    }

    public void deleteInBD(int id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete compete");
        } catch (SQLException exception) {
            throw new RuntimeException("Check your data");
        }
    }

    public void selectAllPerson() {
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("id")
                        , resultSet.getString("firstname")
                        , resultSet.getString("lastname")
                        , resultSet.getInt("age"));
                persons.add(person);
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Check your data", exception);
        }
        for (Person person : persons) {
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Age: " + person.getAge());
            System.out.println("-----------------------");
        }
    }

    public void selectPerson(int id) {
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("id")
                        , resultSet.getString("firstname")
                        , resultSet.getString("lastname")
                        , resultSet.getInt("age"));
                persons.add(person);
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Check your data");
        }
        for (Person person : persons) {
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Age: " + person.getAge());
            System.out.println("-----------------------");
        }
    }

    public void insertToBD(String firstname, String lastname, int age) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, UniqueId.getUniqueId());
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Check your data");
        }
    }




}