package caller;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.AmazonWebServiceResult;
import com.amazonaws.ResponseMetadata;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Handler extends AmazonWebServiceResult<ResponseMetadata> implements RequestHandler<Map<String, String>, String> {
    private final String LAMBDA_FUNCTION_NAME = "lambda-assignment2-task1";
    private final String LAMBDA_REGION = "us-east-1";

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        JSONObject payloadObject = new JSONObject();
        payloadObject.put("assignment2", "task1");
        String payload = payloadObject.toString();

        AWSLambda awsLambda = AWSLambdaAsyncClient.builder().withRegion(LAMBDA_REGION).build();
        InvokeRequest invokeRequest = new InvokeRequest().withFunctionName(LAMBDA_FUNCTION_NAME).withPayload(payload);
        invokeRequest.setInvocationType(InvocationType.RequestResponse);

        // String invocationType = invokeRequest.getInvocationType();

        InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
        String response = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);
        return "Result invoking " + LAMBDA_FUNCTION_NAME + " function: " + response;
    }
}