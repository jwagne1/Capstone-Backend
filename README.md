# Capstone Project - News To Me App - Backend section
### Description
The News to Me! project is news articles aggregator used to put in practice all the applicable methods and tools the SEI student has learned during the General Assembly SEI Bootcamp, including HTML, CSS, JavaScript, Java, PostGreSQL, Angular and Springboot .
The goal of the application is to create a News aggregator that helps a user see the latest headlines in the USA, register and be able to save articles for future reference.

<img width="1187" alt="NewsToMe-App" src="https://user-images.githubusercontent.com/94083595/152597924-6881d30a-9e68-4e9a-827b-c3234ab81ccc.png">


### Getting started
To use News To Me, the user requires only a web browser and access to internet.

Recommended browsers include: 
* [Firefox](https://www.mozilla.org/en-US/firefox/new/)
* [Chrome](https://www.google.com/chrome/)
* [Safari](https://support.apple.com/downloads/safari)
* [Edge](https://www.microsoft.com/en-us/edge)

### User Stories
* Users should be able to see latest news articles
* Users should be able to see news by categories: business, entertainment, general, health, science, sports, technology
* Users should be able to login
* Logged-in users should be able to save articles to personal feed
* Logged-in users should be able to see personalized feed
* Logged-in users should be able to un-save articles to personal feed


### Technologies used to build the project
* HyperText Markup Language (HTML)
* Cascading Style Sheets (CSS)
* Typescript
* Angular
* Java
* Springboot
* Github Projects

### Planning, Development, and Problem-solving
1. Planning - I started the process reading through the project requirements, and from there implemented these steps to get organized: 
    * Brainstorm project ideas that could be implement within the allocated timeframe. 
    * Created a new Github project to keep track of deliverables.
    * Wrote down basic pseudocode for the different functions I felt were required to complete app.
    * Created ERD for the relationships in the backend app.

![News to Me App - Database ER diagram v2](https://user-images.githubusercontent.com/94083595/152598000-b49b8868-ae41-47c8-b9ad-7590d8fbcb54.png)


2. Development - Started the process by creating a new Angular project for the frontend and a Springboot project for the backend. Once those initial projects were created, the following steps were implemented:
    * Started going over the different lesson videos that were applicable.
    * Identified NewsAPI as the source of the news feed.
    * Entered code in Angular app to pull news from NewsAPI and validated that I was able to pull news.
    * Moved on to the SprinBoot app and created appropriate endpoints and models to be able to create users and store articles.
    * Tested endpoints in Postman to validate that I was able to create and read data from the database.
    * Integrated the front-end to the backend.
    * Tested the functionality to achieve Minimum Viable Project.
    * Moved on to styling the different components using CSS and Boostrap, to meet the project deadline.
3. Problem-solving:
    * Ran into CORS errors, and was unable to get the functionality of saving the articles from the project, so added the through Postman en tested that the GET request was working from the backend. Will continue to work on app to solve this issue. 


### Improvements for future iterations
* Enable the functionality to add articles from Angular frontend by using the save button.
* Add weather information from weather API
* Add ability to get Global news and/or customize news feed to local news by zipcode.
