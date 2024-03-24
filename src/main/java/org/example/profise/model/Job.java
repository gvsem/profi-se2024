package org.example.profise.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @Getter @Setter
    private Long id;

    @Column()
    @Getter @Setter
    private Long size;

    @Column()
    @Getter @Setter
    private String task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vm_id")
    @Getter @Setter
    protected Vm vm;

    public Job(Long machineId, Long size, String task) {
        this.id = machineId;
        this.size = size;
        this.task = task;
    }

    public Job() {

    }
}
