# spring-boot-examples-jms

## JMS ActiveMQ examples

1. Start AvtiveMQ

   1. Local binary

      1. Download from [here](https://activemq.apache.org/components/classic/download/) and start with `bin/activemq start`

   2. Via Docker
      1. Or download and run a Docker image with `docker run -p 61616:61616 -p 8161:8161 symptoma/activemq:5.15.13`

2. Open the ActiveMQ admin console at [http://localhost:8161/admin](http://localhost:8161/admin). Create a queue, for example `testQueue`.

3. Start the application. Send a test message via the admin console ([http://localhost:8161/admin/send.jsp?JMSDestination=testQueue&JMSDestinationType=queue](http://localhost:8161/admin/send.jsp?JMSDestination=testQueue&JMSDestinationType=queue)).

4. Output:
   ```
   c.g.k.s.b.examples.jms.MessageReceiver   : Received message ActiveMQTextMessage {commandId = 6, responseRequired = false, messageId = ID:xxx, originalDestination = null, originalTransactionId = null, producerId = ID:xxx, destination = queue://testQueue, transactionId = null, expiration = 0, timestamp = 000, arrival = 0, brokerInTime = 000, brokerOutTime = 000, correlationId = , replyTo = null, persistent = false, type = , priority = 0, groupID = null, groupSequence = 0, targetConsumerId = null, compressed = false, userID = null, content = org.apache.activemq.util.ByteSequence@37e1ee79, marshalledProperties = null, dataStructure = null, redeliveryCounter = 0, size = 0, properties = null, readOnlyProperties = true, readOnlyBody = true, droppable = false, jmsXGroupFirstForConsumer = false, text = test2}
   ```

