package com.ignaciosuay.springscalatests.resource

import com.ignaciosuay.springscalatests.service.HelloService
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


@RunWith(classOf[SpringRunner])
@WebMvcTest(Array(classOf[HelloControler]))
class HelloControlerMvcTest extends FunSuite with GivenWhenThen {

  @Autowired
  var mvc: MockMvc = _

  @MockBean
  val helloService: HelloService = null

  new TestContextManager(this.getClass).prepareTestInstance(this)

  test("test say hello") {

    Given("a name: 'World'")
    val name = "World"
    when(helloService.sayHi(name)).thenReturn("Hello World!")

    When("a request to /hello/{name} is sent")
    val result = mvc.perform(get(s"/hello/$name").contentType("application/json"))

    Then("expect hello world!")
    result
      .andExpect(status.isOk)
      .andExpect(content().string("Hello World!"))
  }

}
