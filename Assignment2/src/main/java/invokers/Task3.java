package invokers;

import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.LambdaAsyncClientBuilder;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

import java.util.Map;
import java.util.concurrent.Future;

public class Task3 implements RequestHandler<Map<String, String>, Object> {

    @Override
    public Object handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();

        AwsBasicCredentials awsCredentials = AwsBasicCredentials
                .create(System.getenv("ACCESS_KEY_ID"),
                        System.getenv("SECRET_ACCESS_KEY"));

        LambdaClient awsLambda = LambdaClient
                .builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();

//        LambdaAsyncClient awsLambdaAsync = LambdaAsyncClient
//                .builder()
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                .region(Region.of(System.getenv("AWS_REGION")))
//                .build();

        InvokeResponse invokeResponse;
        String response;
        JSONObject responseJSON = new JSONObject();

        try {
            JSONObject payloadJSON = new JSONObject(event);
            SdkBytes payload = SdkBytes.fromUtf8String(payloadJSON.toString());
            InvokeRequest invokeRequest = InvokeRequest
                    .builder()
                    .functionName(System.getenv("HANDLER_FUNCTION_NAME_TO_INVOKE"))
                    .payload(payload)
                    .build();

            for (int i = 1; i <= 10; i++) {
                double startTime = System.currentTimeMillis();
                invokeResponse = awsLambda.invoke(invokeRequest);
                double executionTotalTime = System.currentTimeMillis() - startTime;

                String task1StringJSONResponse = invokeResponse.payload().asUtf8String();
                JSONObject task1JSONResponse = (JSONObject) new JSONParser().parse(task1StringJSONResponse);

                response = "The Lambda function " +
                        System.getenv("HANDLER_FUNCTION_NAME_TO_INVOKE") +
                        " running on IP address " +
                        task1JSONResponse.get("ip") +
                        " executed in " +
                        executionTotalTime +
                        " ms\n";
                logger.log(response);

                responseJSON.put("call" + i, response);
            }
        } catch (LambdaException error) {
            System.err.println(error.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseJSON;
    }
}