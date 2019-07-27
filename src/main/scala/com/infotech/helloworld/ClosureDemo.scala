package com.infotech.helloworld

object ClosureDemo {
  
  val number = 10;
  
  val add =  (x: Int) => {
    x+number;
  }
  
  def main(args: Array[String]){
    //number = 100;
    println(add(10))
  }
}