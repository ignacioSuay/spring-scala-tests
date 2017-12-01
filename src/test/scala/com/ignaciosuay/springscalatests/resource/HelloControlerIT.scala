package com.ignaciosuay.springscalatests.resource

import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = RANDOM_PORT)
class HelloControlerIT extends FeatureSpec with GivenWhenThen {

  @LocalServerPort
  val randomServerPort: Integer = null

  @Autowired
  var testRestTemplate: TestRestTemplate = _
  new TestContextManager(this.getClass).prepareTestInstance(this)

  val baseUrl = s"http://localhost:$randomServerPort"

  feature("Hello controller") {

    scenario("Say hello world!") {

      Given("a name")
      val name = "World"

      When("a request to /hello/{name} is sent")
      val url = s"$baseUrl/hello/$name"
      val response = testRestTemplate.getForEntity(url, classOf[String])

      Then("we get a response with the value Hello World!")
      assert(response.getBody === "Hello World!")
    }

  }

}
