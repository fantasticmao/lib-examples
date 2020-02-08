package reflection

import "reflect"

func Walk(x interface{}, fn func(input string)) {
	val := getValue(x) // 若 x 是指针类型，则使用 Elem() 提取底层值

	switch val.Kind() {
	case reflect.String:
		fn(val.String())
	case reflect.Struct: // 若 x 是 struct 类型
		for i := 0; i < val.NumField(); i++ {
			Walk(val.Field(i).Interface(), fn)
		}
	case reflect.Slice, reflect.Array: // 若 x 是 slice 或 array 类型
		for i := 0; i < val.Len(); i++ {
			Walk(val.Index(i).Interface(), fn)
		}
	case reflect.Map: // 若 x 是 map 类型
		for _, key := range val.MapKeys() {
			Walk(val.MapIndex(key).Interface(), fn)
		}
	}
}

func getValue(x interface{}) reflect.Value {
	val := reflect.ValueOf(x)
	if val.Kind() == reflect.Ptr {
		val = val.Elem()
	}
	return val
}
