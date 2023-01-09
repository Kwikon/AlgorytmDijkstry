package com.example.algorytmdijksty

data class Path(val nodes: List<Int>,val cost: Int)

abstract class Algorithm(val graph: Graph) {
    abstract fun pathfind(s: Int, e:Int) : Path?
}