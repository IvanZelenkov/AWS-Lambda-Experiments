Generate an artifact:
`mvn clean install`

Invoke lambda function:
aws lambda invoke --function-name assignment2-handler-task1 --payload eyAidGFzazEiOiAiaXAiIH0= response_handler_task1.json --log-type Tail \--query 'LogResult' --output text | base64 -d

aws lambda invoke --function-name assignment2-invoker-task2 --payload eyAidGFzazEiOiAiaXAiIH0= response_invoker_task2.json --log-type Tail \--query 'LogResult' --output text | base64 -d

aws lambda invoke --function-name assignment2-invoker-task3 --payload eyAidGFzazMiOiAiaXAiIH0= response_invoker_task3.json --log-type Tail \--query 'LogResult' --output text | base64 -d
