package br.com.araujo.jonas.ForumHub.Infra;

public class DataAlreadyRegisteredException extends RuntimeException {
    public DataAlreadyRegisteredException(String message) {
        super(message);
    }
}
