import requests
import gzip
import json
from xml.etree import ElementTree
from scrape import *
from time import sleep

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
            codes.append([asin, child.tag[pre:], child.text])
    return codes

done = []
OUTPUT_FILE = "dataset_refined"
count = 0
fileCount = 7

chunk = []

continuePoint = False

for item in parse() :

    count += 1

    if not continuePoint :
        if item['asin'] == '0199891982' :
            continuePoint = True
            raw_input("Continue point found at position: " + str(count) + ". Press enter to continue: ")
        continue

    chunk.append(item)
    if count % 10 != 0 : continue
    
    try :
        asins = [i['asin'] for i in chunk]

        sleep(0.5)
        
        response = requests.get(amazon_get_UPC(','.join(asins)))
        x = ElementTree.fromstring(response.text)

        pre = count_pre(x)
        barcodes = findUPC(x, pre, [])

        #print barcodes

        for bar in barcodes :
            itemData = {}
            curItem = chunk[asins.index(bar[0])]

            ## Load data into itemData dictionary
            if curItem.has_key('asin') : itemData['asin'] = curItem['asin']
            else : continue
            if curItem.has_key('title') : itemData['title'] = curItem['title']
            else : continue
            if curItem.has_key('price') : itemData['price'] = curItem['price']
            else : continue

            itemData[bar[1].lower()] = bar[2]

            done.append(itemData)
            #print itemData

        chunk = []
        print len(done)

        if len(done) > 10000 :
            data = {}
            data['data'] = done
            data['size'] = len(done)

            with gzip.GzipFile(OUTPUT_FILE + str(fileCount) + ".json.gz", 'wb') as outfile :
                outfile.write(json.dumps(data) + '\n')

            fileCount += 1
            done = []

    except Exception as e :
        pass














        
