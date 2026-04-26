package top.inkly.notification_service.infrastructure.output.mail.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateResolver {

    /**
     * Thymeleaf Template Engine that render HTML files outside
     * the Web Context, ideal for Emails.
     */
    private final SpringTemplateEngine templateEngine;

    /**
     * @param templateLocation Where the HTML Template is located at classpath:resource path
     * @param values Map of values to be replaced in the template
     * @return Parsed HTML Template with all the resolved variables
     *
     * <h4>Operation</h4>
     * <p><b>Context</b> is the Thymeleaf Variables Container, so we pass it to Thymeleaf
     * engine for it to resolve the Template Variables Values</p>
     */
    public String resolve(String templateLocation, Map<String, String> values) {
        Context context = new Context();
        context.setVariables(new HashMap<>(values));

        return templateEngine.process(templateLocation, context);
    }

}
