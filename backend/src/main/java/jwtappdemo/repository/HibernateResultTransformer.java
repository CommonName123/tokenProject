package jwtappdemo.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import java.lang.reflect.Field;
import java.util.Arrays;

import com.github.fluent.hibernate.internal.util.InternalUtils;
import com.github.fluent.hibernate.internal.util.reflection.NestedSetter;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;

public class HibernateResultTransformer extends FluentHibernateResultTransformer {
    private final Class<?> resultClass;
    private NestedSetter[] setters;

    public HibernateResultTransformer(Class<?> resultClass) {
        super(resultClass);
        this.resultClass = resultClass;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        createCachedSetters(resultClass, aliases);

        Object result = InternalUtils.ClassUtils.newInstance(resultClass);

        Field[] fields = resultClass.getDeclaredFields();
        Arrays.sort(fields, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));

        for (int i = 0; i < aliases.length; i++) {
            Field field = findField(fields, aliases[i]);
            Convert convert = field != null ? field.getAnnotation(Convert.class) : null;
            if (convert != null) {
                AttributeConverter attributeConverter = (AttributeConverter) InternalUtils.ClassUtils.newInstance(convert.converter());
                setters[i].set(result, attributeConverter.convertToEntityAttribute(tuple[i]));
            } else {
                setters[i].set(result, tuple[i]);
            }
        }

        return result;
    }

    private void createCachedSetters(Class<?> resultClass, String[] aliases) {
        if (setters == null) {
            setters = createSetters(resultClass, aliases);
        }
    }

    private static NestedSetter[] createSetters(Class<?> resultClass, String[] aliases) {
        NestedSetter[] result = new NestedSetter[aliases.length];

        for (int i = 0; i < aliases.length; i++) {
            result[i] = NestedSetter.create(resultClass, aliases[i]);
        }

        return result;
    }

    private Field findField(Field[] fields, String propertyName) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equalsIgnoreCase(propertyName)) {
                return fields[i];
            }
        }
        return null;
    }
}
