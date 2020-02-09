package concurrency

import (
	"reflect"
	"testing"
	"time"
)

func mockWebsiteChecker(url string) bool {
	if url == "http://www.google.com" {
		return false
	}
	return true
}

func slowStubWebsiteChecker(_ string) bool {
	time.Sleep(20 * time.Millisecond)
	return true
}

func TestCheckWebsites(t *testing.T) {
	urls := []string{
		"http://www.baidu.com",
		"http://www.bing.com",
		"http://www.google.com",
	}
	actualResults := CheckWebsites(mockWebsiteChecker, urls)
	expectedResults := map[string]bool{
		"http://www.baidu.com":  true,
		"http://www.bing.com":   true,
		"http://www.google.com": false,
	}

	if !reflect.DeepEqual(actualResults, expectedResults) {
		t.Fatalf("Wanted %v, got %v", expectedResults, actualResults)
	}
}

func BenchmarkCheckWebsites(b *testing.B) {
	urls := make([]string, 100)
	for i := 0; i < len(urls); i++ {
		urls[i] = "test url"
	}
	for i := 0; i < b.N; i++ {
		CheckWebsites(slowStubWebsiteChecker, urls)
	}
}
