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
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(unique = true)
    private String name;
}
