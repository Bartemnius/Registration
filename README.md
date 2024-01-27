# Registration
Module/project for user registration. Using Spring Security, MySQL, Postman to test API and maildev to see if eamil sending is all right.
Many thanks to Amigoscode for amazing tutorial ->  https://www.youtube.com/watch?v=QwQuro7ekvc&t=5496s&ab_channel=Amigoscode

Application is using main view from springboot security module where we can see the login and password.
<p>
<img src="https://github.com/Bartemnius/Registration/assets/64535843/82cd1d99-f9a6-453d-acff-999f44a69d38">  
</p>

Before we register we get bad credentials info.
<p>
<img src="https://github.com/Bartemnius/Registration/assets/64535843/9a806697-cd26-4f0b-be17-fe3be6d626b5">   
</p>

For testing purposes to register user I am using POSTMAN application.
To register we can simply send a simple POST request : 

![image](https://github.com/Bartemnius/Registration/assets/64535843/46c53ab2-4a2e-45e3-8118-67f5ffa2f85d)
  

I used json credentials: 
{
    "firstName": "someUser",
    "lastName" : "someUser", 
    "email" : "someUser@gmail.com",
    "password" : "password"
}

After sending we get a response with a token that we are using later on to verify user.
![image](https://github.com/Bartemnius/Registration/assets/64535843/03b86744-058f-4265-8a30-a0760da27183)

We can also see that our user is now present in database and his password is ecrypted.
![image](https://github.com/Bartemnius/Registration/assets/64535843/1bcf468d-9ce2-4c45-bf18-a258040a81ca)


To see if we get mail from server the maildev application is used -> https://github.com/maildev/maildev
After instalation and running the app we can see default values for ip and port number that we will use in out application.
<br>
![image](https://github.com/Bartemnius/Registration/assets/64535843/04eadbb5-d1fe-4eb0-9660-ce26af4952ec)


If server was runing during sending of our POST request we should get email with confirmation link on http://localhost:<port_number>/#/ (in our case http://localhost:1080/#/)
![image](https://github.com/Bartemnius/Registration/assets/64535843/82457d7b-246c-423d-b6d3-47063e1a54d0)

After clicking the link the  info "Confirmed!" should appear.
<p>
  <img src="https://github.com/Bartemnius/Registration/assets/64535843/75b08ef6-cf4d-46d7-bdb3-ef631d2ff72e">
</p>

And that is it user is now register! We can see it in our DB that enable will be now true and we can log in! 

