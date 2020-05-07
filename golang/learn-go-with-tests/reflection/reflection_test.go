package reflection

import (
	"reflect"
	"testing"
)

type Person struct {
	Name    string
	Profile Profile
}

type Profile struct {
	Age  int8
	City string
}

func TestWalk(t *testing.T) {
	cases := []struct {
		Name          string
		Input         interface{}
		ExpectedCalls []string
	}{
		{
			Name: "Struct with one string field",
			Input: struct {
				Name string
			}{Name: "小明"},
			ExpectedCalls: []string{"小明"},
		}, {
			Name: "Struct with two string fields",
			Input: struct {
				Name string
				City string
			}{Name: "小明", City: "杭州"},
			ExpectedCalls: []string{"小明", "杭州"},
		}, {
			Name: "Struct with non string field",
			Input: struct {
				Name string
				Age  int8
			}{Name: "小明", Age: 18},
			ExpectedCalls: []string{"小明"},
		}, {
			Name:          "Nested fields",
			Input:         Person{Name: "小明", Profile: Profile{Age: 18, City: "杭州"}},
			ExpectedCalls: []string{"小明", "杭州"},
		}, {
			Name:          "Pointers to things",
			Input:         &Person{Name: "小明", Profile: Profile{Age: 18, City: "杭州"}},
			ExpectedCalls: []string{"小明", "杭州"},
		}, {
			Name:          "Slices",
			Input:         []Profile{{Age: 18, City: "杭州"}, {Age: 20, City: "上海"}},
			ExpectedCalls: []string{"杭州", "上海"},
		}, {
			Name:          "Array",
			Input:         [2]Profile{{Age: 18, City: "杭州"}, {Age: 20, City: "上海"}},
			ExpectedCalls: []string{"杭州", "上海"},
		},
	}

	for _, test := range cases {
		t.Run(test.Name, func(t *testing.T) {
			t.Helper()
			var got []string
			Walk(test.Input, func(input string) {
				got = append(got, input)
			})
			if !reflect.DeepEqual(got, test.ExpectedCalls) {
				t.Errorf("got %v, want %v", got, test.ExpectedCalls)
			}
		})
	}

	t.Run("Map", func(t *testing.T) {
		t.Helper()
		input := map[string]string{"One": "1", "Two": "2"}

		var got []string
		Walk(input, func(input string) {
			got = append(got, input)
		})

		assertContains(t, got, "1")
		assertContains(t, got, "2")
	})
}

func assertContains(t *testing.T, values []string, val string) {
	contains := false
	for _, x := range values {
		if x == val {
			contains = true
		}
	}

	if !contains {
		t.Errorf("expected %+v to contain %q but it didn't", values, val)
	}
}
