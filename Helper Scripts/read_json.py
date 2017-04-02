import requests
import gzip
import json
from xml.etree import ElementTree
from scrape import *


SAVE_INTERVAL = 100
UPDATE_INTERVAL = 10
MAX_CHUNK = 10



def parse() :
	g = gzip.open("metadata.json.gz", 'r')
	for l in g :
		print l
		yield eval(l)
	g.close()

c = 0
for i in parse() :
	print i['asin']
	c += 1
	if c > 10 :
		break