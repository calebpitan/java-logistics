package com.calebpitan.logistics.utils.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> implements Persistable<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private PK id;

    @Nullable
    public PK getId() {
        return id;
    }

    protected void setId(@Nullable PK id) {
        this.id = id;
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }
}
