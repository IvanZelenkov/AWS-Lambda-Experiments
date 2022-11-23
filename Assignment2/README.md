Generate an artifact:
`mvn clean install`

Invoke lambda function synchronously:
`aws lambda invoke --function-name assignment2-handler-task1 --payload eyAidGFzazEiOiAiaXAiIH0= response_handler_task1.json --log-type Tail \--query 'LogResult' --output text | base64 -d`

`aws lambda invoke --function-name assignment2-invoker-task2 --payload eyAidGFzazEiOiAiaXAiIH0= response_invoker_task2.txt --log-type Tail \--query 'LogResult' --output text | base64 -d`

Invoke lambda function asynchronously:
`aws lambda invoke --function-name lambda-assignment2-task1 --invocation-type Event --payload eyAia2V5IjogInZhbHVlIiB9 response.json`

`aws lambda invoke --function-name lambda-assignment2-task2 --invocation-type Event --payload eyAia2V5IjogInZhbHVlIiB9 response.json`
