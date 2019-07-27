package com.infotech.helloworld

object ListDemo {
  
  val list : List[Int] = List(1,2,3,4,5,6,7);
  
  val listStr : List[String] = List("vj", "mj");
  
  def main(args:Array[String]){
    
    val newList = 0 :: list;
    
    println(0 :: list)
    println(0 :: listStr)
    println(newList)
    println(1 :: 5 :: 7 :: Nil)
    println(listStr.head); println(listStr.tail)
    println(listStr.isEmpty +" :::  "+ listStr.isTraversableAgain +" REVERSE = "+ listStr.reverse )
    println(List.fill(5)(2))

    val list1 : List[Any] = List("Text", 12, 12.12);
    
    
    println(list.find(_  > 16).getOrElse(0))
    
    val list2 = list.map(i => i*2);
    
    
    println(list.map(i => i*2))
    println(list2.filter(x => (x > 10)))
    
    println(list.reduceRight((x,y) => (x - y)))
    
    println(listStr.foldRight("10")(_+_))
    
    println(list.scanLeft(10)(_+_))
    
  }
}