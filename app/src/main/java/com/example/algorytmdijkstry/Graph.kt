package com.example.algorytmdijksty

import kotlin.random.Random

data class Edge(val first: Int, val second: Int, val cost: Int)
data class Vertex(val node: Int, val cost: Int)

class Graph(val nCount: Int, val eCount: Int) {
    lateinit var adjacencyList: List<MutableList<Vertex>>
    lateinit var edgesList: MutableList<Edge>

    constructor(nCount: Int, eCount: Int, mCost: Int) : this(nCount, eCount){
        adjacencyList = List(nCount) {
            mutableListOf()
        }

        edgesList = MutableList(eCount) {
            Edge(0, 0, 0)
        }

        for (i in 0 until eCount) {
            val nodeFirst = Random.nextInt(nCount)
            val random = Random.nextInt(nCount - 1)
            val cost = Random.nextInt(1, mCost)

            val nodeSecond: Int = if (nodeFirst > random ) random else {
                random + 1
            }

            adjacencyList[nodeFirst].add(Vertex(nodeSecond, cost))
            adjacencyList[nodeSecond].add(Vertex(nodeFirst, cost))
            edgesList.add(Edge(nodeFirst, nodeSecond, cost))
        }

    }

    fun addEdge(first: Int, second: Int, cost: Int) {
        adjacencyList[first].add(Vertex(second, cost))
        adjacencyList[second].add(Vertex(first, cost))
        edgesList.add(Edge(first, second, cost))
    }

    fun removeEdge(first: Int, second: Int, cost: Int) {
        adjacencyList[first].remove(Vertex(second, cost))
        adjacencyList[second].remove(Vertex(first, cost))
        edgesList.remove(Edge(first, second, cost))
    }

}