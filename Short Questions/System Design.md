# My Presentation Slides
https://docs.google.com/presentation/d/1VrrpvAtVWW-gzTwMnvTAyD3RZRUseSSE3gcWauoZV2E/edit?usp=sharing
# Summary of My Design
I designed a scalable Live Comment System to support real-time interactions for livestream video platforms. The system enables users to:
- Post comments during live streams
- View all historical comments when joining
- Receive new comments in real-time with low latency
# Architecture Overview
### Client → API Gateway → Rate-Limit → Moderation → Kafka → Comment Service → Cassandra
Handles comment creation and historical fetching

### Client → API Gateway → Rate-Limit → Moderation → Kafka → Comment Service → Kafka → Realtime Comment Service → SSE → Client
Handles real-time comment delivery

# Scale Requirements
- 100M concurrent SSE connections
- 30K new comments per second across the platform

# Things That Can Be Improved & Concerns Needs To Be Addressed
I am using **Cassandra** as DB, however, my schema design is traditional RDBMS, which has a **Foreign Key**.

The schema for **Cassandra** should be:
| Field      | Type     | Description                                                   |
|------------|----------|---------------------------------------------------------------|
| video_id   | String   | Partition Key                                                 |
| bucket     | Int      | Time bucket (minute) to prevent hot partitions |
| timestamp  | Datetime | Clustering Key                                                |
| comment_id | String   | Optional, secondary sort or dedup key                         |
| user_id    | String   | Who posted the comment                                        |
| content    | Text     | The actual comment                                            |

Furthermore, in the system, I need a Zookeeper to assign the SSE connections between 100M clients and Realtime Comment Services. I did not draw Zookeeper components in the diagram.

# What I Learned from Reviewing Others’ Designs and Presentations
I learned that it is not a good idea to mention **Cache** in system design interview. Because in many cases, **Cache** will not improve the system performance in a way we thought it would.