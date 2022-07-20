package auth.feign;

import auth.entity.PersonalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="auth-service", url="localhost:8088/personal")
public interface AuthClient {
    @RequestMapping(method = RequestMethod.GET,value = "/")
    List<PersonalDTO> getAll();

}
