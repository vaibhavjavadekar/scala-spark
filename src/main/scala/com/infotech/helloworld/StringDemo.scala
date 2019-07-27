package com.infotech.helloworld

object StringDemo {
  
  val str1 : String = "Hello World";
  val str2 : String = " Max";
  def main(args:Array[String]){
    println(str1.length())
    println(str1.concat(str2))
    
    println(str1 + str2)
    
  }
}