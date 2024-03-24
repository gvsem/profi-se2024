package org.example.profise.service;

import org.example.profise.model.Job;
import org.example.profise.model.Vm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.PriorityQueue;

@Service
@Transactional
public class VmService {

    private final PriorityQueue<Vm> pool = new PriorityQueue<>(new Vm.VmLoadComparator());

    public VmService() {
        final int N = 10000;

        for (long i = 0; i < N; ++i) {
            pool.add(new Vm(i));
        }
    }

    public synchronized Optional<Long> allocateResource(Long machineId, Long size, String task) {
        Vm minLoadVm = pool.peek();
        assert (minLoadVm != null); // N > 0
        if (minLoadVm.getLoad() + size <= minLoadVm.getCapacity()) {
            minLoadVm.setLoad(minLoadVm.getLoad() + size);
            minLoadVm.getJobs().add(new Job(machineId, size, task));
            pool.remove(minLoadVm);
            pool.add(minLoadVm);
            return Optional.of(minLoadVm.getId());
        } else {
            return Optional.empty();
        }
    }

}
