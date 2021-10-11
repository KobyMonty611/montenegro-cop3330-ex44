package ex44;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import static com.google.gson.JsonParser.parseReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductSearchTest
{
    @Test
    void getProducts() throws FileNotFoundException
    {
        //Variables needed for list
        String name;
        Double price;
        int quantity;

        //Open the file so we can access it later
        File jsonFile = new File("src/main/java/ex44/exercise44_input.json");

        //Create arrays for testing later
        ArrayList<ProductSearch> productList = new ArrayList();
        ArrayList<ProductSearch> testProductList = new ArrayList();

        //Create ProductSearch classes so they can be added into the testProductList ArrayList
        ProductSearch testWidget = new ProductSearch("Widget", 25.00, 5);
        ProductSearch testThing = new ProductSearch("Thing", 15.00, 5);
        ProductSearch testDoodad = new ProductSearch("Doodad", 5.00, 10);

        testProductList.add(testWidget);
        testProductList.add(testThing);
        testProductList.add(testDoodad);

        //Reads the File
        JsonElement productFile = parseReader(new FileReader(jsonFile));

        //Get the file as an object
        JsonObject JSONItems = productFile.getAsJsonObject();
        //Get the info from the file as an Array
        JsonArray JSONArray = JSONItems.get("products").getAsJsonArray();


        for(JsonElement product: JSONArray)
        {
            //Place the info from the file into single attributes
            JsonObject JsonProduct = product.getAsJsonObject();
            name = JsonProduct.get("name").getAsString();
            price = JsonProduct.get("price").getAsDouble();
            quantity = JsonProduct.get("quantity").getAsInt();

            //Put attributes into this object
            ProductSearch search = new ProductSearch(name, price, quantity);
            productList.add(search);
        }


        //This is where the comparison happens
        //assertEquals(expected, actual)
        for(int i = 0; i < testProductList.size(); i++)
        {
            assertEquals(testProductList.get(i).getName(), productList.get(i).getName());
            assertEquals(testProductList.get(i).getPrice(), productList.get(i).getPrice());
            assertEquals(testProductList.get(i).getQuantity(), productList.get(i).getQuantity());
        }

    }
}
