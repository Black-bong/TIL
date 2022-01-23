package ex;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Ex11_11 {
	public static void main(String[] args) {
		Set<Person> set = new HashSet<>();

		set.add(new Person("David", 10));
		set.add(new Person("David", 10));

		System.out.println(set);
	}
}

class Person {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;	
	}

	public String toString() {
		return name + ":" + age;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person p = (Person) obj;
			return this.name.equals(p.name) && this.age == p.age;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}
}