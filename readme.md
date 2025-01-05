source: https://www.kaggle.com/datasets/jealousleopard/goodreadsbooks?resource=download

## next features to add
- validate the user input - range(MIN, MAX), non-numeric value/ non-integer value
- filter question: only include books released from x year on
- filter question: what is the maximum number of pages the book should have
- save the year and number pages counts in a text file stat.csvfile 'stat.csv'. 
- display average year and number values on UI when user quits the app

```
    Req: Scanner, Math class, conditional, loop, FileWriter, FileReader
```

## Desktop app:

- Create an interface to enter the year and number of max pages

```
    year: textbox
    max pages: textbox
```

- create button with label "recommend" that starts the process and displays the output
- add feature option: randomize output of books with minimum average_rating of x to avoid always only resulting in same top books

```
    Req: Swing: JPanel, JWindow, JButton, JTextFiel etc
```


#### Two player:
- Update the GUI or Console application to allow two users to play with the computer. Both of the users can enter their guess and click Play. The user that made the correct guess will win

```
    GUI mockup:

    User A's Guess   [  Text Box  ]
    
    User B's Guess   [  Text Box  ]

                     [    PLAY    ]
```

#### (Optional) Multi-Player Game - over socket connection:
- Update the GUI application to allow several users to play simultaneously. All the users will have a copy of the application and can join the Game by running the application on their computer. The first user to start the game can act as a Server.

#### Web Application:

- Create a web application to play the same game in the browser. Reuse the previous code on the backend

    Req: Use Spring Boot / Spring Web , HTML (JSP or Thymeleaf)

- Add a sign-up page to register users. Update logic to allow only the registered/logged-in users to play. 
    
    Req: DB(Use H2), Spring Data JPA to store User info, Spring Security
 
- (Optional) Use ReCaptcha to prevent robot making requests.

- Block users from playing more than 1 hour. Lock them for 2 hours.

- (Optional) Two-player: list online users and provide the ability to request/accept to play with the user. Use WebSocket to listen for updates in realtime.

- (Optional) Make it multi-player

- Store the win/loss statistics into DB.

-  Generate a CSV report with stats about the winner, numbers, etc that you can download it from the web interface. 

    Req: https://github.com/gtiwari333/spring-boot-blog-app/blob/master/src/main/java/gt/app/web/mvc/DownloadController.java

-  Schedule the report to run every day and deliver it to your email address.

    Req: Spring `@Scheduled` 

-  Setup a background job that sends an account deactivation email if the user is not logged-in in last 20 days

-  Setup a background job to deactivate user if the user is not logged-in in the last 30 days

-  Setup a public web REST API to expose information about the winners

    Req: Spring `@RestController`

-  Use caching to read user profile from the cache instead of reading from DB on every request
    
    Req: Spring's `@Cacheable`

-  Setup a Dockerfile script to run your app in docker. Or use buildpack plugin

-  Setup static code analysis with local SonarQube instance. You can use docker to run SonarQube. Take care of SonarQube warnings.

-  Deploy your app in a cloud environment (eg Heroku, AWS, Azure, Oracle). Use free tier resources. Oracle has "always-free" tier.

- Write e2e test

- ...


### Note: 

- Focus on readability, reusability throughout the development.
- Try to make your app modular
- Use the build system eg: Maven, Gradle
- Use git
- Writ tests(unit, integration) after each step 
