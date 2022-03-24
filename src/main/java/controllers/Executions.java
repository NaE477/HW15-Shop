package controllers;

import entities.users.Admin;
import entities.users.Customer;
import repositories.impls.AdminRepositoryImpl;
import repositories.impls.CustomerRepositoryImpl;
import services.impls.AdminServiceImpl;
import services.impls.CustomerServiceImpl;
import services.interfaces.AdminService;
import services.interfaces.CustomerService;

import javax.persistence.EntityManagerFactory;
import java.util.Locale;
import java.util.Scanner;

public class Executions {
    private static final EntityManagerFactory factory = EntityManagerSingleton.getInstance();
    private static final AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory,Admin.class));
    private static final CustomerService customerService = new CustomerServiceImpl(new CustomerRepositoryImpl(factory,Customer.class));
    private static final Scanner sc = new Scanner(System.in);
    private static final Utilities utils = new Utilities();

    public static void main(String[] args) {
        label:
        while (true) {
            System.out.println("Press L/l to Login or S/s to Signup or X/x to exit: ");
            String lOrS = sc.nextLine().toUpperCase(Locale.ROOT);
            switch (lOrS) {
                case "L":
                    login();
                    break;
                case "S":
                    signUp();
                    break;
                case "X":
                    break label;
                default:
                    System.out.println("Wrong option.");
                    break;
            }
        }
    }

    public static void login() {
        System.out.println("Username:");
        String username = sc.nextLine();
        System.out.println("Password");
        String password = sc.nextLine();
        auth(username,password);
    }

    private static void auth(String username,String password) {
        Admin probableAdmin = adminService.findByUsername(username);
        Customer probableCustomer = customerService.findByUsername(username);
        if (probableAdmin != null && probableAdmin.getPassword().equals(password)) guideAdmin(probableAdmin.getId());
        else if (probableCustomer != null && probableCustomer.getPassword().equals(password)) guideUser(probableCustomer.getId());
        else System.out.println("Wrong username/password");
    }

    private static void guideAdmin(Integer adminId) {
        AdminController controller = new AdminController(factory,adminId);
    }

    private static void guideUser(Integer customerId) {
        CustomerController controller = new CustomerController(factory,customerId);
    }

    public static void signUp() {
        System.out.println("Username: ");
        String username = usernameReceiver();
        String password = utils.passwordReceiver();
        Customer customer = new Customer();
        customer.setUsername(username); customer.setPassword(password);
        Customer toRegister = customerService.insert(customer);
        if (toRegister != null) System.out.println("Account registered successfully");
        else System.out.println("Something went wrong with the database");
    }

    private static String usernameReceiver() {
        while (true) {
            String username = sc.nextLine();
            if (customerService.findByUsername(username) == null && adminService.findByUsername(username) == null) return username;
            else System.out.println("Username already exists");
        }
    }
}
