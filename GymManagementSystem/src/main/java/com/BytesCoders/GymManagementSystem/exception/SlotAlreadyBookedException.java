package com.BytesCoders.GymManagementSystem.exception;

@SuppressWarnings("serial")
public class SlotAlreadyBookedException extends RuntimeException {
    public SlotAlreadyBookedException(String message) {
        super(message);
    }
}
