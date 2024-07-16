package br.com.araujo.jonas.ForumHub.infra;

public class DataAlreadyRegisteredException extends RuntimeException {
    public DataAlreadyRegisteredException(String message) {
        super(message);
    }
}
