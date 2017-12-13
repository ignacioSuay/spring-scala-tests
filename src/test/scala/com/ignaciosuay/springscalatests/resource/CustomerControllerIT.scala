package com.ignaciosuay.springscalatests.resource

import com.ignaciosuay.springscalatests.model.Customer
import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerControllerIT extends FeatureSpec with GivenWhenThen with Matchers {

  @Autowired
  var testRestTemplate: TestRestTemplate = _
  new TestContextManager(this.getClass).prepareTestInstance(this)

  @LocalServerPort
  val randomServerPort: Integer = null

  val baseUrl = s"http://localhost:$randomServerPort"

  feature("Customer controller") {

    scenario("Find customer by id") {

      Given("a customer id")
      val id = 1

      When("a request to /customers/{id} is sent")
      val url = s"$baseUrl/customers/$id"
      val response = testRestTemplate.getForEntity(url, classOf[Customer])

      Then("we get a response with the customer in the body")
      response.getBody.getId shouldBe 1
      response.getBody.getName shouldBe "Bob"

    }

  }

}
