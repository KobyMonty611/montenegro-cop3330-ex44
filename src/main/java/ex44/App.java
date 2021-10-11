/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Koby Montenegro
 */

package ex44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class App
{
    public static void main(String[] args) throws FileNotFoundException {

        int printHelp = 0;

        //Open the file so we can read it later
        File jsonFile = new File("src/main/java/ex44/exercise44_input.json");

        //Convert the info to an object array
        ArrayList<ProductSearch> productsList;
        productsList = ProductSearch.getProducts(jsonFile);

        boolean inInventory = false;
        String askedForProduct;

        while (!inInventory)
        {
            //Placed here for when the loop is restarted it can reset as well
            printHelp = 0;

            //User is asked to enter a product name
            System.out.print("What is the product name: ");
            Scanner scan = new Scanner(System.in);

            askedForProduct = scan.nextLine().toLowerCase();


                for (ProductSearch product : productsList)
                {
                    //Checks if the product exists in the array
                    if (product.getName().toLowerCase().equals(askedForProduct))
                    {
                        ProductSearch.printProduct(product);
                        //breaks the loop
                        inInventory = true;
                    }
                    else
                    {
                        //Used to count for not found item
                        printHelp++;
                    }
                }

                //Used to print out in case an item cannot be found
                if(printHelp == 3)
                System.out.print("Sorry, that product was not found in our inventory.\n");
        }
    }
}