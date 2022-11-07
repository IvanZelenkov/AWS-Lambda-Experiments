package task1_handler;

import com.amazonaws.AmazonWebServiceResult;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.util.Map;

public class Handler extends AmazonWebServiceResult<ResponseMetadata> implements RequestHandler<Map<String, String>, String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        JSONObject response = new JSONObject();
        response.put(event.get("assignment2"), gson.toJson(System.getenv().get("AWS_LAMBDA_RUNTIME_API")));
        logger.log(response.toString());
        return response.toString();
    }
}