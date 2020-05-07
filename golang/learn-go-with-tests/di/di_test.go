package main

import (
	"bytes"
	"testing"
)

func TestGreet(t *testing.T) {
	buffer := &bytes.Buffer{}
	name := "Test"
	_, _ = Greet(buffer, name)

	got := buffer.String()
	want := "Hello " + name
	if got != want {
		t.Errorf("got '%s' want '%s'", got, want)
	}
}
