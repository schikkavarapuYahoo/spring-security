# spring-security
Learning &amp; Build spring security


#### Notes 
##### BasicAuth notes 
````
1. For creating any authentication in spring these below things are required 
    1. User-name
    2. Password (Must be encoded)
    3. Roles(ROLE-NAME Must be in caps)
    4. Authorities (or) Permissions ? 

2. These all details are available in a spring class 
         => org.springframework.security.core.userdetails.UserDetails

3. Check configuration/security pkg for more details 
````