package br.com.araujo.jonas.ForumHub.infra.exception;

public class DataAlreadyRegisteredException extends RuntimeException {
    public DataAlreadyRegisteredException(String message) {
        super(message);
    }
}
