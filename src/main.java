import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        book s = new book();
        students st = new students();
        Scanner in = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Welcome to the AITU Library");
            System.out.println("1. Add book");
            System.out.println("2. Add student");
            System.out.println("3. Give book to student");
            System.out.println("4. Return book from student");
            System.out.println("5. Print books information");
            System.out.println("6. Print students information");
            System.out.println("7. Print student's books");
            System.out.println("0. Exit");

            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    try {
                        s.addbook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        st.addstud();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        st.addBookStud();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        st.returnbook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 5:
                    try {
                        s.getAllBooks();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        st.getAllStud();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 7:
                    try {
                        st.printStudBooks();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }
}