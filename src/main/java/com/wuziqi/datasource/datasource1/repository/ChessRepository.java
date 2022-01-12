package com.wuziqi.datasource.datasource1.repository;

import com.wuziqi.datasource.datasource1.model.Chess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessRepository extends JpaRepository<Chess, Integer> {
}
