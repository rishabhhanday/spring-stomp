# spring-stomp

###Introduction
STOMP is a subprotocol operating on top of the lower-level WebSocket.

###Why STOMP when we already have WebSocket?

The WebSocket protocol allows you to implement bidirectional
communication between applications. It is important to know that HTTP is
used only for the initial handshake. After it happens, the HTTP connection
is upgraded to a newly opened TCP/IP connection that is used by a
WebSocket. 

The WebSocket protocol is a rather low-level protocol. It defines how a
stream of bytes is transformed into frames. A frame may contain a text or
a binary message. Because the message itself does not provide any
additional information on how to route or process it, It is difficult to
implement more complex applications without writing additional code.
Fortunately, the WebSocket specification allows using of sub-protocols
that operate on a higher, application level. One of them, supported by the
Spring Framework, is STOMP.

STOMPS defines a handful of frame types that are mapped onto
WebSockets frames, e.g., CONNECT, SUBSCRIBE, UNSUBSCRIBE, ACK, or
SEND. On one hand, these commands are very handy to manage
communication while, on the other, they allow us to implement solutions
with more sophisticated features like message acknowledgment.

###Frames
#####Client frames
1. SEND - The SEND frame sends a message to a destination in the
messaging system. It has one REQUIRED header, destination,
which indicates where to send the message. The body of the
SEND frame is the message to be sent.

2. SUBSCRIBE - The SUBSCRIBE frame is used to register to listen to a
given destination. Like the SEND frame, the SUBSCRIBE frame
requires a destination header indicating the destination to which the client wants to subscribe. Any messages received on the
subscribed destination will henceforth be delivered as MESSAGE frames from the server to the client.

3. UNSUBSCRIBE - The UNSUBSCRIBE frame is used to remove an
existing subscription. Once the subscription is removed the
STOMP connections will no longer receive messages from that
destination.

4. DISCONNECT - A client can disconnect from the server at anytime by
closing the socket

#####Server frames

1. MESSAGE - MESSAGE frames are used to convey messages from
subscriptions to the client. The MESSAGE frame will include a
destination header indicating the destination the message was
sent to. It will also contain a message-id header with a unique
identifier for that message. The subscription header will be set to
match the id header of the subscription that is receiving the
message. The frame body contains the contents of the message

2. ERROR -the server can send the client an ERROR frame and
disconnect the client for any specific reason.

### How to run and play with application (its a spring boot application)
#####Steps
1. mvn clean install 
2. mvn spring-boot:run
3. https://rishabhhanday.github.io/stomp-client-v3/ (Home made stomp test client)
4. websocket connect url -  ws://localhost:8080/stomp
5. Subsciption endpoints - /reply/teams , /reply/score , /reply/over , /reply/weather , /reply/error
6. Publish endpoint - /game/start  
7. Publish body - 
```
{
"teamName":"INDIA",
"playerName":"Rishabh Handay"
}
```

### Additional notes
NOTE:- This poc contains maximum stomp feature with basic stomp authentication
. Below are the references . 
1. https://www.notion.so/STOMP-3b52b0d3f43b4785884997caddedfd8b
1. https://stomp.github.io/stomp-specification-1.1.html#DISCONNECT
2. https://spring.io/guides/gs/messaging-stomp-websocket/
3. https://www.baeldung.com/websockets-spring
4. https://www.toptal.com/java/stomp-spring-boot-websocket
5. https://www.dariawan.com/tutorials/spring/spring-boot-websocket-stomp-tutorial/
6. https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/websocket.html
7. https://www.baeldung.com/spring-security-websockets
8. https://www.toptal.com/java/stomp-spring-boot-websocket


                                                                                                                                         
                                                                                                         
                                                                                                        
