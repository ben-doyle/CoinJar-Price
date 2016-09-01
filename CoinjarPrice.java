import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import java.util.Date;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CoinjarPrice {
    public static void main(String [ ] args) throws IOException, ParseException, java.text.ParseException

    {
        String sURL = "https://api.coinjar.com/v3/exchange_rates.json"; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();


        // Convert to a JSON object to print data
        JSONParser jp = new JSONParser(); //from gson
        Object obj = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JSONObject jsonObject = (JSONObject) obj;

        Date startDate = new Date(Long.parseLong(jsonObject.get("last_updated").toString()) * 1000);

        JSONObject exchangeRates = (JSONObject) jsonObject.get("exchange_rates");
        JSONObject btcAUD = (JSONObject) exchangeRates.get("BTCAUD");

        System.out.println(startDate.toString() + " Price in AUD: " + btcAUD.get("ask"));
    }
}
