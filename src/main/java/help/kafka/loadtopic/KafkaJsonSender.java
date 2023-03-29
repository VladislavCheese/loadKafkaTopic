package help.kafka.loadtopic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class KafkaJsonSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaJsonSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendJson(String topic, String json) {
        kafkaTemplate.send(topic, json);
    }

    public void startSending(String topic) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while(true) {

            executorService.submit(() -> {
                String json = "{\"hello\":\"world\"}";
                kafkaTemplate.send(topic, json);
            });
        }
    }
}