package functionalKotlin.delegate.customDelegate

import java.util.*


interface Person {
    fun printName()
}

interface People {
    fun peopleName()
}

class PeopleImpl(val group:String): People {
    override fun peopleName() {
        print("people 그룹 : $group")
    }

}

class PersonImpl(val name:String):Person {
    override fun printName() {
        println(name)
    }
}

class User(val person:Person):Person by person {

    //이 코드를 주석 하면, person에 있는 printName을 Person 인터페이스에 위임하여, printName을 실행했을 경우  인스턴스가 갖고 있느 메소드를 실행할 것이다.
    //-----------------------------------------------------------------------
    override fun printName() {
        println("Printing Name:")
        person.printName()
    }
    //----------------------------------------------------------------------

    // 위와 같이 위임을 했는데도 오버라이드를 할 경우엔 현재 User에서 오버라이드한 메소드를 사용을 할 것이다.
}

//person 인스턴스가 by를 사용하지 않았다면 밑에와 같이 짜야 겠다.
class User1(val person:Person):Person{
    override fun printName() {
        println("Printing Name:")
        person.printName()
    }
}

//person이 생성자의 속성값으로 들어오는 것이 아니라, 인자값으로 들어오더라도 by뒤에 사용할 수 있다.
class User2(person:Person): Person by person

// person 자리에 object 표현식을 사용해서 만들기
class User3: Person by object:Person{
    override fun printName() {
        println("object statement로 위임한 코드")
    }

}


//그냥 val person을 전역으로 생성한 후에, by에 위임하는 방법

val globalPerson = object:Person{
    override fun printName() {
        println("global 로 이전에 만들어놓은 person을 넣을 경우.")
    }

}

class User4: Person by globalPerson


//인자 값을 받아 object 표현식으로 객체를 생성하는 방법
// 인자값을 받아 인자값을 사용하여 인터페이스르 구현해서 그 클래스에 위임하는 클래스를 만들 수 있다.
class User5(v :String): Person by object:Person {
    override fun printName() {
        println("인자값을 받아 오브젝트 표현식으로 객체를 새로 만들기 받은 파라미터 : $v")
    }
}


//인터페이스를 여러개 상속받는다면 by를 여러개 한다.
class User6(person:Person, people:People): Person by person,People by people

fun main(args: Array<String>) {
    val person = PersonImpl("Mario Arias")

    person.printName()
    println()
    val user = User(person)
    user.printName()


//person이 생성자의 속성값으로 들어오는 것이 아니라, 인자값으로 들어오더라도 by뒤에 사용할 수 있다.
    val person2 = PersonImpl("put as parameter")
    val user2 = User2(person2)
    user2.printName()


    //object 표현식을 위임한 코드
    val user3 = User3()
    user3.printName()

    //인자값이 아닌 이전에 선언한 person을 바로 by로 위임하는 방법
    val user4 = User4()
    user4.printName()

    //인자 값을 받아 object 표현식으로 객체를 생성하는 방법
    val user5 = User5("이것은 새로운 파라미터")
    user5.printName()

    //여러개의 인터페이스를 상속한 클래스의 인터페이스를 모두 위임한다면 따로 by로 위임해야한다.
    val people = PeopleImpl("이태원 코딩도장 사람들")
    val person3 = PersonImpl("오야지")
    val user6 = User6(person3,people);
    user6.printName()
    user6.peopleName()


}