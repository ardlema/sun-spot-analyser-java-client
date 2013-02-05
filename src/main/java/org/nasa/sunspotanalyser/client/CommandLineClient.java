package org.nasa.sunspotanalyser.client;

import java.util.ArrayList;
import java.util.List;

import org.nasa.sunspotanalyser.SunSpotAnalyser;
import org.nasa.sunspotanalyser.SunSpotAnalyserImpl;
import org.nasa.sunspotanalyser.exception.SunSpotAnalyserException;
import org.nasa.sunspotanalyser.exception.SunSpotAnalyserTypeExceptionType;
import org.nasa.sunspotanalyser.SunSpotAnalyserResult;

/**
 * Line Command Client implementation for 
 * SunSpotAnalyser interface.
 * 
 */
public class CommandLineClient  {
   
	private static final String THERMAL_ACTIVITY_RESULTS = "Thermal Activity Results - Command Line Client";
	private static final String OUTPUT_SEPARATOR = "*****************************************************";
	/**
	 * The sun spot analyser interface.
	 */
	private static SunSpotAnalyser sunSpotAnalyser;
	
	/**
	 * Receives the number of results requested, the
	 * size of the grid and a space delimited list of 
	 * numbers that form the grid.
	 * 
	 * @param args[0]: number of results
	 *        args[1]: size of the grid
	 *        args[2..n]: list of numbers
	 */
    public static void main(String[] args) {
        
    	try {
    	
    	if (args.length<3)
    		throw new SunSpotAnalyserException(SunSpotAnalyserTypeExceptionType.EMPTY_INPUT_VALUES_EXCEPTION);
    		
    	String listOfNumbersSpaceDelimited = getListOfNumbersSpaceDelimited(args);
    	List<SunSpotAnalyserResult> thermalActivityResults = new ArrayList<SunSpotAnalyserResult>();
    	
		sunSpotAnalyser = new SunSpotAnalyserImpl();
       	thermalActivityResults =  sunSpotAnalyser.getResultsAsList(args[0],args[1],listOfNumbersSpaceDelimited);
	
       	printResults(thermalActivityResults);
       	
    	} catch (SunSpotAnalyserException sunSpotAnalyserException) {
    		System.out.println(THERMAL_ACTIVITY_RESULTS);
    		System.out.println(OUTPUT_SEPARATOR);
    		System.out.println("Exception: "+sunSpotAnalyserException.getExceptionType());
		}

		
        
    }

    /**
     * This method prints to the standard output
     * the thermal activity results.
     * 
     * @param thermalActivityResults
     */
	private static void printResults(
			List<SunSpotAnalyserResult> thermalActivityResults) {
		System.out.println(THERMAL_ACTIVITY_RESULTS);
		System.out.println(OUTPUT_SEPARATOR);
		for (SunSpotAnalyserResult result: thermalActivityResults)
			System.out.println(result.toString());
	}

    /**
     * This method walks through args[] and
     * returns a String with the list of space
     * delimited numbers.
     *  
     * @param args 
     * @param listOfNumberParameterIndex
     * @param listOfNumbersSpaceDelimited
     * @return the list of space delimited numbers
     */
	private static String getListOfNumbersSpaceDelimited(String[] args) {
		StringBuilder listOfNumbersSpaceDelimited = new StringBuilder();
		int listOfNumberParameterIndex = 2;
		for (int i=0;i<args.length-2;i++){
			listOfNumbersSpaceDelimited.append(args[listOfNumberParameterIndex]);
			listOfNumberParameterIndex++;
			listOfNumbersSpaceDelimited.append(" ");
		}
		return listOfNumbersSpaceDelimited.toString();
	}
}
