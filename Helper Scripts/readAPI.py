import requests
import gzip
import json
from xml.etree import ElementTree
from scrape import *


SAVE_INTERVAL = 10
UPDATE_INTERVAL = 5
MAX_CHUNK = 1



def parse() :
	g = gzip.open("metadata.json.gz", 'r')
	for l in g :
		yield eval(l)
	g.close()

def count_pre(x) :
	pre = 1
	for c in x.tag :
	    if c != "}" : pre += 1
	    else : break
	return pre

def findUPC(root, pre, asins, asin="", ind="") :
    for child in root :

        #print ind + child.tag[pre:]

        if child.tag[pre:] == "ASIN" :
            asin = child.text
        if child.tag[pre:] in ["Items", "Item", "ItemAttributes"] :
            findUPC(child, pre, asins, asin, ind + "\t")
        elif child.tag[pre:] in ["UPC", "EAN"] : 
            asins.append(asin + " : " + child.tag[pre:] + " : " + child.text)
    return asins

data = []

ps_count = 0
chunk = []
for item in parse() :

	ps_count += 1

	if ps_count % SAVE_INTERVAL == 0 : 
		print ps_count, "processed..."
		print "\nSize of data: \t" + str(len(data)), "\n"
		print data
		break
	elif ps_count % UPDATE_INTERVAL == 0 :
		print ps_count, "processed..."

	try :

		if ps_count % MAX_CHUNK == 0 :
			
			response = requests.get(amazon_get_UPC(','.join(chunk)))
			x = ElementTree.fromstring(response.text)

			#print response.text

			pre = count_pre(x)
			data += findUPC(x, pre, [])

			chunk = []

		chunk.append(item['asin'])

	except Exception as e :
		pass

