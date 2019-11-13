package com.atguigu.scala.test01

object QuickSort1 {

    def main(args: Array[String]): Unit = {
        val list = List[Int](3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18)
        val listSorted = quickSort(list)
        println(listSorted)
    }

    def quickSort(list: List[Int]): List[Int] = list match {
        //当时空集合时，返回
        case Nil => Nil
        case List() => List()
        case head :: tail =>
            val (left, right) = tail.partition(_ < head)
            quickSort(left) ::: head :: quickSort(right)
    }
}
