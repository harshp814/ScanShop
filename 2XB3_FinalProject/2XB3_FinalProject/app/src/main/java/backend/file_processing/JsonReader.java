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

package backend.file_processing;

import java.io.BufferedReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import backend.adts.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class JsonReader {

	private ZipFile zipFile;
	private ZipEntry zipEntry;

	private InputStream file;
	private InputStreamReader reader;

	private ZipInputStream zip;
	private BufferedReader in;
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	public JsonReader(String filename) {
		try {
			zipFile = new ZipFile(filename);
		} catch(Exception e) {e.printStackTrace();}
	}
	public JsonReader(InputStream file) { this.file = file; }
	
     /**
      * Reads the input file and returns String representations of JSON objects
      * @exception FileNotFoundException
      * @exception IOException  
      * @exception ParseException 
      */
	public JSONArray read() {
		try {
			/*
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while(entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				System.out.println(entry.getName());
			}

			zipEntry = zipFile.getEntry("amazon_rawdata");
			zip = new ZipInputStream(file);
			*/
			reader = new InputStreamReader(file);
			in = new BufferedReader(reader);

			System.out.println(in.readLine());

			parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(in);	
			jsonArray = (JSONArray) jsonObject.get("data");

		} catch (Exception e) {
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
