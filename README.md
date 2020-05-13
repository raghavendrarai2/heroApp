This application exposes an HTTP REST API that uses JSON that fulfils the following criteria:
<ul>
<li> Users can create a Movie</li>
<li>the movie should have a title, a category and a star rating (from 0.5 to 5)</li>
<li>Users can retrieve a list of movies</li>
<li> Users can retrieve a single movie by ID</li>
<li>Users can update a movie</li>
<li>Users can delete a movie</li>
</ul


<h3>Running Examples</h3>
Download the zip or clone the Git repository.<br>
Unzip the zip file (if you downloaded one)<br>
Open Command Prompt and Change directory (cd) to folder containing pom.xml<br>
Open Eclipse<br>
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip<br>
Select the right project<br>
Choose the Spring Boot Application file (search for @SpringBootApplication)<br>
Right Click on the file and Run as Java Application<br>
Set mysql configuration application.properties <br>
You are all Set


<br>
<br>
<br>
All apis are exposed on Swagger (http://localhost:9000/swagger-ui.html#/)
