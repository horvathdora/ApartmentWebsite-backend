package com.reservation.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column
    private RoleName name;

    public Role(){
    }

    public Long getRoleId() {
        return id;
    }

    public void setRoleId(Long roleId) {
        this.id = roleId;
    }

    public RoleName getName() {
        return name;
    }

    public void setRole(RoleName role) {
        this.name = role;
    }
}
