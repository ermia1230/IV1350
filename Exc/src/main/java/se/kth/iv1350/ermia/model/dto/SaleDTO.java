package se.kth.iv1350.ermia.model.dto;

import se.kth.iv1350.ermia.model.Item;

import java.util.List;

public record SaleDTO(List<Item> itemList, double totalPrice, double totalVATAmount) {
}
