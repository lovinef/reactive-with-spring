package com.practice.react.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="USER")
@SequenceGenerator(
        name = "USER_SEQ_INDEX_GEN",
        sequenceName = "USER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_SEQ_INDEX_GEN"
    )
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Orders> orders;

    public User changeName(String name){
        this.name = name;
        return this;
    }

    public void addOrders(Orders order){
        orders.add(order);
    }
}
