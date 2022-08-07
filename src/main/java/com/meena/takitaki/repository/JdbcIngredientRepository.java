//package com.meena.takitaki.repository;
//
//import com.meena.takitaki.model.Ingredient;
//import com.meena.takitaki.model.Ingredient.Type;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository {
//
//  private final JdbcTemplate jdbcTemplate;
//
//  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//  }
//
//  @Override
//  public List<Ingredient> findAll() {
//    return jdbcTemplate.query("select * from ingredient", this::mapRowToIngredients);
//  }
//
//  @Override
//  public Optional<Ingredient> findById(String id) {
//    List<Ingredient> result = jdbcTemplate.query("select * from ingredient where id = ?", this::mapRowToIngredients,
//        id);
//    return result.isEmpty()
//        ? Optional.ofNullable(null)
//        : Optional.of(result.get(0));
//  }
//
//  @Override
//  public Ingredient save(Ingredient ingredient) {
//    jdbcTemplate.update(
//        "insert into ingredient(id, name , type) values(?, ?, ?) ",
//        ingredient.getId(), ingredient.getName(), ingredient.getType().toString()
//    );
//
//    return ingredient;
//  }
//
//  private Ingredient mapRowToIngredients(ResultSet resultSet, int i) throws SQLException {
//    return new Ingredient(
//        resultSet.getString("id"),
//        resultSet.getString("name"),
//        Type.valueOf(resultSet.getString("type"))
//    );
//  }
//}
