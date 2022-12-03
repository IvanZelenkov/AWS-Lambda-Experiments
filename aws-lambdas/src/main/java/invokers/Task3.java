package invokers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Task3 implements RequestHandler<Map<String, String>, Object> {

    @Override
    public Object handleRequest(Map<String, String> event, Context context) {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials
                .create(System.getenv("ACCESS_KEY_ID"), System.getenv("SECRET_ACCESS_KEY"));

        LambdaAsyncClient lambdaAsyncClient = LambdaAsyncClient
                .builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();

        try {
            JSONObject payloadJSON = new JSONObject(event);
            SdkBytes payload = SdkBytes.fromUtf8String(payloadJSON.toString());
            InvokeRequest invokeRequest = InvokeRequest
                    .builder()
                    .functionName(System.getenv("HANDLER_FUNCTION_NAME_TO_INVOKE"))
                    .payload(payload)
                    .build();

            execute(lambdaAsyncClient, invokeRequest);
        } catch (Exception error) {
            System.err.println(error.getMessage());
            System.exit(1);
        }

        JSONObject response = new JSONObject();
        response.put("statusCode", 200);
        return response;
    }

    public void execute(LambdaAsyncClient lambdaAsyncClient, InvokeRequest invokeRequest) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                double startTime = System.currentTimeMillis();
                CompletableFuture<InvokeResponse> invokeResponse = lambdaAsyncClient.invoke(invokeRequest);
                String statusCode = "N/A";
                try {
                    statusCode = invokeResponse.get().statusCode().toString();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                double executionTotalTime = System.currentTimeMillis() - startTime;
                String response = "Status code: " + statusCode + "\n" + "Total execution time: " + executionTotalTime + "\n";
                return response;
            }
        };

        List<Future<String>> result = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            result.add(executorService.submit(callable));

        for (Future<String> future : result) {
            System.out.print(future.get());
        }

        executorService.shutdown();
    }
}