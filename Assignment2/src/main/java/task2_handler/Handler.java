package task2_handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        String ip = "N/A";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        JSONObject response = new JSONObject();
        response.put(event.get("assignment2"), "IP of system is: " + ip);

        return response.toString();
    }
}