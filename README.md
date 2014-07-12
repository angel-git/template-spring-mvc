template-spring-mvc
===================

This is a test on how to do a template engine for Spring MVC

## what it does

- access to your message source using `#{}`
- include other templates using `@{}`
- access to spring model using `#{}`

## basic config in your servlet config

    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basename" value="messages.messages" />
    </bean>

    <bean id="myTemplateEngine" class="com.ags.my.template.MyTemplateEngine">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".html" />
        <property name="messageSource" ref="messageSource"/>
    </bean>

    <bean class="com.ags.my.spring.web.servlet.MyViewResolver">
        <property name="templateEngine" ref="myTemplateEngine"/>
    </bean>