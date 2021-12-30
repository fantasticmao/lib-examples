package lang

import (
	"fmt"
	"testing"
)

func TestDefer(t *testing.T) {
	defer fmt.Println("a")
	defer fmt.Println("b")
	defer fmt.Println("c")
}
