package com.retail.tienda.proyections;

import java.math.BigDecimal;

public interface TotalProyection {
    BigDecimal getVentas();

    BigDecimal getCompras();

    BigDecimal getDevoluciones();

    BigDecimal getStock();
}