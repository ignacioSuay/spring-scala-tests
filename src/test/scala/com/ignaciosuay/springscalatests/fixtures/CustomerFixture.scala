package com.ignaciosuay.springscalatests.fixtures

import com.ignaciosuay.springscalatests.model.Customer

object CustomerFixture {

  def aCustomer(id: Long = 1, name: String = "Bob") = new Customer(id, name)

}
