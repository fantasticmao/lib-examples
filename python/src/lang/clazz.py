from dataclasses import dataclass


class Complex:
    def __init__(self, real, imag):
        self.r = real
        self.i = imag


class Dog:
    kind = "canine"

    def __init__(self, name):
        self.name = name
        self.tricks = []

    def add_trick(self, trick):
        self.tricks.append(trick)


class Warehouse:
    purpose = "storage"
    region = "west"


@dataclass(frozen=True)
class Employee:
    name: str
    dept: str
    salary: int
