package com.infotech.helloworld

object Geeks {
  
  def main(args: Array[String]){
    printHello();
    println(returnInteger())
    
    
    val l = List(1,2,3)
    l.foreach(println)
    
    println(squareOfNum(5))
    
    
  }
  
  def squareOfNum(x:Int) : Int = {
    return x*x
  }
  
  def returnInteger() : Int = {
    return 10
  }
  def printHello(){
    println("Hello, Geeks !")
    
  }
}