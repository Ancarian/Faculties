package dev.chermenin.dao.util;

import dev.chermenin.dao.specs.SpecificationBuilder;
import dev.chermenin.model.impl.BaseObject;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecsBuilder<T extends BaseObject> {
    private final static String regex = "(\\w+?)([:<>])(\\w+?),";

    public Specification<T> createSpecification(String searchStr) {
        SpecificationBuilder<T> builder = new SpecificationBuilder<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(searchStr + ",");
        while (matcher.find()) {
            if (matcher.group(1).contains("_")){
                String[] join = matcher.group(1).split("_");
                builder.with(join[0],
                        matcher.group(2),
                        matcher.group(3), join[1]);
            }else {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3), null);
            }

        }
        return builder.build();
    }
}
