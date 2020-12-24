package com.neardySoftTest.neardy.models;

import javax.persistence.*;

@Entity
@Table(name="coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column
    private int x1;
    @Column
    private int x2;
    @Column
    private int x3;
    @Column
    private int x4;
    @Column
    private int x5;
    @Column
    private int x6;
    @Column
    private int y1;
    @Column
    private int y2;
    @Column
    private int y3;
    @Column
    private int y4;
    @Column
    private int y5;
    @Column
    private int y6;

    public Coordinates(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
    }

    public Coordinates(int x1, int x2, int x3, int x4, int x5, int x6, int y1, int y2, int y3, int y4, int y5, int y6) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.y6 = y6;
    }

    public Coordinates(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
