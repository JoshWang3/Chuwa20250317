# List all of the annotations you learned from class and homework to annotaitons.md
✅
# Explain TLS, PKI, certificate, public key, private key, and signature.
## TLS (Transport Layer Security)
- **What it is:** A cryptographic protocol that secures communication over networks (like HTTPS).
- **Purpose:** Ensures confidentiality, integrity, and authentication between two parties (usually browser & server).
- **How it works:** Uses a combination of asymmetric encryption (for initial handshake) and symmetric encryption (for actual data transfer).

# PKI (Public Key Infrastructure)
- **What it is:** A system for managing digital certificates and public-private key pairs.
- **Purpose:** Provides a trusted way to verify identities online.
- **Components:**
    - Certificate Authorities (CAs)
    - Digital Certificates
    - Public/Private Keys
    - Certificate Revocation Lists (CRLs)

# Certificate (a.k.a. Digital Certificate)
- **What it is:** A file that binds an identity (like a website) to a public key.
- **Issued by:** A Certificate Authority (CA).
- **Contains:**
    - Public key
    - Owner’s identity
    - Expiry date
    - Digital signature of the CA

# Public Key
- **What it is:** A cryptographic key you can share openly.
- **Used for:**
    - Encrypting data (only the matching private key can decrypt)
    - Verifying digital signatures
- One half of an asymmetric key pair.

# Private Key
- **What it is:** A secret cryptographic key kept by the owner.
- **Used for:**
    - Decrypting data encrypted with the public key
    - Creating digital signatures
- Must be kept secure — whoever has it can impersonate the owner.

# Digital Signature
- **What it is:** A cryptographic stamp created using a private key to prove the authenticity and integrity of data.
- **How it works:**
	1.	Data is hashed.
	2.	Hash is encrypted using the signer’s private key.
	3.	Receiver can use the signer’s public key to verify that the signature is valid and the data hasn’t been tampered with.

# Write a Spring security based application, which provides https APIs (one simple get controller with empty response is good enough )instead of http, please generate a self-signed certificate to make your https TLS verfication work.
✅

# List all http status codes that related to authentication and authorization failures.
| Code | Name                        | Meaning                             |
|------|-----------------------------|-------------------------------------|
| 401  | Unauthorized                | Not authenticated                   |
| 403  | Forbidden                   | Authenticated but not authorized    |
| 407  | Proxy Authentication Required | Must authenticate with a proxy    |
| 419  | Authentication Timeout (non-standard) | Session expired / CSRF issues |
| 440  | Login Timeout (Microsoft)   | Login session expired               |

# Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization
| Feature        | Authentication                      | Authorization                         |
|----------------|--------------------------------------|----------------------------------------|
| **Definition** | Verifying who you are               | Verifying what you can do              |
| **Focus**      | Identity                            | Access control                         |
| **Happens When** | Always happens before authorization | Happens after authentication           |
| **Example**    | Logging in with username/password   | Allowing access to /admin only if role = ADMIN |
| **HTTP Status**| 401 Unauthorized                    | 403 Forbidden                          |

## Authentication Components
### AuthenticationManager
- Central interface to handle authentication logic.
- Takes an Authentication object and returns an authenticated one (or throws exception).
### AuthenticationProvider
- Actual strategy for validating credentials (e.g., DaoAuthenticationProvider for database users).
- Pluggable — you can add custom providers (e.g., for LDAP, JWT).
### UserDetailsService
- Loads user-specific data by username.
- Returns a UserDetails object containing username, password, roles, etc.
### UserDetails
- A Spring Security interface that represents the authenticated user.
- Contains details like username, password, enabled status, authorities (roles).
### SecurityContext & SecurityContextHolder
- Holds the current Authentication object for the request/thread.
- Used to check who is currently authenticated.

## Authorization Components
### AccessDecisionManager
- Makes the final call on whether a user is authorized.
- Uses AccessDecisionVoter to vote on permission checks.
### GrantedAuthority
- Represents a permission or role assigned to the user (e.g., ROLE_USER, ROLE_ADMIN).
### @PreAuthorize, @Secured, @RolesAllowed
- Annotation-based access control at the method level.
```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser() { ... }
```
### FilterSecurityInterceptor
- Intercepts web requests and applies authorization logic based on URL, roles, etc.

# Explain HTTP Session?
An HTTP Session is a way for the server to maintain state about a client across multiple requests.

Since HTTP is a stateless protocol (each request is independent and doesn’t remember previous ones), the session acts like a memory on the server side to track a user’s interaction (like login, shopping cart, etc.).
## Key Concepts
- A session is usually created when:
    - A user logs in, or
    - The server explicitly creates a session via request.getSession() in Java/Servlet.
- The server generates a unique session ID and stores data (e.g., username, preferences) in a session object.

# Explain Cookie?
A cookie is a small piece of data stored on the client side (in your browser) that websites use to remember information across multiple HTTP requests.

Cookies exist because HTTP is stateless, every request is independent. Cookies help websites “remember” things like:
- Whether you’re logged in
- Items in your shopping cart
- Your language preference
- Analytics & tracking info

# Compare Session and Cookie?
## Session vs Cookie

| Feature              | Session                                               | Cookie                                               |
|----------------------|--------------------------------------------------------|-------------------------------------------------------|
| **Storage Location** | Stored on the server                                  | Stored on the client (browser)                        |
| **Data Stored**      | Typically a session ID, and server holds full user data | Full or partial data (e.g., token, preferences)       |
| **Size Limit**       | Large (depends on server memory)                      | ~4KB per cookie                                       |
| **Security**         | More secure (data stays on server)                    | Less secure (exposed to client-side risks)            |
| **Access by JavaScript** | ❌ Not accessible from client-side               | ✅ Unless marked HttpOnly                             |
| **Lifetime**         | Until browser is closed or timeout occurs             | Can be short-lived (session cookie) or persistent with expiry date |
| **Use Case**         | Login sessions, shopping cart, secure info            | Language prefs, theme, tokens, tracking info          |
| **Performance Impact** | Loads server memory                                 | Minimal server load                                   |
| **Cross-site sharing** | ❌ Can’t be shared across domains                  | ✅ (if not SameSite restricted)                        |

## Example Use Cases
| Use Case                   | Better Option                      |
|----------------------------|------------------------------------|
| Login tracking             | Session                            |
| “Remember me” on login     | Cookie                             |
| Shopping cart (secure)     | Session                            |
| Store language preference  | Cookie                             |
| Authentication in REST API | Token in Cookie or LocalStorage    |

# Find at least TWO websites who can be logged in using your Google Account, explain in detail on how Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool:
✅

# How do we use session and cookie to keep user information across the the application?
Since HTTP is stateless, we use:
- Session (server-side) → stores user info
- Cookie (client-side) → stores session ID to “link” client and server

# What is the spring security filter?
Spring Security Filter is a Java servlet filter that sits in the filter chain and processes incoming requests before they reach your controllers.

Responsible for:
- Extracting credentials
- Validating authentication
- Enforcing access control (authorization)
- Applying security headers, CORS, CSRF, etc.

# Explain bearer token and how JWT works.
## What is a Bearer Token?
A Bearer Token is a type of access token used in HTTP Authorization headers to identify the client or user making a request.
## What is JWT?
A JWT is a compact, self-contained, digitally signed token used to securely transmit information between parties.

Used for:
- Authentication (e.g., login)
- Authorization (e.g., role-based access)
- Stateless sessions (no server storage needed)

## How JWT Works in Authentication Flow
### Login
- User sends username/password.
- Server verifies and generates JWT:
- Server returns JWT to the client.
### Client Stores JWT
Typically stored in:
- localStorage or sessionStorage (web app)
- Memory (mobile app)
- Cookie (optional, with HttpOnly and Secure flags)
### Authenticated Requests
- Client sends JWT with each request
### Server Validates JWT
- Verifies signature, expiry, and claims.
- If valid → proceeds
- If invalid/expired → returns 401 Unauthorized

# Explain how do we store sensitive user information such as password and credit card number in DB?
## Password Storage
### Use Hashing + Salt
| Technique         | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| Hashing           | One-way transformation (can’t be reversed)                                  |
| Salt              | A random string added to the password before hashing to prevent rainbow table attacks |
| Hashing Algorithm | Use bcrypt, argon2, or PBKDF2 (avoid MD5/SHA1/SHA256 for password storage)  |
## Credit Card Number Storage
### Use encryption
| Technique      | Description                                                                 |
|----------------|------------------------------------------------------------------------------|
| Encryption     | Two-way transformation — you can decrypt with a secret key                  |
| Key Management | Store encryption keys securely (e.g., AWS KMS, Vault)                        |
| AES            | Use symmetric encryption (e.g., AES-256-GCM)                                 |
| Tokenization   | Replace card number with a token; actual number stored in secure vault (used by Stripe, etc.) |

# Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?
| Component              | Role in Spring Security                                         | Key Responsibility                                                                 | Customizable? |
|------------------------|------------------------------------------------------------------|-------------------------------------------------------------------------------------|---------------|
| **UserDetailsService** | Loads user-specific data                                        | Fetch user details (username, password, roles) from DB or other source             | ✅ Yes        |
| **AuthenticationProvider** | Performs actual authentication                              | Verifies credentials using data from `UserDetailsService` or other logic           | ✅ Yes        |
| **AuthenticationManager** | Coordinates authentication process                           | Delegates to one or more `AuthenticationProvider`s                                 | ⚠️ Typically not |
| **AuthenticationFilter** | Intercepts login (or token) requests                          | Extracts credentials (e.g., from HTTP request), passes them to `AuthenticationManager` | ✅ Yes        |

# What is the disadvantage of Session? how to overcome the disadvantage?
## Disadvantages of Using Sessions
### Scalability Issues
- Sessions are stored server-side (in memory by default).
- In multi-server or cloud environments, user requests may hit different servers → session data not available unless shared.

**Solution:**
- Use distributed session storage like:
- Redis
- Memcached
- Sticky sessions (load balancer sends user to same server)
- Spring Session with Redis

### Memory Consumption
- Each logged-in user consumes memory.
- More users = more memory = potential server pressure.

**Solution:**
- Store only minimal data in session (e.g., user ID).
- Use a token-based stateless approach (e.g., JWT) if suitable.

### Session Hijacking
- If an attacker steals the session ID (via XSS, sniffing, etc.), they can impersonate the user.

**Solution:**
- Always use HTTPS
- Enable HttpOnly and Secure flags on session cookies
- Regenerate session ID after login (sessionFixation protection)
- Use SameSite=Strict or Lax to prevent CSRF

### Session Expiry / Timeout
- Users can lose their session due to inactivity, causing frustration.

**Solution:**
	•	Configure appropriate session timeout based on app usage.
	•	Show a “session is about to expire” warning.
	•	Consider using “remember me” tokens stored in secure cookies.

### Not Ideal for Stateless REST APIs
- REST APIs are supposed to be stateless.
- Using sessions breaks this principle and can cause complications with caching, scaling, and mobile clients.

**Solution:**
- For APIs, prefer JWT or OAuth2 Bearer Tokens.
- Store token in client and send via Authorization: Bearer <token>

# How to get value from application.properties in Spring security?
In Spring Security, you can easily read values from application.properties using @Value or @ConfigurationProperties.

# What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?
## configure(HttpSecurity http) — Authorization & Web Security Configuration
### Purpose:
Controls which requests are protected, what access rules apply, and what login/logout behaviors are used.
### You define:
- URL access rules (antMatchers, authenticated, permitAll)
- Form login, logout, CORS, CSRF
- Security filters
- Custom exception handling

## configure(AuthenticationManagerBuilder auth) — Authentication Configuration
### Purpose:
Defines how authentication is performed: which users, credentials, and authentication mechanisms to use.
### You define:
- In-memory users
- JDBC or custom user service (UserDetailsService)
- Password encoding (e.g., BCrypt)
- Custom AuthenticationProviders

# Reading, 泛读⼀下即可，⾃⼰觉得是重点的，可以多看两眼。
✅

# Explain best practices to securely store secrets in applications.
## Never hardcode secrets in source code
Risk: secrets get exposed via version control, logs, or public repos (e.g., GitHub leaks).

Use environment variables or external configuration instead.

## Use Environment Variables
Store secrets like DB passwords or API keys in env vars.

Safe only if your deployment environment is secure.

## Use a Secret Management System
Use tools designed for managing secrets securely.

## Use Least Privilege Access
- Ensure only specific services/users can access each secret.
- Use IAM roles, policies, or access control lists (ACLs).
- Rotate secrets regularly, and revoke old ones immediately if compromised.

## Encrypt Secrets at Rest and in Transit
- Use AES-256 or stronger encryption for files that store secrets.
- Use TLS/HTTPS for all secret transfers.
- For sensitive files (.env, config.yaml), encrypt with a secure key management system (KMS).