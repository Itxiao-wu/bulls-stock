package com.itheima.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="part")
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name="partname")
    private String partname;

    @Override
    public String toString() {
        return "Part{" +
                "pid=" + pid +
                ", partname='" + partname + '\'' +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public Part(String partname) {
        this.partname = partname;
    }

    public Part() {
    }
}
