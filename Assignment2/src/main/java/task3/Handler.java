package task3;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.AmazonWebServiceResult;
import com.amazonaws.ResponseMetadata;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Handler extends AmazonWebServiceResult<ResponseMetadata> implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        JSONObject payloadObject = new JSONObject(event);
        String invokeLambdaName = System.getenv("LAMBDA_FUNCTION_NAME_TO_INVOKE");

        AWSLambda awsLambda = AWSLambdaAsyncClient
                .builder()
                .withRegion(System.getenv("AWS_REGION"))
                .build();
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(invokeLambdaName)
                .withPayload(payloadObject.toString());

        invokeRequest.setInvocationType(InvocationType.RequestResponse);

        InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
        String response = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);
        logger.log("Result invoking " + invokeLambdaName + " function " + response);
        return "Result invoking " + invokeLambdaName + " function " + response;
    }
}