🎬 OMDB Movie Explorer:-

OMDB Movie Explorer is a full-stack movie search application built using Spring Boot and js.
It fetches movie data from the OMDB API and displays detailed information in a clean UI.

✨ Features
==============================
🔍 Search movies by title
📄 View movie details (plot, director, actors, ratings)
⚡ Fast responses using in-memory caching
🌐 RESTful backend APIs
🛠 Tech Stack
🧩 Backend: Java, Spring Boot
🎨 Frontend: React, HTML, CSS, JavaScript
## 🏗️ Project Architecture

The project follows a classic client-server model:

## 🧮 API Endpoints
| Function | Method | Endpoint |
| :--- | :--- | :--- |
| Search for movies by title | `GET` | `/api/movies/search?title={title}` |
| Get detailed information by imdbId | `GET` | `/api/movies/{imdbId}` |
---
| Postman Testing | ![Register Page](OUTPUT/postman-testing.png) |
---
2. **Configure API Key**

Open `src/main/resources/application.properties` and add your OMDB API key:
```properties
omdb.api.key=YOUR_API_KEY_HERE
```
3. **Run the backend**
```bash
mvn clean install
mvn spring-boot:run
```
Backend will start on `http://localhost:8080`


