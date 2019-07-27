package com.infotech

class Rectangle(val width:Double, val height:Double) extends Polygon with Shape{
  
  override def area : Double = width * height;
  def color : String = "Green";
}