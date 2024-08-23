package com.cuonglv.learning_spring.utility.helper;

import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class GsonHelper {

	public GsonHelper() {
	}

	public static BigDecimal getBigDecimalValue(JsonObject dataObject, String fieldName) {

		BigDecimal value = null;
		JsonElement number = dataObject.get(fieldName);
		if (!number.isJsonNull()) {
			value = new BigDecimal(number.getAsString());
		}
		return value;
	}

	public static JsonArray deduplication(JsonArray jsonArray) {

		for (int i = 0; i < jsonArray.size(); i++)
			for (int j = i + 1; j < jsonArray.size(); j++) {
				if (jsonArray.get(i).getAsJsonObject().equals(jsonArray.get(j).getAsJsonObject())) {
					jsonArray.remove(j);
				}
			}
		return jsonArray;
	}

	public static JsonArray mergeJsonObject(JsonArray jsonArray, String propertyGroup, String property) {
		int[] test = new int[jsonArray.size()];

		JsonArray result = new JsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jo = new JsonObject();
			String tempStringi = jsonArray.get(i).getAsJsonObject().get(propertyGroup).getAsString();

			JsonArray ja = new JsonArray();
			if (test[i] == 0) {
				jo.addProperty(propertyGroup, tempStringi);
				ja.add(jsonArray.get(i).getAsJsonObject());
				test[i] = 1;
				for (int j = i + 1; j < jsonArray.size(); j++) {
					String tempStringj = jsonArray.get(j).getAsJsonObject().get(propertyGroup).getAsString();
					if (tempStringi.equals(tempStringj) && test[j] == 0) {
						ja.add(jsonArray.get(j).getAsJsonObject());
						test[j] = 1;
					}
				}
				jo.add(property, ja);
				jo.addProperty("Count", ja.size());

				result.add(jo);

			}
		}
		return GsonHelper.deduplication(result);

	}

	public static JsonObject findMax(JsonArray jsonArray, String key) {
		JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			if (jsonObject.get(key).getAsInt() > jsonArray.get(i).getAsJsonObject().get(key).getAsInt()) {
				jsonObject = jsonArray.get(i).getAsJsonObject();
			}
		}
		return jsonObject;
	}

	public static String getAsString(JsonObject jo, String key) throws Exception {
		if (!jo.has(key)) {
			throw new NullPointerException(String.format("Can't find the property '%s' in object.", key));
		}
		if (jo.get(key).isJsonNull()) {
			throw new NullPointerException(String.format("'%s' is null.", key));
		}
		if (!jo.get(key).isJsonPrimitive()) {
			throw new NullPointerException(String.format("'%s' is not a string.", key));
		}
		return jo.get(key).getAsString();
	}

	public static JsonArray getAsJsonArray(JsonObject jo, String key) throws Exception {
		if (!jo.has(key)) {
			throw new NullPointerException(String.format("Can't find the property '%s' in object.", key));
		}
		if (jo.get(key).isJsonNull()) {
			throw new NullPointerException(String.format("'%s' is null.", key));
		}
		if (!jo.get(key).isJsonArray()) {
			throw new NullPointerException(String.format("'%s' is not an array.", key));
		}
		return jo.get(key).getAsJsonArray();
	}

	public static int getAsInt(JsonObject jo, String key) throws Exception {
		if (!jo.has(key)) {
			throw new NullPointerException(String.format("Can't find the property '%s' in object.", key));
		}
		if (jo.get(key).isJsonNull()) {
			throw new NullPointerException(String.format("'%s' is null.", key));
		}
		if (!jo.get(key).isJsonPrimitive()) {
			throw new NullPointerException(String.format("'%s' is not an integer.", key));
		}
		return jo.get(key).getAsInt();
	}

	public static JsonObject getAsJsonObject(JsonObject jo, String key) throws Exception {
		if (!jo.has(key)) {
			throw new NullPointerException(String.format("Can't find the property '%s' in object.", key));
		}
		if (jo.get(key).isJsonNull()) {
			throw new NullPointerException(String.format("'%s' is null.", key));
		}
		if (!jo.get(key).isJsonObject()) {
			throw new NullPointerException(String.format("'%s' is not an object.", key));
		}
		return jo.get(key).getAsJsonObject();
	}

	public static Object getAsObject(JsonObject jo, String key) throws Exception {
		if (!jo.has(key)) {
			throw new NullPointerException(String.format("Can't find the property '%s' in object.", key));
		}
		if (jo.get(key).isJsonNull()) {
			throw new NullPointerException(String.format("'%s' is null.", key));
		}
		return new Gson().fromJson(jo.get(key).getAsJsonObject(), Object.class);
	}

	public static JsonArray mergeArrays(JsonArray schemaArray, JsonArray dataArray) {
		JsonArray mergedArray = new JsonArray();

		for (JsonElement schemaElement : schemaArray) {
			JsonObject schemaObject = schemaElement.getAsJsonObject();
			String key = schemaObject.get("key").getAsString();
			String type = schemaObject.get("type").getAsString();

			for (JsonElement dataElement : dataArray) {
				JsonObject dataObject = dataElement.getAsJsonObject();
				String dataKey = dataObject.get("key").getAsString();
				JsonElement dataValue = dataObject.get("value");

				if (key.equals(dataKey)) {
					// Create a new JsonObject for the merged array
					JsonObject mergedObject = new JsonObject();
					mergedObject.addProperty("key", key);

					// Set the value based on the type
					if ("STRING".equals(type)) {
						mergedObject.addProperty("value", dataValue.getAsString());
					} else if ("INTEGER".equals(type)) {
						mergedObject.addProperty("value", dataValue.getAsInt());
					} else if ("BOOLEAN".equals(type)) {
						mergedObject.addProperty("value", dataValue.getAsBoolean());
					} else {
						// Handle other types as needed
						mergedObject.add("value", dataValue);
					}
					mergedArray.add(mergedObject);
					break; // Move to the next schema key
				}
			}
		}

		return mergedArray;
	}

	public static Object getPrimitiveObject(JsonObject jsonObject, String key) {

		if (jsonObject.has(key) && !jsonObject.get(key).isJsonNull()) {
			JsonElement element = jsonObject.get(key);
			if (element.isJsonPrimitive()) {
				JsonPrimitive primitive = element.getAsJsonPrimitive();
				if (primitive.isBoolean()) {
					return primitive.getAsBoolean();
				}
				if (primitive.isNumber()) {
					return primitive.getAsInt();
				}
				if (primitive.isString()) {
					return primitive.getAsString();
				}

			}
		}
		return null;
	}

	public static boolean isJsonObject(String data) {
		try {
			@SuppressWarnings("unused")
			JsonObject jsonObject = new Gson().fromJson(data, JsonObject.class);

			return true;
		} catch (Exception e) {
			try {
				@SuppressWarnings("unused")
				JsonArray jsonArray = new Gson().fromJson(data, JsonArray.class);
				return true;
			} catch (Exception e2) {
				return false;
			}
		}
	}

	public static JsonObject convertToJsObject(Object object) throws Exception {
		try {
			return new Gson().fromJson(new Gson().toJson(object), JsonObject.class);
		} catch (Exception e) {
			throw new Exception("Can't convert object to JsonObject " + e.getMessage());
		}

	}
}
