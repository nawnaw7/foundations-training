package exercises.function

import java.time.LocalDate

import exercises.function.FunctionExercises._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

import scala.util.Try

class FunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  /////////////////////////////////////////////////////
  // Exercise 1: String API with higher-order functions
  /////////////////////////////////////////////////////

  // replace `ignore` by `test` to enable the test
  test("secret examples") {
    assert(secret("hello world") == "***********")
    assert(secret("") == "")
  }

  // replace `ignore` by `test` to enable the test
  test("secret doesn't change the length") {
    forAll { (text: String) =>
      assert(secret(text).length == text.length)
    }
  }

  test("secret output is the same when input is reversed") {
    forAll { (text: String) =>
      assert(secret(text).reverse == secret(text))
    }
  }

  test("secret transforms all chars to * regardless of input") {
    forAll { (text: String) =>
      assert(secret(text).forall(c => c == '*'))
    }
  }

  test("isValidUsername") {
    assert(isValidUsername("john-doe"))
    assert(!isValidUsername("*john*"))
  }

  ///////////////////////
  // Exercise 2: Point
  ///////////////////////

  ignore("isPositive") {
    assert(Point(2, 4, 9).isPositive)
    assert(Point(0, 0, 0).isPositive)
    assert(!Point(0, -2, 1).isPositive)
  }

  ignore("isEven") {
    assert(Point(2, 4, 8).isEven)
    assert(Point(0, -8, -2).isEven)
    assert(!Point(3, -2, 0).isEven)
  }

  ignore("forAll") {
    assert(Point(1, 1, 1).forAll(_ == 1))
    assert(!Point(1, 2, 5).forAll(_ == 1))
  }

  ////////////////////////////
  // Exercise 3: JsonDecoder
  ////////////////////////////

  test("JsonDecoder Int") {
    assert(intDecoder.decode("1234") == 1234)
    assert(intDecoder.decode("-1") == -1)

    assert(Try(intDecoder.decode("hello")).isFailure)
    assert(Try(intDecoder.decode("1111111111111111")).isFailure)
  }

  test("JsonDecoder Int round-trip") {
    forAll { (id: Int) =>
      assert(intDecoder.decode(id.toString) == id)
    }
  }

  ignore("JsonDecoder UserId") {
    assert(userIdDecoder.decode("1234") == UserId(1234))
    assert(userIdDecoder.decode("-1") == UserId(-1))

    assert(Try(userIdDecoder.decode("hello")).isFailure)
    assert(Try(userIdDecoder.decode("1111111111111111")).isFailure)
  }

  ignore("JsonDecoder LocalDate") {
    assert(localDateDecoder.decode("\"2020-03-26\"") == LocalDate.of(2020, 3, 26))
    assert(Try(localDateDecoder.decode("2020-03-26")).isFailure)
    assert(Try(localDateDecoder.decode("hello")).isFailure)
  }

  ignore("JsonDecoder weirdLocalDateDecoder") {
    val date = LocalDate.of(2020, 3, 26)
    assert(weirdLocalDateDecoder.decode("\"2020-03-26\"") == date)
    assert(weirdLocalDateDecoder.decode("18347") == date)
    assert(Try(weirdLocalDateDecoder.decode("hello")).isFailure)
  }

  ///////////////////////////////
  // Exercise 4: Data processing
  ///////////////////////////////

  ignore("sum") {
    assert(sum(List(1, 5, 2)) == 8)
    assert(sum(Nil) == 0)
  }

  ignore("sum is consistent with List sum") {
    forAll { (numbers: List[Int]) =>
      assert(sum(numbers) == numbers.sum)
    }
  }

  ignore("min") {
    assert(min(List(2, 5, 1, 8)) == Some(1))
    assert(min(Nil) == None)
  }

  ignore("wordCount") {
    assert(wordCount(List("Hi", "Hello", "Hi")) == Map("Hi" -> 2, "Hello" -> 1))
    assert(wordCount(Nil) == Map.empty)
  }

}
