package com.cakefactory.model;

public record AccountAddress(
        String email,
        String addressLine1,
        String addressLine2,
        String postcode
) {
}
