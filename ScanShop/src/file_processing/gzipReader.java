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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class gzipReader {
	private final String infile = "dataset_refined0.json.gz";
	private FileInputStream file;
	private GZIPInputStream in;
	private Reader decoder;
	private BufferedReader reader;
	
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
			file = new FileInputStream(infile); 
			in = new GZIPInputStream(file);
			decoder = new InputStreamReader(in);
			reader = new BufferedReader(decoder);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Reads the input file line by line
	 * splits the line into elements separated by commas
	 * @throws IOException
	 */
	public void read() throws IOException {		
		String[] products = (reader.readLine()).split(",");	
		
		// this line to make sure the format of the very first product is the same as all others
		products[0] = " " + (products[0].substring(10, products[0].length())); 
		
		// extracting the relevant data from each field for each product
		// e.g. '"price": 15.95' becomes '15.95'
		for (int i = 0; i < products.length; i++) {
			if (products[i].contains("asin"))
				products[i] = products[i].substring(11, products[i].length() - 1);
			else if (products[i].contains("price"))
				products[i] = products[i].substring(10, products[i].length() - 1);
			else if (products[i].contains("ean"))
				products[i] = products[i].substring(10, products[i].length() - 1);
			else if (products[i].contains("title"))
				products[i] = products[i].substring(10, products[i].length() - 1);
		}
		
		// for visibility purposes 
		String[] temp = {"asin", "price", "upc/ean", "title"};
		for (int i = 0; i < 1000; i++)
			System.out.println(products[i] + "\t" + temp[i%temp.length]);
		reader.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		gzipReader reader = new gzipReader();
		reader.fileInit();
		reader.read();	
	}	
}
