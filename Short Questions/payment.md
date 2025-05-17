![Global Payment System](./payment.assets/Global Payment System-1747267539098-3.png)

## Core functions and objectives of the payment system

- Design a payment processing system similar to Stripe that can handle various payment methods for online transactions
- The system needs to be secure, reliable, highly available, and able to process transactions globally
- Support merchant integration, allowing third-party applications to call our payment services via API

## Key Functional Requirements

- **Payment Processing**: Accept and process credit cards, debit cards, digital wallets, and other payment methods
- **Third-party Developers Integration**: Provide APIs for Third-party Developers to easily integrate payment functions
- **Payment Verification**: Verify payment information and fraud prevention systems
- **Payment Status Tracking**: Real-time tracking of payment status (pending, successful, failed)
- **Refund Processing**: Support full or partial refunds
- **Reports and Analytics**: Provide transaction history and financial reports for merchants
- **Multi-currency Support**: Process transactions in different currencies
- **Management Console**: Interface for merchants to manage transactions and configurations

## Non-functional Requirements

### Scalability

- System needs to handle peak traffic (such as during shopping festivals)
- Estimated daily transaction volume: Assume processing 10 million transactions per day
- Average QPS: Approximately 115 QPS, with peak potentially 10 times the average
- System needs to scale horizontally to accommodate growth

### Availability

- Requires 99.99% uptime (less than 52 minutes of downtime per year)
- System must be able to tolerate partial failures
- High availability is essential because payment is a critical business function

### Data Modeling

- **Structured Data**: User information, transaction records, payment methods, etc.
- **Semi-structured Data**: API request/response logs, etc.
- **Unstructured Data**: Verification documents uploaded by users, etc.



## Rough Estimation

- Storage Requirements:

- Assume each transaction record is about 1KB
- 10 million transactions per day = 10GB/day
- Approximately 3.65TB of raw data per year
- Considering backups and redundancy, actual need is about 10-15TB/year

- Bandwidth Requirements:

- Average transaction size approximately 2KB (request+response)
- 115 QPS * 2KB = 230KB/s average
- Peak approximately 2.3MB/s

- Server Estimation:

- Payment service: Each server handles about 1000 QPS
- Peak 1150 QPS requires about 2-3 servers (considering redundancy)
- Total of about 15-20 servers needed (including all services)



## High-level Design

![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747258504179-6875e852-c68c-470d-9db7-e1512b97b817.png)

## Database

![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747255843552-28df71c0-671f-40d4-939f-2897361c944d.png)

**Users Table**  

```sql
CREATE TABLE users (
  user_id VARCHAR(36) PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  profile_data JSONB NOT NULL,
  kyc_status ENUM('verified', 'pending', 'rejected') NOT NULL DEFAULT 'pending',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

- `user_id`: Unique user identifier (UUID format), primary key
- `email`: User's email address, unique index
- `password_hash`: Encrypted password hash
- `profile_data`: User profile information including name, stored in JSON format
- `kyc_status`: Know Your Customer verification status (verified, pending, rejected)
- `created_at`: User creation timestamp



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747255866318-30b4c34e-52f7-4b88-896d-9828ba5788cf.png)

**Accounts Table**  

```sql
CREATE TABLE accounts (
    account_id VARCHAR(10) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    currency_code CHAR(3) NOT NULL,
    balance DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    locked_balance DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

- `account_id`: Unique account identifier, primary key
- `user_id`: Associated user ID, foreign key
- `currency_code`: Currency code (e.g., USD, EUR, GBP, JPY)
- `balance`: Available balance
- `locked_balance`: Locked funds (being processed or frozen)
- `updated_at`: Last update timestamp



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747255924378-4a38822c-11e1-4791-b3d3-2d980e0c1500.png)

**Payments Table**  

```sql
CREATE TABLE payments (
    payment_request_id VARCHAR(10) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    merchant_id VARCHAR(36) NOT NULL,
    amount DECIMAL(20,2) NOT NULL,
    currency_code CHAR(3) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    request_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'success', 'failed') NOT NULL DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

- `payment_request_id`: Unique payment request identifier, primary key
- `user_id`: User initiating the payment, foreign key
- `merchant_id`: Merchant ID receiving the payment
- `amount`: Payment amount
- `currency_code`: Payment currency code
- `payment_method`: Payment method used (e.g., PayPal, Credit Card, Debit Card)
- `request_time`: Request initiation timestamp
- `status`: Payment status (pending, success, failed)



 **Currency Exchange Rates Table**  

```sql
CREATE TABLE currency_exchange_rates (
    exchange_rate_id VARCHAR(10) PRIMARY KEY,
    source_currency CHAR(3) NOT NULL,
    target_currency CHAR(3) NOT NULL,
    rate DECIMAL(10,6) NOT NULL,
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

- `exchange_rate_id`: Unique exchange rate record identifier, primary key
- `source_currency`: Source currency code
- `target_currency`: Target currency code
- `rate`: Exchange rate value
- `last_updated`: Last update timestamp



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747256168189-2d0fc4c2-2519-4fc1-af73-8555cc0ab3e4.png)

**Transaction Table**  

```sql
CREATE TABLE transactions (
    transaction_id VARCHAR(10) PRIMARY KEY,
    account_id VARCHAR(10) NOT NULL,
    amount DECIMAL(20,2) NOT NULL,
    currency_code CHAR(3) NOT NULL,
    transaction_type ENUM('purchase', 'refund', 'deposit', 'withdrawal') NOT NULL,
    status ENUM('pending', 'completed', 'failed') NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)<delete>
);
```

- `transaction_id`: Unique transaction identifier, primary key
- `account_id`: Associated account ID, foreign key
- `amount`: Transaction amount
- `currency_code`: Transaction currency code
- `transaction_type`: Type of transaction (purchase, refund, deposit, withdrawal)
- `status`: Transaction status (pending, completed, failed)
- `created_at`: Transaction creation timestamp



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747256056766-848fd3c9-5754-4243-ab21-d787f63d07c8.png)

**Fraud Detection Table**  

```sql
CREATE TABLE fraud_detection (
    fraud_log_id VARCHAR(10) PRIMARY KEY,
    transaction_id VARCHAR(10),
    user_id VARCHAR(36) NOT NULL,
    suspicious_activity VARCHAR(255) NOT NULL,
    fraud_score INT NOT NULL,
    action_taken VARCHAR(100) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

- `fraud_log_id`: Unique fraud log identifier, primary key
- `transaction_id`: Associated transaction ID (can be null if detection occurs before transaction), foreign key
- `user_id`: Associated user ID, foreign key
- `suspicious_activity`: Description of suspicious activity (e.g., Unusual Location, Multiple Failed Logins)
- `fraud_score`: Fraud risk score (0-100, higher score indicates higher risk)
- `action_taken`: Action taken (e.g., Payment Held, Flagged for Review, Payment Approved)
- `timestamp`: Record creation timestamp



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747256142545-a64b4d17-ed26-4939-8a0c-072dc50d8619.png)

**Notification Table**  

```sql
CREATE TABLE notifications (
    notification_id VARCHAR(10) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    notification_type ENUM('Email', 'SMS', 'Push', 'In-App') NOT NULL,
    message_content TEXT NOT NULL,
    delivery_status ENUM('Pending', 'Delivered', 'Failed') NOT NULL DEFAULT 'Pending',
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

- `notification_id`: Unique notification identifier, primary key
- `user_id`: User receiving the notification, foreign key
- `notification_type`: Type of notification (Email, SMS, Push, In-App)
- `message_content`: Notification content
- `delivery_status`: Delivery status (Pending, Delivered, Failed)
- `timestamp`: Notification creation timestamp





![img](https://cdn.nlark.com/yuque/0/2025/jpeg/34899122/1747255186022-db74f0ba-fc45-4c4c-9d79-454904ad018a.jpeg)



## Payment API Design

```plain
1. Payment Processing
    POST /v1/payments
    Headers: 
       Content-Type: application/json
       Idempotency-Key: 12345-abcde-67890-fghij
    Body:
    {
       "amount": 1000,
       "currency": "USD",
       "description": "Payment for order #12345"
    }

2. Duplicate Payment Prevention (using idempotency)
    POST /v1/payments
    Headers: 
       Content-Type: application/json
       Idempotency-Key: 12345-abcde-67890-fghij
    Body:
    {
       "amount": 1000,
       "currency": "USD",
       "description": "Payment for order #12345"
    }

3. Payment Query
	GET /v1/payments/{payment_id}
    Headers:
       Content-Type: application/json
    
   
4. Refund
    POST /v1/payments/{payment_id}/refunds
    Headers:
       Content-Type: application/json
    Body:
    {
       "amount": 1000,
       "reason": "Customer refund request"
    }
```



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747256658590-79d93c27-5fc9-4125-b2f7-935e79b1fe94.png)



![img](https://cdn.nlark.com/yuque/0/2025/png/34899122/1747258701334-bf64b383-fe98-4df2-b1a0-e8e8a2d2ae7f.png)





## Summary of the Current Design

The current payment system design resembles Stripe's functionality, aiming to provide secure, reliable, and highly available online transaction processing globally. The system is structured as a microservices architecture with three primary layers:

1. **Client Layer**: Web applications, mobile applications, and API interfaces
2. **Service Layer**: Core microservices handling business logic
3. **Data Layer**: Relational and NoSQL databases, plus caching systems

Key metrics include processing 10 million transactions daily (average 115 QPS, peak 1,150 QPS) with 99.99% uptime. The design features a comprehensive database schema and inter-service communication through Kafka message queues.

## Areas for Improvement

### SQL Database Schema Issues

- **Unnecessary Foreign Keys**: Several tables contain potentially problematic foreign key relationships, especially the `transaction_id` reference in the Fraud Detection table that may not be necessary and could create tight coupling
- **Strong Coupling Between Tables**: The tight coupling between Users and Accounts tables via foreign keys could make system evolution difficult, especially for international expansion or multi-currency support.



### Synchronous/Asynchronous Communication Understanding

- **Unclear Differentiation**: The design heavily utilizes Kafka for asynchronous communication but doesn't clearly articulate when to use synchronous vs. asynchronous patterns.
- **Improvement**:
  - Use synchronous communication for operations requiring strong consistency (account deduction)
  - Use asynchronous communication for operations that can tolerate eventual consistency (notifications, analytics)
  - Document the consistency guarantees for each service interaction
- **Compensation Transactions**: The design lacks clear compensation mechanisms for handling failures in distributed transactions, which are critical for payment systems.

### Inter-Service Communication Design

- **Over-reliance on Kafka**: While Kafka provides excellent message queuing, the design uses it for nearly all communication without considering alternatives like direct API calls for time-sensitive operations.
- **Circuit Breaker Implementation**: No mention of how the system handles service failures with patterns like circuit breakers to prevent cascading failures.

### Scaling Approach Deficiencies

- Horizontal Scaling Strategy: While the design mentions horizontal scaling, it lacks specific strategies for:
  - Database sharding methodology
  - Read replicas deployment strategy
  - Cross-datacenter replication
- **Burst Traffic Handling**: The design mentions 10x peak traffic but doesn't detail how auto-scaling, rate limiting, and graceful degradation would be implemented.
- **Regional Deployment**: For a global payment system, the design should include multi-region deployment strategies to reduce latency and improve reliability.

### API Design Limitation

- REST API Focus: The design only covers REST APIs, missing opportunities for:
  - **GraphQL Benefits**: Would allow merchants to request exactly the data they need, reducing payload sizes and network traffic
  - **WebSocket Applications**: Real-time payment status updates, transaction monitoring, and merchant dashboards would benefit from WebSocket connections
- **API Versioning Strategy**: No clear approach for maintaining backward compatibility while evolving the API.

## Implementation Concerns to Address

1. **Data Consistency**: How does the system maintain consistency across distributed transactions spanning multiple services?
2. **Security Measures**:
   - The design should elaborate on encryption methods for data at rest and in transit
   - Token management and secure key rotation for merchant API keys
   - PCI DSS compliance implementation details
3. **Observability Infrastructure**:
   - Distributed tracing to debug cross-service transactions
   - Centralized logging architecture
   - Alerting and monitoring systems
4. **Disaster Recovery**:
   - RTO (Recovery Time Objective) and RPO (Recovery Point Objective) targets
   - Backup strategies for critical financial data
   - Failover procedures between regions

## Lessons Learned from Other Designs

1. **Service Isolation**: More mature designs implement bulkheads and strict service boundaries to prevent cascade failures.
2. **Event Sourcing Pattern**: Some payment platforms use event sourcing to maintain a complete audit trail and enable system reconstruction from events.
3. **Cache Strategies**: More sophisticated designs implement multi-level caching with clear invalidation strategies.

## Next Steps

1. Develop a clear communication pattern guide for synchronous vs. asynchronous operations
2. Implement a service mesh for improved service discovery and communication
3. Expand API capabilities to include GraphQL and WebSocket support
4. Create detailed disaster recovery procedures with defined SLAs
