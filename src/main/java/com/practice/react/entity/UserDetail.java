package com.practice.react.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_DETAIL")
@SequenceGenerator(
        name = "USER_DETAIL_SEQ_INDEX_GEN",
        sequenceName = "USER_DETAIL_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class UserDetail {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_DETAIL_SEQ_INDEX_GEN"
    )
    @Column(name = "ID", unique = true)
    private Long id;

    private Long userId;

    private int age;

    private String address;

    @OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY)
    private User user;

    public UserDetail changeAge(int age){
        this.age = age;
        return this;
    }

    public UserDetail changeAddress(String address){
        this.address = address;
        return this;
    }
}
