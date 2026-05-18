package top.inkly.shared.infrastructure.config;

import org.springframework.context.annotation.Import;
import top.inkly.shared.infrastructure.output.queues.config.RabbitMQConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RabbitMQConfig.class})
public @interface EnableRabbitMQ {

}
