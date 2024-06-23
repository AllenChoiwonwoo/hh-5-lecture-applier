package com.hh99.hh5cleanarchitecture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "is_delete")
    private boolean isDelete;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.isDelete = false;
    }
    @PreUpdate
    public void preUpdate(){
        this.modifiedDate = LocalDateTime.now();
    }
}
