package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Task3 implements RequestHandler<Map<String, String>, Object> {

    @Override
    public Object handleRequest(Map<String, String> event, Context context) {
        String ip = "N/A";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("IP address " + ip + "\n");

        JSONObject response = new JSONObject();
        response.put(event.get("task3"), ip);
        return response;
    }
}