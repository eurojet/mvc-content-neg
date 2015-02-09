<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>{
  "Help": "Mandatory Basic or Login Authentication", 
  "Enhanced": "This enhanced version of HAL Browser support Basic Authentication", 
  "Valid Basic Authentication": "http://{username}:{password}@**", 
<c:forEach var="user" items="${users}" varStatus="rsts"> "Customer${rsts.index}": "${user}:${user}", 
</c:forEach> "_links": {
    "self": {
      "href": "${url}"
    },
<c:forEach var="urlUser" items="${urlUsers}" varStatus="rsts"> "user${rsts.index}": {
    "href": "${urlUser}"
    }, 
</c:forEach>    "hal:user": {
      "href": "${url}/user/{username}",
      "templated": true
    },
    "curies": [
      {
        "href": "${url}/docs/{rel}",
        "name": "hal",
        "templated": true
      }
    ]
  },"Please":"follow links"}<%-- 
  "_embedded": {
    "user": [<c:forEach var="user" items="${users}" varStatus="rsts"><c:if test="${rsts.index>0}">,
</c:if>       {
        "username": "${user}",
        "password": "${user}",
        "_links": {
          "self": {
            "href": "${url}/user/${user}",
          }
        }
      }</c:forEach>
    ]
  }
}
--%>
    
