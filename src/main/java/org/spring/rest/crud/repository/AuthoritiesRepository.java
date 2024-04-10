package org.spring.rest.crud.repository;

import org.spring.rest.crud.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

  @Query(value = "SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH AS COLUMN_SIZE, IS_NULLABLE, COLUMN_KEY, COLUMN_DEFAULT, EXTRA FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = :tableSchema AND TABLE_NAME = :tableName", nativeQuery = true)
  List<Object[]> getTableStructure(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

  @Query(value = "SELECT id FROM authorities WHERE authority = :authority", nativeQuery = true)
  List<Integer> getIdsByUserName(@Param("authority") String authority);


}
