# 1. List all of the annotations you learned from this class session

# 2. Explain tight coupling vs loose coupling and what does Spring IOC do?

Tight coupling is a strong relationship between components, such as calling constructors. This makes it difficult to swap implementations and unit testing.

Loose Coupling means components rely on abstractions. Components are not directly created inside each other.

Spring IoC controls object creation and injection. Programmer only declares dependencies and IoC will inject them. This creates a loose coupling between onjects.

# 3. What is MVC pattern?

MVC is Model-View-Controller. Model represents data and business logic. View renders the UI of data. Controllers handles user request, interact with model and returns a view.

# 4. What is Front-Controller?

Front-controller is a design pattern that a single handler handles all client requests. In Spring MVC, this is the `DispatcherServlet`.

# 5. Explain DispatcherServlet and how it works.

`DispatcherServlet` intercepts all incoming HTTP requests, use handlermapper to find corresponding controller and delegate the request to the controller. Then it will get the result (`ModelAndView`) from the controller, resolve the view with view resolver and send it back to the client.

Here is the work flow:

Client Request
     ↓
DispatcherServlet (Front Controller)
     ↓
HandlerMapping → Controller
     ↓
Controller processes and returns Model + View name
     ↓
ViewResolver resolves actual view file
     ↓
View rendered with Model data
     ↓
Client gets the Response

# 6. What is JSP and What is Model And View？

JSP is JavaServer Pages. It is a view technology that allows programmer to mix Java code and HTML for dynamic pages.

`ModelAndView` is a class in Spring MVC that combines:

| Part            | Meaning                                                                  |
| --------------- | ------------------------------------------------------------------------ |
| **Model** | Data you want to pass to the view (e.g., user name, list of products).   |
| **View**  | The name of the view to render (like `greeting.jsp` or `home.html`). |

It is used by JSP to create a view.

# 7. Explain servlet and servlet container, name some servlet implementations and servlet containers other than tomcat

A servlet is a Java class running on server that handles HTTP requests and generate responses.

A servlet container is basically an engine that runs servlets. Its main responsibilities include:

* Loads, manages, and executes Servlets.
* Maps URLs to Servlet classes.
* Handles threading, lifecycle, and session management.

Eclipse Jetty is both an implementation and a container.

GlassFish is a full java EE server, includes servlet container (reference implementation).

# 8. Clone this repo, and run it on you local, https://github.com/CTYue/springmvc5-demo

    1. Notice that you need to configure the Tomcat by yourself.
    2. find out the APIs in controlelr and call some APIs, In slides, I also list some API.
    3. remeber to setup mysql database for this project
    4. Test APIs (controllers) in postman
