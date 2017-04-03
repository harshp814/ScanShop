/*package file_processing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.zip.GZIPInputStream;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class gzipReader {
	private final String 	infile = "metadata.json.gz";
	private FileReader reader;
	private JSONParser parser = new JSONParser();
	private static JSONObject jsonObject;
	
	public gzipReader() {
		fileInit();
	}

	private void fileInit() {
		try{			
			reader = new FileReader(infile);
			Object obj = parser.parse(reader);
			jsonObject = (JSONObject) obj;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	private void read() {
		String asin = (String) jsonObject.get("asin");
		System.out.println(asin);
	}
	
	public static void main(String[] args) throws IOException {
		gzipReader r = new gzipReader();
		r.read();
	}

}*/

package file_processing;

import java.io.BufferedReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class JsonReader {
	
	private FileInputStream file;
	private InputStreamReader reader;
	
	private GZIPInputStream gzip;
	private BufferedReader in;
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	private String filename;
	
	public JsonReader(String filename) { 
		this.filename = filename;
	}
	
     /**
      * Reads the input file and returns String representations of JSON objects
      * @exception FileNotFoundException
      * @exception IOException  
      * @exception ParseException 
      */
	public JSONArray read() {
		try {
			
			file = new FileInputStream(filename);
			gzip = new GZIPInputStream(file);
			reader = new InputStreamReader(gzip);
			in = new BufferedReader(reader);
			
			parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(in);	
			jsonArray = (JSONArray) jsonObject.get("data");
			
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public static void main(String[] args) {
		String str = "1";
		Object o;
		System.out.println(str instanceof Object);
	}
	
}
