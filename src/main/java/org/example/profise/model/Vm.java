package org.example.profise.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "vms")
public class Vm {

    public Vm(Long id) {
        this.id = id;
    }

    @Id
    @Getter @Setter
    private Long id;

    @OneToMany()
    @Getter
    protected List<Job> jobs = new ArrayList<>();

    @Transient
    @Getter @Setter
    private Long load = 0L;

    @Column
    @Getter @Setter
    private Long capacity = 128L;

    public Vm() {

    }

    @Transient
    public double getLoadCoefficient() {
        return (double) load / capacity;
    }

    public static class VmLoadComparator implements Comparator<Vm> {

        @Override
        public int compare(Vm a, Vm b) {
            return (int) (a.getLoadCoefficient() - b.getLoadCoefficient());
        }
    }


}
