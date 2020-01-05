# Custom annotations example project

This project implements a simple java socket server that you can connect through telnet or netcat like this:

    telnet localhost 1111
    nc localhost 1111

It also illustrates a how to implement a custom annotation by creating a "Message Filter".

This message filter makes annotated be executed when a certain message arrives.

If I have this method:

    @MessageFilter("hello")
    public String callWhenHello() {
        return "world"
    }

Then, when someone telnet my server and write "hello", my program will automatically respond with "world".

Reflection code that make this happen is inside main method.