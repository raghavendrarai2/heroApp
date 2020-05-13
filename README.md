This application exposes an HTTP REST API that uses JSON that fulfils the following criteria:
● Users can create a Movie
○ the movie should have a title, a category and a star rating (from 0.5 to 5)
● Users can retrieve a list of movies
● Users can retrieve a single movie by ID
● Users can update a movie
● Users can delete a movie



<h3>Running Examples</h3>
Download the zip or clone the Git repository.
Unzip the zip file (if you downloaded one)
Open Command Prompt and Change directory (cd) to folder containing pom.xml
Open Eclipse
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
Select the right project
Choose the Spring Boot Application file (search for @SpringBootApplication)
Right Click on the file and Run as Java Application
You are all Set



All apis are exposed on Swagger (http://localhost:9000/swagger-ui.html#/)
