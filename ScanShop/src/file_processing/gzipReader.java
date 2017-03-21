/*package file_processing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonReader {
	private final String infile;
	private FileReader reader;
	private JSONParser parser = new JSONParser();
	
	public JsonReader(String infile) {
		this.infile = infile;
		fileInit();
	}

	private void fileInit() {
		try{
			
			reader = new FileReader(infile);
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}

}*/

package file_processing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

public class gzipReader {
	private final String infile = "metadata.json.gz";
	private GZIPInputStream in = new GZIPInputStream(new FileInputStream(infile));
	
	private void fileInit)()
	
}
