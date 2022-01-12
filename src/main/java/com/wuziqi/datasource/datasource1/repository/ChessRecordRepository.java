package com.wuziqi.datasource.datasource1.repository;

import com.wuziqi.datasource.datasource1.model.ChessRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessRecordRepository extends JpaRepository<ChessRecord, Integer> {
}
