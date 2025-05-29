# UserPosts
This repository contains Spring Boot project built with Spring Boot 3.4.5 and Java 21 with MySQL as the database.

This project has explored various spring capabilities.

1. localhost:8080/filter-cred or localhost:8080/filter-id show how we can send the response to different end points for the same Object.
Here, filter-cred omits credentials of students in the response where as filter-id return all other attributes of Student except for the id.
 
2. localhost:8080/users endpoint enables the CRUD operations on Users and localhost:8080/users/{userId}/posts endpoint enables us to access the 
 social media posts of that user. We can do CRUD operations on posts as well. When the user/post doesn't exists and we try to update/delete user/post 
the end point gives a proper JSON response.

3. localhost:8080/hello-world-i18n gives the response based on the Accept-Language Header.

 
 




