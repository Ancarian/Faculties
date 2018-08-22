package dev.chermenin.service.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> model;
    private String template;
}
