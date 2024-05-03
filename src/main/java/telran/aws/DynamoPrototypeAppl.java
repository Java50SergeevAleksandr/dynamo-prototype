package telran.aws;

import java.util.stream.IntStream;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;

import java.util.*;

public class DynamoPrototypeAppl {

	public static void main(String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDB dynamo = new DynamoDB(client);
		Table table = dynamo.getTable("avg-values");
		List<Map<String, Object>> mapsList = getMapsList(20);
		mapsList.forEach(m -> table.putItem(new PutItemSpec().withItem(Item.fromMap(m))));

	}

	private static List<Map<String, Object>> getMapsList(int nMapas) {
		List<Map<String, Object>> result = IntStream.range(0, nMapas).mapToObj(i -> getRandomMap()).toList();
		return result;
	}

	static Map<String, Object> getRandomMap() {
		Random gen = new Random();
		Map<String, Object> result = new HashMap<>();
		result.put("id", gen.nextInt(1, 4));
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {

		}
		result.put("timestamp", System.currentTimeMillis());
		result.put("value", gen.nextFloat(100, 200));
		return result;
	}

}