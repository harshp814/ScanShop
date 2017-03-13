def amazon_get_ASIN(UPC):
    import base64, hashlib, hmac, time
    from urllib import urlencode, quote_plus

    ASSOCIATE_TAG = "wolffd-20"
    AWS_ACCESS_KEY_ID = 'AKIAJQDM7M5YDQSXU45A'
    AWS_SECRET_ACCESS_KEY = '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'  
    

    base_url = "http://webservices.amazon.ca/onca/xml"
    url_params = dict(
        Service='AWSECommerceService', 
        Operation='ItemLookup', 
        IdType='UPC', 
        ItemId=UPC,
        SearchIndex='All',
        AWSAccessKeyId=AWS_ACCESS_KEY_ID,
        AssociateTag=ASSOCIATE_TAG,
        ResponseGroup='Images,ItemAttributes,EditorialReview,SalesRank')

    #Can add Version='2009-01-06'. What is it BTW? API version?


    # Add a ISO 8601 compliant timestamp (in GMT)
    url_params['Timestamp'] = time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime())

    # Sort the URL parameters by key
    keys = url_params.keys()
    keys.sort()
    # Get the values in the same order of the sorted keys
    values = map(url_params.get, keys)

    # Reconstruct the URL parameters and encode them
    url_string = urlencode(zip(keys,values))

    #Construct the string to sign
    string_to_sign = "GET\nwebservices.amazon.ca\n/onca/xml\n%s" % url_string

    # Sign the request
    signature = hmac.new(
        key=AWS_SECRET_ACCESS_KEY,
        msg=string_to_sign,
        digestmod=hashlib.sha256).digest()

    # Base64 encode the signature
    signature = base64.encodestring(signature).strip()

    # Make the signature URL safe
    urlencoded_signature = quote_plus(signature)
    url_string += "&Signature=%s" % urlencoded_signature

    #print "%s?%s\n\n%s\n\n%s" % (base_url, url_string, urlencoded_signature, signature)

    return "%s?%s" % (base_url, url_string)

def amazon_get_UPC(ASIN):
    import base64, hashlib, hmac, time
    from urllib import urlencode, quote_plus

    ASSOCIATE_TAG = "wolffd-20"
    AWS_ACCESS_KEY_ID = 'AKIAJQDM7M5YDQSXU45A'
    AWS_SECRET_ACCESS_KEY = '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'  
    

    base_url = "http://webservices.amazon.ca/onca/xml"
    url_params = dict(
        Service='AWSECommerceService', 
        Operation='ItemLookup', 
        IdType='ASIN', 
        ItemId=ASIN,
        AWSAccessKeyId=AWS_ACCESS_KEY_ID,
        AssociateTag=ASSOCIATE_TAG,
        ResponseGroup='Images,ItemAttributes,EditorialReview,SalesRank')

    #Can add Version='2009-01-06'. What is it BTW? API version?


    # Add a ISO 8601 compliant timestamp (in GMT)
    url_params['Timestamp'] = time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime())

    # Sort the URL parameters by key
    keys = url_params.keys()
    keys.sort()
    # Get the values in the same order of the sorted keys
    values = map(url_params.get, keys)

    # Reconstruct the URL parameters and encode them
    url_string = urlencode(zip(keys,values))

    #Construct the string to sign
    string_to_sign = "GET\nwebservices.amazon.ca\n/onca/xml\n%s" % url_string

    # Sign the request
    signature = hmac.new(
        key=AWS_SECRET_ACCESS_KEY,
        msg=string_to_sign,
        digestmod=hashlib.sha256).digest()

    # Base64 encode the signature
    signature = base64.encodestring(signature).strip()

    # Make the signature URL safe
    urlencoded_signature = quote_plus(signature)
    url_string += "&Signature=%s" % urlencoded_signature

    #print "%s?%s\n\n%s\n\n%s" % (base_url, url_string, urlencoded_signature, signature)

    return "%s?%s" % (base_url, url_string)

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



