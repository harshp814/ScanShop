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

import adts.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class JsonReader {
	private static final String infile = "amazon_rawdata.json.gz";
	private static FileInputStream file;
	private static InputStreamReader reader;
	
	private static GZIPInputStream gzip;
	private static BufferedReader in;
	private static JSONParser parser;
	private static JSONObject jsonObject;
	private static JSONArray jsonArray;
	
	private JsonReader() {  }
	
     /**
      * Reads the input file and returns String representations of JSON objects
      * @exception FileNotFoundException
      * @exception IOException  
      * @exception ParseException 
      */
	public static JSONArray read() {
		try {
			
			file = new FileInputStream(infile);
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
