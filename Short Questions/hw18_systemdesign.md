### Summary of Payment System Design (Stripe-like)

The design outlines a **robust and modular payment system** that integrates both **functional** and **non-functional requirements**, focusing on **security**, **fault tolerance**, **data consistency**, and **scalability**.

---
####  Functional Flow

* **Client** initiates checkout → `Payment Page` triggers a flow into `Payment Service`.
* `Payment Service` coordinates:

  * **Ledger** for persistent financial records
  * **Payment Executor** for logic and orchestration
  * **Wallet** for real-time balance
  * **PSP & Card Schemes** via `Webhook`, `Token`, and `Nonce` for secure external interaction
* **Load Balancer + DB Cluster** ensures horizontal scalability.
* **RabbitMQ retry + DLQ** supports message recovery/failure handling.

#### Non-Functional Attributes

* **Security:** Credit card info isn't stored, PSP handles it. Communication involves nonce, token, secret-based webhook.
* **Consistency & Reconciliation:** Ledger + Wallet + periodic reconciliation (via settlement file).
* **Fault Tolerance:** Message queue retry + dead-letter for failures.

#### Back-of-the-envelope Capacity

* Designed for **1 million transactions/day (≈10 TPS)**.

---

### Concerns and Areas for Improvement

##### **Webhook Security & Replay Protection**

###### Situation:
PSP shows payment success to user, but our system marks it as failed

###### Problem:

In many payment systems, the **user is shown a "success" page by the PSP**, but the backend relies on a **webhook** from the PSP to mark the payment as complete.

If the webhook fails or is delayed:

* The user sees a success screen.
* Our system might mark the payment as **failed**.
* This causes a **data inconsistency**, possible user complaints, and financial mismatches.


#### RabbitMQ — use a database table for retry and dead-letter logic
The database table solution is lightweight, easier to debug, and ideal for simple or small systems. RabbitMQ excels in complex, 
high-volume, low-latency systems but comes with more operational overhead.

---
### Lesson from Others’ Designs and Presentations
Don’t rely solely on webhook for critical payment status updates. Also don't rely on PSP or
3rd party provider as they may not reliable. 

For real-time systems need circuit breakers. A downstream PSP outage may lead to a full system failure. 
Circut breakers with fallback behaviors can avoid cascading failures. 