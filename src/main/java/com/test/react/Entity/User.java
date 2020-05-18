package com.test.react.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String name;

    private int age;

    private String address;

    public User changeAge(int age){
        this.age = age;
        return this;
    }

    public User changeAddress(String address){
        this.address = address;
        return this;
    }
}
