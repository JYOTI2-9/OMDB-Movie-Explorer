
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


