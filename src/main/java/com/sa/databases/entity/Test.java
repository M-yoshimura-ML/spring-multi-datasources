package com.sa.databases.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "test_id")
    private int testId;

    @Column(name = "test_column")
    private String testColumn;
}
