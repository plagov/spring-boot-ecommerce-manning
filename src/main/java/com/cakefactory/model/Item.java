package com.cakefactory.model;

import java.math.BigDecimal;

public record Item(String sku, String title, BigDecimal price) {
}
