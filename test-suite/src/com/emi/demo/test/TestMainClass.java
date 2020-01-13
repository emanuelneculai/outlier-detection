package com.emi.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestMainClass {


	private static final String EXPECTED_RESULT = "{\"publisherId\":\"publisher-1\",\"medians\":[7.5,44.5,306.0,4.0,3.0,3.0,1.0,828.0,2.5],\"medianOutliers\":[828.0]}";

	public static void main(String[] args) {

			List<String> testSensorData = getTestSensorData();
			try {
				for (String sensorData : testSensorData) {
					publishData(sensorData);
				}

				Thread.sleep(5000);

				String outlierResponse = getOutlierResponse();

				if (EXPECTED_RESULT.equals(outlierResponse)) {
					System.out.println("Test OK");
				} else {
					System.out.println("Test KO");
				}
			} catch (Exception e){
				System.out.println("Test KO");
				System.out.println("------------------------");
				System.out.println(e.getStackTrace());
			}

	}

	private static List<String> getTestSensorData() {
		String sensorData1 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:11:30.352\",\"readings\":[1,2,2,5,3,10,3,1]}";
		String sensorData2 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:12:30.352\",\"readings\":[1,5,2,4,4,2,1,3]}";
		String sensorData3 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:13:30.352\",\"readings\":[300,2000,1101,4000,555,10,123,1500]}";
		String sensorData4 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:14:30.352\",\"readings\":[1,4,1]}";
		String sensorData5 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:15:30.352\",\"readings\":[3,2,1,5,6]}";
		String sensorData6 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:16:30.352\",\"readings\":[1,3,5]}";
		String sensorData7 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:17:30.352\",\"readings\":[1,2,100,30,3,5]}";
		String sensorData8 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:18:30.352\",\"readings\":[100,3000,512,30]}";
		String sensorData9 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:19:30.352\",\"readings\":[11,32,54,66,78,35]}";
		String sensorData10 = "{\"publisher\":\"publisher-1\",\"time\":\"2015-11-20 15:20:30.352\",\"readings\":[1,3,5,10,11,12]}";

		List<String> sensorData = new ArrayList<>();
		sensorData.add(sensorData1);
		sensorData.add(sensorData2);
		sensorData.add(sensorData3);
		sensorData.add(sensorData4);
		sensorData.add(sensorData5);
		sensorData.add(sensorData6);
		sensorData.add(sensorData7);
		sensorData.add(sensorData8);
		sensorData.add(sensorData9);
		sensorData.add(sensorData10);

		return sensorData;
	}

	private static void publishData(String sensorData) throws IOException {

		URL url = new URL("http://localhost:8081/sensorData");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream os = conn.getOutputStream();
		os.write(sensorData.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		conn.disconnect();
	}

	private static String getOutlierResponse() throws IOException {

		URL url = new URL("http://localhost:8083/sensorData/outliers/publisher-1?numberOfReadings=9");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String response = br.readLine();

		conn.disconnect();

		return response;
	}

}

