package com.meena.takitaki.repository;

import com.meena.takitaki.model.TacoOrder;
import org.springframework.data.repository.Repository;

public interface TacoOrderRepository extends Repository<TacoOrder, Long> {
  TacoOrder save(TacoOrder order);
}
