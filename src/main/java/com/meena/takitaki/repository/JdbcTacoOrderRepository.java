package com.meena.takitaki.repository;

import com.meena.takitaki.model.IngredientRef;
import com.meena.takitaki.model.Taco;
import com.meena.takitaki.model.TacoOrder;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import jdk.internal.org.objectweb.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcTacoOrderRepository implements TacoOrderRepository {
  private final JdbcOperations jdbcOperations;

  public JdbcTacoOrderRepository(JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  @Transactional
  // because we want to write in all the tables, if any of the write operation fails it should fail the whole operation
  public TacoOrder save(TacoOrder order) {
    PreparedStatementCreatorFactory pscf =
        new PreparedStatementCreatorFactory(
            "insert into taco_order "
                + "(delivery_name, delivery_street, delivery_city, "
                + "delivery_state, delivery_zip, cc_number, "
                + "cc_expiration, cc_cvv, placed_at) "
                + "values (?,?,?,?,?,?,?,?,?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
    pscf.setReturnGeneratedKeys(true);
    order.setPlacedAt(LocalDateTime.now());
    PreparedStatementCreator psc =
        pscf.newPreparedStatementCreator(
            Arrays.asList(
                order.getDeliveryName(),
                order.getDeliveryStreet(),
                order.getDeliveryCity(),
                order.getDeliveryState(),
                order.getDeliveryZip(),
                order.getCcNumber(),
                order.getCcExpiration(),
                order.getCcCVV(),
                order.getPlacedAt()));
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long orderId = keyHolder.getKey().longValue();
    order.setId(orderId);
    List<Taco> tacos = order.getTacos();
    int i = 0;
    for (Taco taco : tacos) {
      saveTaco(orderId, i++, taco);
    }
    return order;
  }


  private long saveTaco(Long orderId, int orderKey, Taco taco) {
    taco.setCreatedAt(LocalDateTime.now());
    PreparedStatementCreatorFactory pscf =
        new PreparedStatementCreatorFactory(
            "insert into taco "
                + "(name, created_at, taco_order, taco_order_key) "
                + "values (?, ?, ?, ?)",
            Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
    pscf.setReturnGeneratedKeys(true);
    PreparedStatementCreator psc =
        pscf.newPreparedStatementCreator(
            Arrays.asList(
                taco.getName(),
                taco.getCreatedAt(),
                orderId,
                orderKey));
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long tacoId = keyHolder.getKey().longValue();
    taco.setId(tacoId);

    List<IngredientRef> ingredientRefs = taco.getIngredients().stream()
        .map(ingredient -> new IngredientRef(ingredient.getId()))
        .collect(Collectors.toList());

    saveIngredientRefs(tacoId, ingredientRefs);
    return tacoId;
  }

  private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
    int key = 0;
    for (IngredientRef ingredientRef : ingredientRefs) {
      jdbcOperations.update(
          "insert into ingredient_ref (ingredient, taco, taco_key) "
              + "values (?, ?, ?)",
          ingredientRef.getIngredient(), tacoId, key++);
    }
  }


}

