package top.inkly.shared.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import top.inkly.shared.infrastructure.output.user.adapter.UserAdapter;
import top.inkly.shared.infrastructure.output.user.client.UserClient;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({UserAdapter.class})
@EnableFeignClients(clients = {UserClient.class})
public @interface EnableUserClient {

}
