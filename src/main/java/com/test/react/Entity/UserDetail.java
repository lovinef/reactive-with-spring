package com.test.react.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
}
