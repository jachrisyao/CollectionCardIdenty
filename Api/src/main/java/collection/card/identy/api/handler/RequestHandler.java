package collection.card.identy.api.handler;

import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestHandler {
    public static String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            LogManager.getLogger(RequestHandler.class).error(e.toString());
        }
        return sb.toString();
    }
}
