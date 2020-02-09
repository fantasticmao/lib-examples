package main

import "fmt"

const englishHelloPrefix = "Hello, "
const chinese = "Chinese"
const chineseHelloPrefix = "你好, "
const japanese = "Japanese"
const japaneseHelloPrefix = "こんにちは"

func Hello(name string, language string) string {
	if name == "" {
		name = "World"
	}
	return greetingPrefix(language) + name
}

func greetingPrefix(language string) (prefix string) {
	switch language {
	case chinese:
		prefix = chineseHelloPrefix
	case japanese:
		prefix = japaneseHelloPrefix
	default:
		prefix = englishHelloPrefix
	}
	return
}

func main() {
	fmt.Printf(Hello("世界", "Chinese"))
}
