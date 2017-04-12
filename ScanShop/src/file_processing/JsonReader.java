
package file_processing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A class designed to read a single JSON file (given the file name).
 * @author Group 1
 */
public class JsonReader {
	
	private FileInputStream file;
	private InputStreamReader reader;
	
	private GZIPInputStream gzip;
	private BufferedReader in;
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	private String filename;
	
	/**
	 * Constructor for the JsonReader object. Takes a file name as a parameter.
	 * @param filename String representing the input file name.
	 */
	public JsonReader(String filename) { 
		this.filename = filename;
	}
	
     /**
      * Reads the input file and returns String representations of JSON objects.
      * @exception FileNotFoundException
      * @exception IOException  
      * @exception ParseException 
      * @return JSONArray of the product data. Returns empty JSONArray is file
      * could not be read.
      */
	public JSONArray read() {
		try {
			
			// Read the file with GZip
			file = new FileInputStream(filename);
			gzip = new GZIPInputStream(file);
			reader = new InputStreamReader(gzip);
			in = new BufferedReader(reader);
			
			// Use the JSONParser to read the JSON data.
			parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(in);	
			jsonArray = (JSONArray) jsonObject.get("data");
			
		} catch (Exception e) {
			System.out.println("JSON.GZ file could not be read!");
			return new JSONArray();
		}
		
		return jsonArray;
	}
	
}
