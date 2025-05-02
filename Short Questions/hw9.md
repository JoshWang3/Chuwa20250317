1. List all of the annotations you learned from class and homework to annotaitons.md (your own
   cheatsheet).
2. Compare Spring and Springboot? What are the benfits of Springboot?
   Spring is the core framework, which gives us the tools to build java applications, but we have to configure everything manually.
   Spring Boot is part of the Spring ecosystem. It’s built on top of Spring. It auto-configure applications so we can set things up faster and easier.

   Less Configuration: no need to write a lot of set up manually
   Build-in Server: No need to install tomcat or jetty, as it's preconfigured in Spring Boot
   Faster Development
   Production Tools: build-in health checks, metrics and monitoring via Actuator
   Starter Templates: eaily add things like web, security or JPA with simple starter dependencies
3. What is IOC and What is DI?
   Inversion of Control is a design principle where the control of creating and managing objects is transferred from the developer to the framework
   Dependency Injection is a way of implementing Ioc. Instead of creating dependencies manually, the framework injects them where needed. There three main types of DI:
4. What is @CompnonentScan ?
   @ComponentScan is a Spring annotation that tells the framework: Look in these packages, find the classes marked as beans, and automatically register them in the Spring container.
5. What is @SpringbootApplication ?
   In almost every Spring Boot project, we use @SpringBootApplication only once, and it goes on the main class that starts the application.
6. How many ways to define a bean? Provide code examples.
   Using @Component and its specializations (@Service, @Repository, @Controller)
   Using @Bean inside a @Configuration class
   Using XML configuration (old-style, mostly in legacy projects).
7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean .
   @Component The default bean name is the class name with the first letter in lowercase
   @Bean The default name is the method name
8. Compare @component and @service , @repository , @controller ?
   Using @Component when we control the class and want Spring to auto-detect it.
   Using @Bean when we want full control or need to register external/third-party classes.
9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
   Annotation	Layer	Purpose	Extra Behavior?
   @Component	Generic (any layer)	Base annotation — generic bean	No extra behavior
   @Service	Service layer	Holds business logic	No extra behavior — semantic only
   @Repository	Data access layer	Handles database interaction (DAOs)	Yes — auto exception translation
   @Controller	Web layer (MVC)	Handles web requests, returns views or JSON	Yes — integrates with Spring MVC
10. How many annotaitons we can use to inject a bean?
    @Autowired,@Qualifier,@Resource,@Inject
11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
    Constructor Injection: most recommanded, makes dependencies immutable and testable
    Setter Injection: good for optional dependencies
    Field Injection: Quick to write, but harder to test and not ideal for complex apps
12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to
    inject if no primay, demo with code examples.
    Mark one as @Primary
    Use @Qualifier when there is no @Primary
    If no bean is marked @Primary and there are multiple candidates, you must use @Qualifier to specify which one to inject. Otherwise, Spring will throw a NoUniqueBeanDefinitionException.
13. Compare BeanFactory and ApplicationContext in Spring framework?
    Feature	BeanFactory	ApplicationContext
    Core Role	Basic bean container	Advanced container (extends BeanFactory)
    Bean Loading	Lazy (loaded when requested)	Eager (beans loaded at startup by default)
    Application Events	Not supported	Supports event publishing/listening
    Internationalization	Not supported	Supports i18n (message sources)
    AOP Support	Limited or none	Fully supported
    Used In	Lightweight / legacy apps	Most Spring apps (especially Spring Boot)
14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
    Bean scope tells Spring how and when to create and manage bean instances in the application context. By default, Spring beans are singleton, but we can change this with the @Scope annotation.
    singleton	One instance per Spring container (default)	All applications
    prototype	A new instance every time it's requested	All applications
    request	One instance per HTTP request	Web only
    session	One instance per HTTP session	Web only
    application	One instance per ServletContext	Web only
    websocket	One instance per WebSocket session	WebSocket apps
15. Write a Spring application that registers and autowires beans,
    Demo different types of dependency injection
    Demo bean scopes.
    Demo dependency injection by type and by name, when there's ambiguity in bean definition.
    Demo bean registration by both @Component and @Bean
16. Explain builder pattern with code examples.
    The Builder Pattern helps us create objects step by step by using a builder class instead of a long constructor with many parameters.
    It solves the problem of having too many constructor arguments and makes the code easier to read, flexible, and maintainable.