package ricciliao.mcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.mcp.SendPostKafkaDto;
import ricciliao.x.component.kafka.KafkaProducer;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;

import java.time.Instant;

@Tag(name = "Testing Controller")
@RestController
@RequestMapping("/testing")
public class TestController {

    private KafkaProducer<SendPostKafkaDto> signUpEmailKafka;

    @Qualifier("signUpEmailKafka")
    @Autowired
    public void setSignUpEmailKafka(KafkaProducer<SendPostKafkaDto> signUpEmailKafka) {
        this.signUpEmailKafka = signUpEmailKafka;
    }


    @Operation(description = "Testing")
    @PostMapping("")
    public Response<PayloadData> test() {
        SendPostKafkaDto dto = new SendPostKafkaDto();
        dto.setCode("code");
        dto.setTo("to");
        dto.setExpireAt(Instant.now().plusMillis(5L));
        signUpEmailKafka.send(dto);

        return ResponseUtils.success();
    }

}
