package test.spring.kafkademo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestService {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public String insert() {
		ListenableFuture<SendResult<String,String>> future =  kafkaTemplate.send("hub1"/*토픽*/,null /*파티션*/,LocalDateTime.now().toString());
		future.addCallback(sCallback -> {
			log.info("Success");sCallback.getRecordMetadata().offset();sCallback.getRecordMetadata().partition();
		}, fCallback -> {
			
		});
		return "{}";
	}

}
