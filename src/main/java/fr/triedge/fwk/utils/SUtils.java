package fr.triedge.fwk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

public class SUtils {
    public static float round(float f){
        return Math.round(f*100f)/100f;
    }

    public static double round(double d){
        return Math.round(d*100.0)/100.0;
    }

    public static String removeLastComa(String str){
        return str.replaceAll(",$", "");
    }

    public static String toComaSeparated(Object[] ids){
        String id_str = "";
        for(Object id : ids)
            id_str+=id.toString()+",";
        return removeLastComa(id_str);
    }

    public static Date getFileDate(String path) throws IOException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("No file found");
        BasicFileAttributes attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
        FileTime modified = attr.lastModifiedTime();
        return new Date(modified.toMillis());
    }

    public static String getCurrentMonthDateAsString(DateTimeFormatter dtf){
        LocalDate date = YearMonth.now().atDay(1);
        return date.format(dtf);
    }

    public static String getCurrentMonthDateAsString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        return getCurrentMonthDateAsString(dtf);
    }

    public static String getPreviousMonthDateAsString(DateTimeFormatter dtf){
        LocalDate date = YearMonth.now().atDay(1).minusMonths(1);
        return date.format(dtf);
    }

    public static String getPreviousMonthDateAsString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        return getPreviousMonthDateAsString(dtf);
    }

    public static boolean isUrlReachable(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode == 200)
                return true;
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static boolean isUrlReachable(String urlStr, String user, String password){
        if (user == null || password == null)
            return false;
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestMethod("GET");

            String userCredentials = user+":"+password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes(StandardCharsets.UTF_8)));
            conn.setRequestProperty ("Authorization", basicAuth);

            int code = conn.getResponseCode();
            if (code == 200)
                return true;

        }catch (IOException e){
            return false;
        }
        return false;
    }

    public static String encode(String password){
        String enc = new String(Base64.getEncoder().encode(password.getBytes()));
        return enc.replace("+","%20");
    }

    public static String decode(String password){
        return new String(Base64.getDecoder().decode(password));
    }

    public static String encodeURL(String text){
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    public static String decodeURL(String text){
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    public static String toPrint(Object obj){
        StringBuilder tmp = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        tmp.append(obj.getClass().getSimpleName()).append(" [\n");
        for (Field field : fields){
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                tmp.append("  ").append(field.getName()).append(": ").append(o==null?"null":o.toString()).append("\n");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                field.setAccessible(false);
            }
        }
        tmp.append("]");
        return tmp.toString();
    }
}
