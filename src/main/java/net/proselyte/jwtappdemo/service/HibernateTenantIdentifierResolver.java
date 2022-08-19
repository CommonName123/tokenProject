package net.proselyte.jwtappdemo.service;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Класс определяющий схему с которой работает Hibernate
 */
@Component
public class HibernateTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private String defaultSchema = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes != null ? requestAttributes
                .getRequest() : null;

        if (request == null) {
            return defaultSchema;
        }

        String schema = request.getParameter("schema") != null ? request.getParameter("schema") : (String) request.getAttribute("schema");

        return schema != null ? schema : defaultSchema;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public void skipDefaultSchema() {
        this.setDefaultSchema("public");
    }
}
