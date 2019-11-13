package com.atguigu.scala.test01

object TestMatchGuard {
    def main(args: Array[String]): Unit = {
        def ads(x : Int) = x match {
            case i:Int if i >= 0 => i
            case j:Int if j < 0 => -j
            case _ =>"default"
        }
        println(ads(-5))
    }
}
