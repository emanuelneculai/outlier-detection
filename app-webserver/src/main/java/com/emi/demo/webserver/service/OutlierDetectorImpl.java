package com.emi.demo.webserver.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

@Service
public class OutlierDetectorImpl implements OutlierDetector {

	@Override
	public boolean isOutlier(double[] values, double valueToBeChecked) {
		DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
		for (double value : values) {
			descriptiveStatistics.addValue(value);
		}
		double Q1 = descriptiveStatistics.getPercentile(25);
		double Q3 = descriptiveStatistics.getPercentile(75);
		double IQR = Q3 - Q1;
		double highRange = Q3 + 3 * IQR;
		double lowRange = Q1 - 3 * IQR;
		if (valueToBeChecked > highRange || valueToBeChecked < lowRange) {
			return true;
		}
		return false;
	}

}
