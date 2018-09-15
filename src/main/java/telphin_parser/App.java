package telphin_parser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static io.restassured.RestAssured.get;



public class App 
{
 private static String APP_ID="a64e10602da54552b37ff2fc376784b7";
 private static String APP_SECRET="be1ffb6c00394ddbbdc4c9a01f0ced7d";
 private static String HOST="https://apiproxy.telphin.ru/";
 private static String HOST_OAUTH="oauth/token";
 private static String CALL_HISTORY="api/ver1.0/client/@me/call_history";


    public static void main( String[] args )
    {
        GregorianCalendar gc = new GregorianCalendar();
        Date today = new Date();
        today.m
        LocalDate yesterday =new LocalDate().minusDays(1);

        CallHistoryGet callHistoryGet = GetCallHistrory(GetAuthToken(),"2018-09-14 05:00:00","2018-09-14 20:00:00" );

    }
    public static CallHistoryGet GetCallHistrory(String authToken,String startDateTime,String endDateTime)
    {
        CallHistoryGet callHistoryGet =new CallHistoryGet();
        RequestSpecification request =RestAssured.given();
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "Bearer "+authToken);
        HashMap<String,String> params = new HashMap<>();
        params.put("start_datetime",startDateTime);
        params.put("end_datetime",endDateTime);
        request.headers(header);
        request.params(params);
        request.baseUri(HOST);
        Response response=request.get(CALL_HISTORY);
        if (response.getStatusCode()==200) callHistoryGet = new Gson().fromJson(response.getBody().asString(),CallHistoryGet.class);

        return callHistoryGet;

    }
    public static String GetAuthToken()
    {
        RequestSpecification request =RestAssured.given();
        request.header("Content-Type", "application/x-www-form-urlencoded");
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("grant_type", "client_credentials");
        map.put("client_id", APP_ID);
        map.put("client_secret", APP_SECRET);
        request.formParameters(map);
        request.baseUri(HOST);
        Response response= request.post(HOST_OAUTH);
        if (response.getStatusCode()==200) {
            OauthRes oauthRes = new Gson().fromJson(response.getBody().asString(), OauthRes.class);
            return oauthRes.getAccessToken();
        }
        else return "";

    }
}
