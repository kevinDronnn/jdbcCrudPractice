import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudJdbc {

    private static String SELECT_ALL = "select * from jdbcstudents;";
    private static String INSERT_STUDENT = "insert into jdbcstudents (name,surname,course_name) values (?,?,?);";

    private static String UPDATE_STUDENT = "update jdbcstudents set name=? , surname=? ,course_name=? where id=?;";

    private static String DELETE_STUDENT = "delete from jdbcstudents where id=?;";

    public static List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionToDb.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static List<Student> saveStudent(Student student) {

        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionToDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourseName());
            preparedStatement.executeUpdate();

            PreparedStatement allStudents = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = allStudents.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static List<Student> updateStudent(int id , Student student) {

        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionToDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourseName());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();

            PreparedStatement allStudents = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = allStudents.executeQuery();

            while (resultSet.next()) {
                int idStud = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");

                students.add(new Student(idStud, name, surname, course_name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static void deleteStudent(int id) {

        try (Connection connection = ConnectionToDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
