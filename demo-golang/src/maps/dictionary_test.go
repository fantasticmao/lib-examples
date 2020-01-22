package maps

import "testing"

func TestSearch(t *testing.T) {
    word := "test"
    definition := "this is just a test"
    dictionary := Dictionary{word: definition}

    t.Run("known word", func(t *testing.T) {
        assertDefinition(t, dictionary, word, definition)
    })

    t.Run("unknown word", func(t *testing.T) {
        _, err := dictionary.Search("unknown")
        assertError(t, err, ErrNotFound)
    })
}

func TestAdd(t *testing.T) {
    word := "test"
    definition := "this is just a test"
    dictionary := Dictionary{}

    _ = dictionary.Add(word, definition)
    assertDefinition(t, dictionary, word, definition)

    err := dictionary.Add(word, definition)
    assertError(t, err, ErrWordExists)
}

func TestUpdate(t *testing.T) {
    word := "test"
    definition := "this is just a test"
    newDefinition := "new definition"
    dictionary := Dictionary{}

    err := dictionary.Update(word, newDefinition)
    assertError(t, err, ErrWordDoesNotExist)

    err = dictionary.Add(word, definition)
    assertNoError(t, err)
}

func TestDelete(t *testing.T) {
    word := "test"
    definition := "this is just a test"
    dictionary := Dictionary{word: definition}

    assertDefinition(t, dictionary, word, definition)

    dictionary.Delete(word)
    _, err := dictionary.Search(word)
    assertError(t, err, ErrNotFound)
}

func assertDefinition(t *testing.T, dictionary Dictionary, word string, definition string) {
    t.Helper()
    got, err := dictionary.Search(word)
    assertNoError(t, err)
    if got != definition {
        t.Errorf("got '%s' want '%s'", got, definition)
    }
}

func assertNoError(t *testing.T, err error) {
    t.Helper()
    if err != nil {
        t.Fatal("got an error but didnt want one")
    }
}

func assertError(t *testing.T, got error, want error) {
    t.Helper()
    if got != want {
        t.Fatal("expected to get an error.")
    }
}
