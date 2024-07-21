package com.BytesCoders.GymManagementSystem.exception;

public class SeatNotAvailableException extends RuntimeException {
	private static final long serialVersionUID=1L;
	public SeatNotAvailableException() {
        super("No seats available for the selected slot and item.");
    }
}
