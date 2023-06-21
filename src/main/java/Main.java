import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static boolean check = false;
    static PersonManager personManager = new PersonManager();
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        String menu = """
                Menu
                1. Add person
                2. Update person
                3. Delete person
                4. Show person by ID
                5. Show all person
                """;
        System.out.println(menu);
        System.out.println("SELECT NUMBER");

        while (!check){
            String selectNumber = data.nextLine();
            switch (selectNumber){
                case "1" -> addPerson();
                case "2" -> updatePerson();
                case "3" -> deletePerson();
                case "4" -> showByIdPerson();
                case "5" -> showAllPerson();

            }
            System.out.println(menu);
        }
    }

    static void addPerson(){
        String firstname = enteredFirstName();
        String lastname = enteredLastName();
        int age = enteredAge();
        personManager.insertToBD(firstname,lastname,age);
    }

    static void updatePerson(){
        int id = enteredId();
        System.out.println("Entered new data");
        personManager.selectPerson(id);
        String firstname = enteredFirstName();
        String lastname = enteredLastName();
        int age = enteredAge();
        personManager.updateInBD(id, firstname, lastname, age);
        personManager.selectPerson(id);
    }

    static void deletePerson(){
        int id = enteredId();
        personManager.deleteInBD(id);
    }

    static void showByIdPerson(){
        int id = enteredId();
        personManager.selectPerson(id);
    }

    static void showAllPerson(){
        personManager.selectAllPerson();
    }

    static String enteredFirstName(){
        Scanner firstName = new Scanner(System.in);
        System.out.println("Entered first name");
        return firstName.nextLine();
    }

    static String enteredLastName(){
        Scanner lastName = new Scanner(System.in);
        System.out.println("Entered last name");
        return lastName.nextLine();
    }

    static Integer enteredAge(){
        Scanner age = new Scanner(System.in);
        System.out.println("Entered age");

        while (true){
            try {
                return age.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Invalid data, enter an integer");
                age.next();
            }
        }
    }

    static Integer enteredId(){
        Scanner id = new Scanner(System.in);
        System.out.println("Entered id");
        while (true){
            try {
                return id.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Invalid data, enter an integer");
                id.next();
            }
        }
      }

}

