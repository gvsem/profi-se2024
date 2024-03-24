package org.example.profise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.profise.service.VmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class LoadTests {

    @Autowired
    VmService vmService;

    @Test()
    public void basicLoadTest() {
        long k = 0;
        for (int i = 0; i < 10000 * 2; i++) {
            assertTrue(vmService.allocateResource(k++, 64L, "").isPresent());
        }
        for (long i = 1; i <= 128; i++) {
            assertTrue(vmService.allocateResource(k++, i, "").isEmpty());
        }
    }

    @Test()
    public void basicLoadTestWithSmallRams() {
        long k = 0;
        for (int i = 0; i < 10000 * 128; i++) {
            assertTrue(vmService.allocateResource(k++, 1L, "").isPresent());
        }
        for (long i = 1; i <= 128; i++) {
            assertTrue(vmService.allocateResource(k++, i, "").isEmpty());
        }
    }

    @Test()
    public void basicLoadTestWithLoadCheck() {
        Map<Long, Long> servers = new HashMap<>();
        long k = 0;
        for (int i = 0; i < 10000 * 128; i++) {
            var v = vmService.allocateResource(k++, 1L, "");
            assertTrue(v.isPresent());
            servers.put(v.get(), servers.getOrDefault(v.get(), 0L) + 1);
            assertTrue(servers.get(v.get()) <= 128);
        }
        for (long i = 1; i <= 128; i++) {
            assertTrue(vmService.allocateResource(k++, i, "").isEmpty());
        }
    }


}
