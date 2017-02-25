package ca.mcgill.ecse223.tileo.controller;

public class InvalidInputException extends Exception {

	public InvalidInputException(String error){
		super(error);
	}
}
