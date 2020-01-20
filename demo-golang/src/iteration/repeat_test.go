package iteration

import (
	"strings"
	"testing"
)

func TestRepeat(t *testing.T) {
	repeat := Repeat("a", 5)
	expected := "aaaaa"

	if repeat != expected {
		t.Errorf("expected '%q' but got '%q'", expected, repeat)
	}
}

func BenchmarkRepeat(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Repeat("a", 5)
	}
}

func ExampleRepeat() {
	Repeat("a", 5)
}

func TestString(t *testing.T) {
	index := strings.Index("hello world", "wo")
	expected := 6
	if index != expected {
		t.Errorf("expected '%d' but got '%d'", expected, index)
	}
}
