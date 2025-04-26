## `@EnableWebSecurity`
- Enables Spring Security's web support.

### Example:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
}
```

## `@EnableGlobalMethodSecurity(prePostEnabled = true)`
- Enables method-level security annotations.


### Example:
```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
}
```

## `@PreAuthorize`, `@PostAuthorize`
- Restricts access to methods based on roles or permissions.

### Example:
```java
@PreAuthorize("hasRole('ADMIN')")
@PostAuthorize("returnObject.owner == authentication.name")
public Document getDocument(Long id) {
    return documentRepository.findById(id);
}
```

## `@WithMockUser`
- Simulates a logged-in user in unit tests.

### Example:
```java
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminAccess() throws Exception {
        mockMvc.perform(get("/admin"))
               .andExpect(status().isOk());
    }
}
```