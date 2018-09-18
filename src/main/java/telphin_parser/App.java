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

import java.io.FileInputStream;
import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;



public class App 
{
 private static String APP_ID="a64e10602da54552b37ff2fc376784b7";
 private static String APP_SECRET="be1ffb6c00394ddbbdc4c9a01f0ced7d";
 private static String HOST="https://apiproxy.telphin.ru/";
 private static String HOST_OAUTH="oauth/token";
 private static String CALL_HISTORY="api/ver1.0/client/@me/call_history";
 private static Logger log = Logger.getLogger("TelphinParser");
 private static FileHandler fileHandler;
 private static String startDate;
 private static String endDate;
 private static List<Manager> managers=new LinkedList<>();

/*
-k keys app_id app_secret
-d date dd.mm.yyyy or today or yesterday
-e extensions ex. 1,2,3,4

 */

 public static void main( String[] args )
    {

        InitializeLogger();
        Calendar today = Calendar.getInstance();

        log.info("Program started");


        int argsIndex=0;
        while (args.length>argsIndex) {

            if (args[argsIndex].equals("-k") || args[argsIndex].equals("-keys"))
            {
                APP_ID=args[argsIndex+1];
                APP_SECRET=args[argsIndex+2];
                argsIndex+=3;
                continue;
            }
            if (args[argsIndex].equals("-d"))
            {
                if  (args[argsIndex].equals("today")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(today.get(Calendar.YEAR));
                    stringBuilder.append("-");
                    stringBuilder.append(today.get(Calendar.MONTH)+1);
                    stringBuilder.append("-");
                    stringBuilder.append(today.get(Calendar.DATE));
                    StringBuilder stringBuilder1 = stringBuilder;
                    stringBuilder.append(" 04:00:00");
                    stringBuilder1.append(" 22:00:00");
                    startDate=stringBuilder.toString();
                    endDate=stringBuilder1.toString();
                    argsIndex+=2;
                }

            }
        }
        if (argsIndex==0){
            FileInputStream fileInputStream;
            Properties properties = new Properties();
            try {
                fileInputStream= new FileInputStream(System.getProperty("user.dir") + "\\telphinparser.ini");
                properties.load(fileInputStream);
                APP_ID= properties.getProperty("app_id");
                APP_SECRET=properties.getProperty("app_secret");
                String ext=properties.getProperty("extensions");
                String names=properties.getProperty("names");

                String numbers=properties.getProperty("numbers");
                Pattern pattern=Pattern.compile("([a-z0-9A-Zа-яА-Я]+)");
                Matcher extMatcher=pattern.matcher(ext);
                Matcher nameMatcher=pattern.matcher(names);
                Matcher numMatcher=pattern.matcher(numbers);
                while ((extMatcher.find()) & (nameMatcher.find()) & (numMatcher.find())) {
                    managers.add(new Manager(nameMatcher.group(),numMatcher.group(),new Long(extMatcher.group())));
                }
            }
            catch (IOException e) {
                System.out.println("Can't open ini file");
            }
        }
        CallHistoryGet callHistoryGet = GetCallHistrory(GetAuthToken(),"2018-09-17 05:00:00","2018-09-17 20:00:00" );
        List<CallHistory> callHistories= callHistoryGet.getHistory();
        for (int i=0;i<managers.size();i++)
            for (int j=0;j<callHistories.size();j++) {

                long ext = managers.get(i).getExtensionId();
                List<CdrResponse> cdr = callHistories.get(j).getCdr();
                for (int k = 0; k < cdr.size(); k++)
                    if ((ext == cdr.get(k).getExtensionId()) && "NORMAL_CLEARING".equals(cdr.get(k).getHangupCause()) && (validateCallNumber(cdr.get(k).getToUsername()))) {
                        CdrResponse temp = cdr.get(k);
                        managers.get(i).adAmountOutgoingCalls(1);
                    }

            }
         for (Manager a:managers) System.out.println(a.getName()+" "+a.getAmountOutgoingCalls());
        log.info("Program terminated");
    }

    public static boolean validateCallNumber(String number)
    {
        if (number.length()<4) return false;
        if (number.contains("*")) return false;
        return true;
    }
    public static void InitializeLogger()
    {
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir") + "\\log.log", 1024 * 1024 * 10, 1);
            fileHandler.setFormatter(new SimpleFormatter());
            log.addHandler(fileHandler);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't create log");
        }
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
        params.put("flow","out");
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
        else {
         log.info(response.getStatusLine());
            return "";
        }
    }

}
