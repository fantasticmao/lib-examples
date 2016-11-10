package priv.mm.java8;

import java.util.Optional;

/**
 * OptionalDemo
 * Created by maomao on 16-11-10.
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set: " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "none" ) );
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }
}
