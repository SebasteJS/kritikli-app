package com.example.demo.model;

import com.example.demo.model.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class Role {

    public Role(RoleType roleType) {
        type = roleType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType type;

    @Override
    public String toString() {
        return "" + type;
    }

}
