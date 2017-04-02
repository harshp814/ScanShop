import requests
import gzip
import json
from xml.etree import ElementTree
from scrape import *


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

def findUPC(root, pre, codes, asin="", ind="") :
    for child in root :

        #print ind + child.tag[pre:]

        if child.tag[pre:] == "ASIN" :
            asin = child.text
        if child.tag[pre:] in ["Items", "Item", "ItemAttributes"] :
            findUPC(child, pre, codes, asin, ind + "\t")
        elif child.tag[pre:] in ["UPC", "EAN"] : 
            codes.append(str(asin) + "," + str(child.tag[pre:]) + "," + str(child.text))
    return codes

done = []
OUTPUT_FILE = "dataset_refined.json.gz"

for item in parse() :

    itemData = {}

    try :

        response = requests.get(amazon_get_UPC(item['asin']))
        x = ElementTree.fromstring(response.text)

        pre = count_pre(x)
        barcodes = findUPC(x, pre, [])
        
        if len(barcodes) < 1 : continue

        ## Load data into itemData dictionary
        if item.has_key('asin') : itemData['asin'] = item['asin']
        else : continue
        if item.has_key('title') : itemData['title'] = item['title']
        else : continue
        if item.has_key('price') : itemData['price'] = item['price']
        else : continue

        for i in barcodes :
            i = i.split(",")
            itemData[i[1].lower()] = i[2]
                
        done.append(itemData)
        print itemData

        if len(done) > 100 : break

    except Exception as e :
        pass

data = {}
data['data'] = done
data['size'] = len(done)

with gzip.GzipFile(OUTPUT_FILE, 'wb') as outfile :
        outfile.write(json.dumps(data) + '\n')












        
