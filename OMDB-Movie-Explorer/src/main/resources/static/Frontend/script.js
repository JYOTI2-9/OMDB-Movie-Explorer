
const BASE_URL = "http://localhost:8080/api/movies";

function searchMovies() {
    const title = document.getElementById("searchInput").value;
    document.getElementById("details").innerHTML = "";

    fetch(`${BASE_URL}/search?title=${title}`)
        .then(res => res.json())
        .then(data => {
            if (!data.success) {
                alert(data.message);
                return;
            }
            renderResults(data.data.search);
        })
        .catch(err => console.error(err));
}

function renderResults(movies) {
    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";

    movies.forEach(movie => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
            <img src="${movie.poster}" alt="Poster">
            <h4>${movie.title}</h4>
            <p>${movie.year}</p>
        `;
        card.onclick = () => loadDetails(movie.imdbID);
        resultsDiv.appendChild(card);
    });
}

function loadDetails(imdbId) {
    fetch(`${BASE_URL}/${imdbId}`)
        .then(res => res.json())
        .then(data => {
            if (!data.success) {
                alert(data.message);
                return;
            }
            showDetails(data.data);
        })
        .catch(err => console.error(err));
}

function showDetails(movie) {
    const detailsDiv = document.getElementById("details");
    detailsDiv.innerHTML = `
        <h2>${movie.title}</h2>
        <img src="${movie.poster}" width="200">
        <p><b>Year:</b> ${movie.year}</p>
        <p><b>Director:</b> ${movie.director}</p>
        <p><b>Actors:</b> ${movie.actors}</p>
        <p><b>Plot:</b> ${movie.plot}</p>
        <p><b>IMDB Rating:</b> ${movie.imdbRating}</p>
    `;
}
*/