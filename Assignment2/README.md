Generate an artifact:
mvn clean install

Invoke lambda function synchronously:

aws lambda invoke --function-name lambda-assignment2-task1 --payload '{ "key": "value" }' response.json

aws lambda invoke --function-name lambda-assignment2-task1 --payload BASE64-ENCODED-STRING response.json

aws lambda invoke --function-name lambda-assignment2-task1 --payload eyAiYXNzaWdubWVudDIiOiAidGFzazEiIH0= response.json

aws lambda invoke --function-name lambda-assignment2-caller --payload eyAia2V5IjogInZhbHVlIiB9 response.json

or

aws lambda invoke --function-name lambda-assignment2-task1 response.json

Invoke lambda function asynchronously:

aws lambda invoke --function-name lambda-assignment2-task1 --invocation-type Event --payload eyAia2V5IjogInZhbHVlIiB9 response.json

aws lambda invoke --function-name lambda-assignment2-caller --invocation-type Event --payload eyAia2V5IjogInZhbHVlIiB9 response.json

Get execution time of lambda function:

aws lambda invoke --function-name lambda-assignment2-task2 --payload eyAiYXNzaWdubWVudDIiOiAidGFzazIiIH0= response_task2.json --log-type Tail \--query 'LogResult' --output text | base64 -d
