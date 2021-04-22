package lv.lu.finalwork.ui;

import lv.lu.finalwork.models.repository.ProductCategory;
import lv.lu.finalwork.models.ui.ProductInputData;
import lv.lu.finalwork.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Scanner;

@Controller
public class ConsulUI {
    private final ProductService service;
    private final Scanner scanner;

    @Autowired
    public ConsulUI(ProductService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void startUI() {
        int userChoice;
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
            case 2:
                retrieveProductList();
                break;
        }
    }

    private void retrieveProductList() {
        service.findAll().stream()
                .forEach(System.out::println);
    }

    private void initProductSave() {
        ProductInputData product = new ProductInputData();

        System.out.println("Enter product name: ");
        product.setName(scanner.next());

        System.out.println("Enter product price: ");
        product.setPrice(scanner.nextDouble());

        System.out.printf("Enter product category %s: ",
                Arrays.asList(ProductCategory.values()));
        product.setCategory(scanner.next());

        service.save(product);
    }

    private void printMenu() {
        System.out.println("=== PRODUCT ACCOUNTING APPLICATION ===");
        System.out.println("Enter digit for action:");
        System.out.println("1 - save product");
        System.out.println("2 - get all products");
        System.out.println("0 - exit application");
    }

}
