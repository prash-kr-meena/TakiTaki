package com.meena.takitaki.repository;

import com.meena.takitaki.model.TacoOrder;

public interface TacoOrderRepository {
  TacoOrder save(TacoOrder order);
}
