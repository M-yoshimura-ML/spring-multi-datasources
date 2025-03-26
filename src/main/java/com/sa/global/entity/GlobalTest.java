package com.sa.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "global_test")
public class GlobalTest {
    @Id
    @Column(name = "test_id")
    private int id;

    @Column(name = "test_column")
    private String testColumn;
}
