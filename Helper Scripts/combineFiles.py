
import gzip
import json
from time import sleep


INPUT_FILE_PREFIX = "dataset_refined"
INPUT_FILE_TYPE = ".json.gz"
OUTPUT_FILE = "amazon_rawdata.json.gz"

def parse(file) :
	g = gzip.open(file, 'r')
	for l in g :
		yield eval(l)
	g.close()

totalSize = 0

itemData = []

with gzip.GzipFile(OUTPUT_FILE, 'wb') as outfile :
	for num in range(15) :
		for fileData in parse(INPUT_FILE_PREFIX + str(num) + INPUT_FILE_TYPE) :
			for item in fileData['data'] :
				itemData.append(item)
			totalSize += int(fileData['size'])
			
	data = {}
	data['data'] = itemData
	data['size'] = totalSize
	outfile.write(json.dumps(data) + "\n")
	
print totalSize

