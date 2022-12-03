package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Map;

public class Task1 implements RequestHandler<Map<String, String>, Object> {

    @Override
    public Object handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        String ip = "N/A";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        logger.log("IP address: " + ip + "\n");

        JSONObject response = new JSONObject();
        response.put(event.get("task1"), ip);
        return response;
    }
}