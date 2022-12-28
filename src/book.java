import java.sql.*;
import java.util.Scanner;

public class book {
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "postgres";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public void addbook () throws Exception{
        Scanner sc = new Scanner(System.in);
        String sql = "insert into book (tittle,author ,year,isbn, quantity) values (?, ? , ? , ?, ?)";
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Enter tittle: ");
        String DbTittle = sc.nextLine();
        Statement statment = connection.createStatement();
        String SQL_SELECT_TITTLE = "select * from book order by tittle desc";
        ResultSet result = statment.executeQuery(SQL_SELECT_TITTLE);
        preparedStatement.setString(1, DbTittle);
        while (result.next()) {
            if (result.getString("tittle") == DbTittle) {
                System.err.println("This book is already exist ");
                break;
            } else
                System.out.println("Enter Author: ");
            String DbAuthor = sc.nextLine();
            preparedStatement.setString(2, DbAuthor);

            System.out.println("Enter year: ");
            int DbYear = Integer.parseInt(sc.nextLine());
            preparedStatement.setInt(3, DbYear);
            System.out.println("Enter ISBN: ");
            String DbIsbn = sc.nextLine();
            System.out.println("Enter quantity: ");
            preparedStatement.setString(4, DbIsbn);
            int DbQuantity = Integer.parseInt(sc.nextLine());
            preparedStatement.setInt(5, DbQuantity);
            preparedStatement.executeUpdate();
            System.out.println("Book is added");
            break;
        }
    }
    static void getAllBooks() throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        Statement statment = connection.createStatement();
        String SQL_SELECT_NAME = "select * from book order by tittle desc";
        ResultSet result = statment.executeQuery(SQL_SELECT_NAME);
        while (result.next()) {

            System.out.println(result.getString("tittle") + " " + result.getString("author") + " " + result.getInt("year") + " " + result.getString("isbn") +result.getInt("quantity"));
        }
    }
    }
