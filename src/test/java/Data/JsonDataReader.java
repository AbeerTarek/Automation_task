package Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
public class JsonDataReader {
    public  String fullNameField, storeNameField,phoneNumberField, emailField, passwordField, retypePasswordField;

    public JsonDataReader (int testcase) throws IOException, ParseException {
        String filePath= "src/test/java/data/userData.json";
        File srcFile= new File(filePath);
        JSONParser parser=new JSONParser();
        JSONArray jarray= (JSONArray) parser.parse(new FileReader(srcFile));
        Object JsonObj=  jarray.get(testcase-1);
        JSONObject person= (JSONObject) JsonObj;
        fullNameField = (String) person.get("fullNameField");
        System.out.println(fullNameField);

        storeNameField = (String) person.get("storeNameField");
        System.out.println(storeNameField);

        phoneNumberField= (String) person.get("phoneNumberField");
        System.out.println(phoneNumberField);

        emailField = (String) person.get("emailField");
        System.out.println(emailField);

        passwordField = (String) person.get("passwordField");
        System.out.println ( passwordField );

        retypePasswordField = (String) person.get("retypePasswordField");
        System.out.println (  retypePasswordField );
        System.out.println("----------------------------------------------------");

    }
}