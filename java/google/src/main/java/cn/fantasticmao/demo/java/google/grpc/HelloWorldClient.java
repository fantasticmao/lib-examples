package cn.fantasticmao.demo.java.google.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.demo.GreeterGrpc;
import io.grpc.demo.HelloReply;
import io.grpc.demo.HelloRequest;

import java.util.concurrent.TimeUnit;

/**
 * HelloWorldClient
 *
 * @author maodh
 * @since 20/06/2018
 */
public class HelloWorldClient {
    private ManagedChannel managedChannel;
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.managedChannel = channel;
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        System.out.println("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder()
            .setName(name)
            .build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Greeting: " + response.getMessage());

        try {
            response = blockingStub.sayHelloAgain(request);
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Greeting: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
            client.greet("MaoMao");
        } finally {
            client.shutdown();
        }
    }
}
