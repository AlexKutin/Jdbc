import java.sql.*;

public class Application {

    public static void main(String[] args) {
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String username = "postgres";
        final String password = "Renby413!";

        final String getByIdRequest = "SELECT * FROM employee WHERE id = (?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(getByIdRequest)
        ) {
            int idEmployee = 2;
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                System.out.printf("Employee{id: %d, firstName: %s, lastName: %s, gender: %s, age: %d, cityId: %d}\n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getInt("city_id")
                        );
            } else {
                System.out.printf("Employee with id: %d not found!\n", idEmployee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
