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
	private final String infile = "metadata.json.gz";
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class gzipReader {
	private final String infile = "dataset_refined0.json";
	private FileInputStream file;
	private GZIPInputStream in;
	private Reader decoder;
	private FileReader reader;
	private JSONParser parser = new JSONParser();
	private JSONObject jsonObject;
	
	public gzipReader() {
		fileInit();
	}
	
     /**
      * Initializes the input file to handle exceptions
      * @exception Handles a FileNotFoundException from the input file
      * @exception Handles a IOException from the GZIPInputStream object  
      */
	private void fileInit() {
		try {
			reader = new FileReader(infile);
			JSONArray a = (JSONArray) parser.parse(reader);	
			
			for (Object o : a)
			  {
			    JSONObject product = (JSONObject) o;
			    
			    String asin = (String) product.get("asin");
			    System.out.println(asin);

			    String title = (String) product.get("title");
			    System.out.println(title);

			    String ean = (String) product.get("ean");
			    System.out.println(ean);

			    String price = (String) product.get("price");
			    System.out.println(price);
			    		
			    System.out.println();
			    
			  }
			
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Reads the input file line by line
	 * splits the line into elements separated by commas
	 * @throws IOException
	 */
	public void read() throws IOException {		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		gzipReader reader = new gzipReader();		
	}	
}
