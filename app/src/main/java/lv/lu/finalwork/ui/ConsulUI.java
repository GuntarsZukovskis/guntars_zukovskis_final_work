package lv.lu.finalwork.ui;

import lv.lu.finalwork.models.Product;
import lv.lu.finalwork.models.ProductCategory;
import lv.lu.finalwork.service.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class ConsulUI {

    private ProductService service;
    private final Scanner scanner = new Scanner(System.in);


    public ConsulUI() {
        this.service = new ProductService();
    }

    public void startUI() {
        int userChoice = 0;
        while (true) {
            printMenu();
            userChoice = scanner.nextInt();

            callServiceByChoice(userChoice);

            if (userChoice == 0) {
                break;
            }
        }


    }

    private void callServiceByChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                initProductSave();
                break;
        }
    }

    private void initProductSave() {
        Product product = new Product();
        System.out.println("Enter product name:");
        product.setName(scanner.next());
        System.out.println("Enter product price:");
        product.setPrice(BigDecimal.valueOf(scanner.nextDouble()));
        System.out.printf("Enter product category %s:", Arrays.asList(ProductCategory.values()));
        product.setCategory(ProductCategory.valueOf(scanner.next()));
        service.save(product);
    }

    private void printMenu() {
        System.out.println("=== PRODUCT ACCOUNTING APPLICATION ===");
        System.out.println("Enter digit for action:");
        System.out.println("1 - save product");
        System.out.println("0 - exit application");
    }

}
