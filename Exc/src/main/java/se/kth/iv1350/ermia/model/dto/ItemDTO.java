package se.kth.iv1350.ermia.model.dto;

public record ItemDTO(int itemId, double vatRate, String name, String description, double price) {
}
