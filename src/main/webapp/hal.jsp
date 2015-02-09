<%@ page contentType="application/hal+json" pageEncoding="utf-8"%><%
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", 
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", 
//"x-requested-with");
"origin, content-type, x-requested-with");
%>{
  "Help": "Mandatory Basic or Login Authentication", 
  "Enhanced": "This enhanced version of HAL Browser support Basic Authentication", 
  "Sample Basic Authentication": "http://{username}:{password}@**", 
  "Customer0": "samd:samd", 
  "Customer1": "dollie:dollie", 
  "Customer2": "cornelia:cornelia", 
  "Customer3": "cvb:cvb", 
  "Customer4": "schmidtj:schmidtj",
  "_links": {
    "self": {
      "href": "/rest"
    },
    "hal:user": {
      "href": "http://{username}:{username}@localhost:4041/rest/user/{username}",
      "templated": true
    },
    "curies": [
      {
        "href": "http://www.example.com/docs/{rel}",
        "name": "hal",
        "templated": true
      }
    ]
  }
}
