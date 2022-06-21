package jpabook.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="BOARD")
/*@SequenceGenerator(name="BOARD_SEQ_GENERATOR",sequenceName = "BOARD_SEQ",initialValue = 1,allocationSize = 1)*/

@TableGenerator(
        name = "BOARD_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "BOARD_SEQ", allocationSize = 1)
public class Board {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOARD_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "BOARD_SEQ_GENERATOR")
    private int id;

    public String name;

}
