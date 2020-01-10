package com.emi.demo.webserver.service;

public interface OutlierDetector {

	boolean isOutlier(double[] medians, double median);

}
