package org.javaboy.openfeign;

import org.javaboy.api.IUserService;
import org.javaboy.commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider",fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloService extends IUserService {
}
