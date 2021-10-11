package ex44;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static com.google.gson.JsonParser.parseReader;


public class ProductSearch
{
    //These will be used throughout the function
    //Use this to retrieve name for printing
    public String getName() {
        return nameInfo;
    }

    //Use this to retrieve price for printing
    public double getPrice() {
        return priceInfo;
    }

    //Use this to retrieve quantity for printing
    public int getQuantity() {
        return quantityInfo;
    }


    //Create this class so we can add these attributes to the arraylist later
    //Make attributes private since they only need to be used in this function
    private String nameInfo;
    private double priceInfo;
    private int quantityInfo;

    //Inputs info
    public ProductSearch(String nameInfo, double priceInfo, int quantityInfo)
    {
        this.nameInfo = nameInfo;
        this.priceInfo = priceInfo;
        this.quantityInfo = quantityInfo;
    }


    //This helps look through the JSON file
    public static ArrayList<ProductSearch> getProducts(File jsonFile) throws FileNotFoundException
    {
        //Variables needed for list (ProductSearch)
        String name;
        double price;
        int quantity;

        //Create a list to store the values using the ProductSearch class
        ArrayList<ProductSearch> productList = new ArrayList();
        JsonElement productFile = parseReader(new FileReader(jsonFile));

        //Get the file as an object
        JsonObject JSONItems = productFile.getAsJsonObject();
        //Get the info from the file and put it into an array
        JsonArray JSONProductList = JSONItems.get("products").getAsJsonArray();


        for (JsonElement attributes : JSONProductList)
        {
            JsonObject attribute = attributes.getAsJsonObject();

            //Get product info
            name = attribute.get("name").getAsString();
            price = attribute.get("price").getAsDouble();
            quantity = attribute.get("quantity").getAsInt();

            //Put the attributes into this object
            ProductSearch searchProduct = new ProductSearch(name, price, quantity);
            productList.add(searchProduct);
        }

        return productList;
    }

    //Printing the info
    public static void printProduct(ProductSearch product)
    {
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice() + "0");
        System.out.println("Quantity: " + product.getQuantity());
    }
}