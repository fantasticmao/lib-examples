package cn.fantasticmao.demo.java.google.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.demo.GreeterGrpc;
import io.grpc.demo.HelloReply;
import io.grpc.demo.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * HelloWorldServer
 *
 * @author maodh
 * @since 20/06/2018
 */
public class HelloWorldServer {

    private Server server;

    private void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
            .addService(new GreeterImpl())
            .build()
            .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.out.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("*** receive a new message");

            HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + req.getName())
                .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sayHelloAgain(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("*** receive a new message");

            HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello again " + req.getName())
                .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloWorldServer server = new HelloWorldServer();
        server.start(50051);
        server.blockUntilShutdown();
    }
}
