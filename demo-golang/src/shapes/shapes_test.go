package shapes

import (
	"testing"
)

func TestShapes(t *testing.T) {
	// 定义 anonymous struct slice 变量
	aresTests := []struct {
		name  string
		shape Shape
		want  float64
	}{
		{name: "Rectangle", shape: Rectangle{Width: 12.0, Height: 6.0}, want: 72.0},
		{name: "Circle", shape: Circle{Radius: 10.0}, want: 314.1592653589793},
		{name: "Triangle", shape: Triangle{Base: 12.0, Height: 6.0}, want: 36.0},
	}

	// 表格测试驱动（table driven tests）适用场景：测试一个接口的不同实现、测试不同参数的函数调用
	for _, tt := range aresTests {
		t.Run(tt.name, func(t *testing.T) {
			got := tt.shape.Area()
			if got != tt.want {
				t.Errorf("%#v got %.2f want %.2f", tt.shape, got, tt.want)
			}
		})
	}

	assertPerimeterCorrectMessage := func(t *testing.T, shape Shape, want float64) {
		t.Helper()
		got := shape.Perimeter()
		if got != want {
			t.Errorf("got %.2f want %.2f", got, want)
		}
	}

	t.Run("calculate rectangle perimeter", func(t *testing.T) {
		rectangle := Rectangle{10.0, 10.0}
		want := 40.0
		assertPerimeterCorrectMessage(t, rectangle, want)
	})

	t.Run("calculate circle perimeter", func(t *testing.T) {
		circle := Circle{10.0}
		want := 62.83185307179586
		assertPerimeterCorrectMessage(t, circle, want)
	})
}
