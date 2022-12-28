import java.sql.*;
import java.util.Scanner;

public class students {

    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "postgres";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public void addstud() throws Exception {
        Scanner sc = new Scanner(System.in);
            String sql = "insert into student (id, name,surname,groupp) values (?, ? , ? , ?)";
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Enter id: ");
        int DbID = Integer.parseInt(sc.nextLine());
        Statement statment = connection.createStatement();
        String SQL_SELECT_ID = "select * from student order by id desc";
        ResultSet result = statment.executeQuery(SQL_SELECT_ID);
        preparedStatement.setInt(1, DbID);
        while (result.next()) {
            if (result.getInt("id") == DbID) {
                System.err.println("This student is already exist ");
                break;
            } else
                System.out.println("Enter name: ");
            String DbName = sc.nextLine();
            preparedStatement.setString(2, DbName);

            System.out.println("Enter Surname: ");
            String DbSurname = sc.nextLine();
            preparedStatement.setString(3, DbSurname);
            System.out.println("Enter your group: ");
            String DbGroup = sc.nextLine();
            preparedStatement.setString(4, DbGroup);
            preparedStatement.executeUpdate();
            System.out.println("Student is added");
            break;
        }
    }

    static void getAllStud() throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        Statement statment = connection.createStatement();
        String SQL_SELECT_NAME = "select * from student order by name desc";
        ResultSet result = statment.executeQuery(SQL_SELECT_NAME);
        while (result.next()) {

            System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getString("Surname") + " " + result.getString("groupp"));
        }
    }

    static void addBookStud() throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection conn = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        Statement statement;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            System.out.println("Enter ISBN");
            String isbn;
            isbn=sc.nextLine();
            System.out.println("Enter ID");
            int id;
            id= Integer.parseInt(sc.nextLine());
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT isbn FROM book WHERE isbn='" + isbn + "'");
            try {
                if (result.next()) {
                    if (isbn.equals(result.getString("isbn"))) {
                        result = statement.executeQuery("SELECT * FROM student WHERE id=" + id);
                        if (result.next()) {
                            if (id == result.getInt("id")) {
                                result = statement.executeQuery("SELECT quantity FROM book WHERE isbn='" + isbn + "'");
                                if (result.next()) {
                                    if (result.getInt("quantity") == 0) {
                                       System.err.println("Quantity 0");
                                    }
                                    else{
                                        if(result.next()) {
                                            System.out.println("Book is already taken");
                                        }
                                        else{
                                            String sqlAddBooks = "INSERT INTO studentboook" +
                                                    " (id, isbn) " +
                                                    " VALUES (?, ?)";
                                            stat = conn.prepareStatement(sqlAddBooks);
                                            stat.setInt(1, id);
                                            stat.setString(2, isbn);
                                            stat.execute();
                                            System.out.println("Book with isbn: " + isbn + " have been added to Student with id:" + id);
                                            String sqlDecreaseQuantity = "UPDATE book SET quantity=quantity - 1 WHERE isbn='" + isbn + "'";
                                            stat = conn.prepareStatement(sqlDecreaseQuantity);
                                            stat.executeUpdate();
                                        }
                                    }
                                }
                            } else
                                System.out.println("Wrong id");
                        }
                    }
                } else {
                    System.out.println("Wrong isbn");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void returnbook() throws Exception{
        Scanner sc = new Scanner(System.in);
        Connection conn = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        Statement statement;
        PreparedStatement stat = null;
        ResultSet result = null;
        System.out.println("Enter ISBN");
        String isbn;
        isbn=sc.nextLine();
        System.out.println("Enter ID");
        int id;
        id= sc.nextInt();
        try{


            statement = conn.createStatement();
            String SQL_QUERY = "SELECT id FROM studentboook WHERE id=" + id + " AND isbn='"
                    + isbn + "'";
            result = statement.executeQuery(SQL_QUERY);
            while (result.next()){
                if (id == result.getInt("id")){
                    String SQL_UPDATE_BOOKS = "UPDATE book SET quantity=quantity + 1 WHERE isbn='" + isbn + "'";
                    stat = conn.prepareStatement(SQL_UPDATE_BOOKS);
                    stat.executeUpdate();
                    String SQL_DELETE_BORROWEDBOOKS = "DELETE FROM studentboook WHERE id=" + id +
                            "AND isbn='" + isbn + "'";
                    stat = conn.prepareStatement(SQL_DELETE_BORROWEDBOOKS);
                    stat.execute();
                    System.out.println("Book deleted");

                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void printStudBooks() throws Exception{
        Connection conn = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_USERNAME);
        Statement statement = conn.createStatement();
        PreparedStatement stat = null;
        String SQL_SELECT_ID = "select * from studentboook order by id desc";
        ResultSet result = statement.executeQuery(SQL_SELECT_ID);
        while (result.next()) {

            System.out.println(result.getString("isbn") + " isbn. Owner " + result.getInt("id"));
        }

    }


}


