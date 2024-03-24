package org.example.profise.controller;

import org.example.profise.api.VmApiDelegate;
import org.example.profise.dto.PostVm;
import org.example.profise.dto.PostVmResult;
import org.example.profise.dto.PostVmResultOk;
import org.example.profise.exception.BadRequestException;
import org.example.profise.service.VmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class VmController implements VmApiDelegate {

    @Autowired
    VmService vmService;

    @Override
    public ResponseEntity<PostVmResult> vmPost(PostVm postVm) throws Exception {
        if (postVm == null || postVm.getSize().longValue() == 0 || postVm.getSize().longValue() % 2 != 0) {
            throw new BadRequestException("Size must be a power of 2");
        }
        if (postVm.getId() == null || postVm.getSize() == null || postVm.getTask() == null) {
            throw new BadRequestException("Null fields are against schema");
        }
        Optional<Long> result = vmService.allocateResource(postVm.getId().longValue(), postVm.getSize().longValue(), postVm.getTask());
        return result.map(aLong -> ResponseEntity.ok(new PostVmResult().result(PostVmResult.ResultEnum.OK).hostId(BigDecimal.valueOf(aLong)))).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body(new PostVmResult().result(PostVmResult.ResultEnum.NOT_OK)));
    }

}
