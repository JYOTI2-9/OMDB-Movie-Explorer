package Springboot.exceptionhandaling;





public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String identifier) {
        super("Movie not found: " + identifier);
    }
}
