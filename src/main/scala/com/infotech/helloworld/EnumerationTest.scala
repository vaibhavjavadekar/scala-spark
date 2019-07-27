package com.infotech.helloworld

object EnumerationTest extends Enumeration {
  
  type EnumerationTest = Value
    
    val first = Value("First")
    val second = Value("Second")
    val third = Value("Third")
    val fourth = Value("Fourth")
    
  def main (args: Array[String]){
    
    println(s"If of THird = ${EnumerationTest.third.id}")
    
    EnumerationTest.values.foreach{
      case d if (d == EnumerationTest.third) =>
        println(s"Favourite Number = $d")
      case _=> None
    }
   
  }
  
}