package com.ignaciosuay.springscalatests.resource

import com.ignaciosuay.springscalatests.model.Customer
import com.ignaciosuay.springscalatests.service.CustomerService
import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.{FunSuite, GivenWhenThen}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.hamcrest.Matchers.is


@RunWith(classOf[SpringRunner]) //1
@WebMvcTest(Array(classOf[CustomerController]))
class CustomerControllerMvcTest extends FunSuite with GivenWhenThen {

  @Autowired //2
  var mvc: MockMvc = _

  @MockBean //3
  val customerService: CustomerService = null

  new TestContextManager(this.getClass).prepareTestInstance(this) //4

  test("test find a customer") {

    Given("a customer id")
    val id = 1l
    val expectedCustomer = new Customer(id, "Bob")
    when(customerService.findCustomer(id)).thenReturn(expectedCustomer)

    When("a request to /customers/{id} is sent")
    val result = mvc.perform(get(s"/customers/$id").contentType("application/json"))

    Then("expect a customer")
    result
      .andExpect(status.isOk)
      .andExpect(jsonPath("$.id", is(1)))
      .andExpect(jsonPath("$.name", is(expectedCustomer.getName)))
  }

}
