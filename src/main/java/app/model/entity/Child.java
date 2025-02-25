package app.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "childs")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Father father;

    @Override
    public String toString(){
        return name;
    }
}
